package com.HotFlow.TribeCraft.Listener;

import com.HotFlow.TribeCraft.Event.Plugin.PluginTimeChangeEvent;
import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Inventory.Item.ArmorType;
import com.HotFlow.TribeCraft.Permissions.Permissions;
import com.HotFlow.TribeCraft.Player.Extension.PermissionAppointment;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.TribeCraft;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

/**
 * @author HotFlow
 */
public class Listeners implements Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        final TribePlayer player  = TribeCraft.getPlayerManager().getPlayer(event.getEntity().getPlayer().getName());
        List<ItemStack> items = new ArrayList<ItemStack>();
        HashMap<ArmorType,ItemStack> equiements = new HashMap<ArmorType,ItemStack>();
        DeathInventory inventory = new DeathInventory();
        
        for(ItemStack item : event.getEntity().getPlayer().getInventory().getContents())
        {
            if(item != null && item.getType() != Material.AIR)
            {
                items.add(item);
            }
        }

        if(player.getCraftPlayer().getInventory().getHelmet() != null)
        {
            equiements.put(ArmorType.Helmet, player.getCraftPlayer().getInventory().getHelmet());
        }
        if(player.getCraftPlayer().getInventory().getChestplate() != null)
        {
            equiements.put(ArmorType.Chestplate, player.getCraftPlayer().getInventory().getChestplate());
        }
        if(player.getCraftPlayer().getInventory().getLeggings() != null)
        {
            equiements.put(ArmorType.Leggings, player.getCraftPlayer().getInventory().getLeggings());
        }
        if(player.getCraftPlayer().getInventory().getBoots() != null)
        {
            equiements.put(ArmorType.Boots, player.getCraftPlayer().getInventory().getBoots());
        }
        
        if(!player.getCraftPlayer().hasPermission(new Permissions().deathSaveAll))
        {
            List<ItemStack> protectedItems = new ArrayList<ItemStack>();
            
            if(player.getVIPList().size() > 0)
            {
                player.getCraftPlayer().sendMessage("����VIP�ȼ�Ϊ: " + player.getVIPList().get(0).getLevel());
                player.getCraftPlayer().sendMessage("��Ʒ�������: " + (player.getVIPList().get(0).getItemDropChance() * 100) + "%");
                player.getCraftPlayer().sendMessage("װ���������: " + (player.getVIPList().get(0).getArmorDropChance() * 100) + "%");
                player.getCraftPlayer().sendMessage("�������ٷֱ�: " + (player.getVIPList().get(0).getExpDropPercentage() * 100) + "%");
                
//                for(ItemStack item : items)
//                {
//                    for(int i = 1;i < item.getAmount();i++)
//                    {
//                        ItemStack newItem = item.clone();
//                        newItem.setAmount(1);
//                        if(Math.random() >= player.getVIPList().get(0).getItemDropChance())
//                        {
//                            inventory.items.add(newItem);
//                            protectedItems.add(newItem);
//                        }
//                    }
//                }
            }
            else
            {
                player.getCraftPlayer().sendMessage("����VIP�ȼ�Ϊ: 0");
                player.getCraftPlayer().sendMessage("��Ʒ�������: " + (TribeCraft.config.getDouble("ȫ������.��������.��ͨ�û�.��Ʒ�������") * 100) + "%");
                player.getCraftPlayer().sendMessage("װ���������: " + (TribeCraft.config.getDouble("ȫ������.��������.��ͨ�û�.װ���������") * 100) + "%");
                player.getCraftPlayer().sendMessage("�������ٷֱ�: " + (TribeCraft.config.getDouble("ȫ������.��������.��ͨ�û�.�������ٷֱ�") * 100) + "%");
            }
        }
        
        //�ɰ汾
        if(player.getVIPList().size() > 0)
        {
            player.getCraftPlayer().sendMessage("����VIP�ȼ�Ϊ: " + player.getVIPList().get(0).getLevel());
            player.getCraftPlayer().sendMessage("ÿ����Ʒ�ĵ������Ϊ: " + (player.getVIPList().get(0).getItemDropChance() * 100) + "%");
            player.getCraftPlayer().sendMessage("ÿ��װ���ĵ������Ϊ: " + (player.getVIPList().get(0).getArmorDropChance() * 100) + "%");
            player.getCraftPlayer().sendMessage("�ܹ��������: " + (player.getVIPList().get(0).getExpDropPercentage() * 100) + "%");
            
            List<ItemStack> protectedItems = new ArrayList<ItemStack>();
            
            for(ItemStack item : items)
            {
                for(int i = 1;i < item.getAmount();i++)
                {
                    ItemStack newItem = item.clone();
                    newItem.setAmount(1);
                    if(Math.random() >= player.getVIPList().get(0).getItemDropChance())
                    {
                        inventory.items.add(newItem);
                        protectedItems.add(newItem);
                    }
                }
            }
            
            for(ArmorType type : equiements.keySet())
            {
                ItemStack armor = equiements.get(type);
                
                if(Math.random() >= player.getVIPList().get(0).getArmorDropChance())
                {
                    inventory.equiments.put(type,armor);
                    protectedItems.add(armor);
                }
            }
            
            List<ItemStack> dropItems = new ArrayList<ItemStack>();
            for(ItemStack item1 : protectedItems)
            {
                ItemStack dropItem = item1.clone();
                for(ItemStack item2 : protectedItems)
                {
                    if(item1.equals(item2))
                    {
                        dropItem.setAmount(item1.getAmount() + item2.getAmount());
                    }
                }
                dropItems.add(dropItem);
            }
            
            event.setKeepLevel(true);
            event.setDroppedExp(0);
            
            player.setDeathProtectedItems(inventory);
            
            for(ItemStack ditem : dropItems)
            {
                for(int i = 0;i < items.size();i++)
                {
                    ItemStack item = items.get(i);
                    if(item.equals(ditem))
                    {
                        event.getDrops().remove(item);
                        break;
                    }
                }
            }
            
            for(ItemStack pitem : protectedItems)
            {
                for(ItemStack armor : equiements.values())
                {
                    if(armor.equals(pitem))
                    {
                        event.getDrops().remove(armor);
                        break;
                    }
                }
            }
        }
        else
        {
            player.getCraftPlayer().sendMessage("����VIP�ȼ�Ϊ: 0");
            player.getCraftPlayer().sendMessage("ÿ����Ʒ�ĵ������Ϊ: 50%");
            
            List<ItemStack> protectedItems = new ArrayList<ItemStack>();
            
            for(ItemStack item : items)
            {
                ItemStack newItem = item.clone();
                newItem.setAmount(1);
                
                for(int i = 0;i < item.getAmount();i++)
                {
                    if(Math.random() >= 0.5)
                    {
                        newItem.setAmount(newItem.getAmount() + 1);
                    }
                }
                
                inventory.items.add(item);
                protectedItems.add(item);
            }
            
            for(ArmorType type : equiements.keySet())
            {
                ItemStack armor = equiements.get(type);
                
                if(Math.random() >= 0.5)
                {
                    inventory.equiments.put(type,armor);
                    protectedItems.add(armor);
                }
            }
            
            player.setDeathProtectedItems(inventory);
            
            for(ItemStack pitem : protectedItems)
            {
                for(int i = 0;i < items.size();i++)
                {
                    ItemStack item = items.get(i);
                    if(item.equals(pitem))
                    {
                        event.getDrops().remove(item);
                        break;
                    }
                }
            }
            
            for(ItemStack pitem : protectedItems)
            {
                for(ItemStack armor : equiements.values())
                {
                    if(armor.equals(pitem))
                    {
                        event.getDrops().remove(armor);
                        break;
                    }
                }
            }
        }
        
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        final TribePlayer player  = TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName());
        player.getCraftPlayer().getInventory().clear();

        for(ItemStack item : player.getDeathProtectedItems().items)
        {
            player.getCraftPlayer().getInventory().addItem(item);
        }
        
        for(ArmorType type :  player.getDeathProtectedItems().equiments.keySet())
        {
            ItemStack armor = player.getDeathProtectedItems().equiments.get(type);

            if(type == ArmorType.Helmet)
            {
                player.getCraftPlayer().getInventory().setHelmet(armor);
            }
            else if(type == ArmorType.Chestplate)
            {
                player.getCraftPlayer().getInventory().setChestplate(armor);
            }
            else if(type == ArmorType.Leggings)
            {
                player.getCraftPlayer().getInventory().setLeggings(armor);
            }
            else if(type == ArmorType.Boots)
            {
                player.getCraftPlayer().getInventory().setBoots(armor);
            }
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        final TribePlayer player = new TribePlayer(event.getPlayer());
        TribeCraft.getPlayerManager().setPlayer(event.getPlayer().getName(), player);
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if ((event.getPlayer().getItemInHand() != null) && (!event.getPlayer().getItemInHand().getType().equals(Material.AIR)))
        {
            if (event.getPlayer().getItemInHand().getType().equals(Material.INK_SACK))
            {
                if(event.getPlayer().hasPermission(new com.HotFlow.TribeCraft.Permissions.Permissions().admin))
                {
                    return;
                }
                
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
                {
                    if (event.getClickedBlock().getType() == Material.getMaterial(6))
                    {
                      event.setCancelled(true);
                      event.getPlayer().sendMessage("����������ֹʹ�ùǷ۶� " + Material.getMaterial(6).name() + " ���д߳�!");
                    }
                }
            }
            else if(event.getPlayer().getItemInHand().getType().equals(Material.WOOD_SPADE))
            {
                if(event.getPlayer().hasPermission(new com.HotFlow.TribeCraft.Permissions.Permissions().admin))
                {
                    TribePlayer tribePlayer = TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName());
                    Block block = event.getClickedBlock();

                    if(event.getAction() == Action.LEFT_CLICK_BLOCK)
                    {
                        tribePlayer.setGateSelectedLocation1(block.getLocation());
                        tribePlayer.getCraftPlayer().sendMessage("���ѡ����[" + "����=" + block.getLocation().getWorld().getName() + "," + "x=" + block.getLocation().getX() + "," + "y=" + block.getLocation().getY() + "," + "z=" + block.getLocation().getZ() + "]");
                        event.setCancelled(true);
                    }
                    else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
                    {
                        tribePlayer.setGateSelectedLocation2(block.getLocation());
                        tribePlayer.getCraftPlayer().sendMessage("�Ҽ�ѡ����[" + "����=" + block.getLocation().getWorld().getName() + "," + "x=" + block.getLocation().getX() + "," + "y=" + block.getLocation().getY() + "," + "z=" + block.getLocation().getZ() + "]");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event)
    {
        if(event.getCause().equals(TeleportCause.NETHER_PORTAL))
        {
            event.useTravelAgent(false);
            
            if(TribeCraft.getPortalGateManager().getPortalGate(event.getFrom()) != null)
            {
                PortalGate gate = TribeCraft.getPortalGateManager().getPortalGate(event.getFrom());
                
                if(!gate.getMessage().equals(""))
                {
                    event.getPlayer().sendMessage(gate.getMessage());
                }
                
                if(gate.getType().equals(PortalGateType.Location))
                {
                    if(gate.getTo() != null)
                    {
                        event.getPlayer().teleport(gate.getTo());
                        event.setCancelled(true);
                    }
                    else
                    {
                        event.getPlayer().sendMessage("�ô�������δ���ô���Ŀ�ĵ�!");
                        event.setCancelled(true);
                    }
                }
                else if(gate.getType().equals(PortalGateType.Random))
                {
                    Random random = new Random();
                    int x = random.nextInt(3000) + 1;
                    int y = 100;
                    int z = random.nextInt(3000) + 1;

                    Location location = new Location(event.getPlayer().getWorld(),x,y,z);

                   event.getPlayer().teleport(location);
                }
                else
                {
                    TribeCraft.getPermissionManager().playerAdd(event.getPlayer(),"Tribe.user.survival");
                    TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName()).setPermissionAppointment(new PermissionAppointment(10,"Tribe.user.survival"));
                    event.setCancelled(true);
                }

                for(String command : gate.getCommands())
                {
                    event.getPlayer().performCommand(command);
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        TribePlayer player = TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName());
        if(player.getTeleportingAppointment() != null)
        {
            if((event.getFrom().getX() != event.getTo().getX()) || (event.getFrom().getZ() != event.getTo().getZ()))
            {
                player.setTeleportingAppointment(null);
                player.getCraftPlayer().sendMessage(ChatColor.GOLD + "��ȡ������!");
            }
        }
    }
    
    @EventHandler
    public void onPluginTimeChange(PluginTimeChangeEvent event)
    {
        for(TribePlayer player : TribeCraft.getPlayerManager().getPlayers())
        {
            if(player.getTeleportingAppointment()!= null)
            {
                if(player.getTeleportingAppointment().getTime() > 0)
                {
                    player.getTeleportingAppointment().setTime(player.getTeleportingAppointment().getTime() - 1);
                }
                else
                {
                    player.getCraftPlayer().sendMessage(ChatColor.GOLD + "׼������...");
                    player.getCraftPlayer().teleport(player.getTeleportingAppointment().getLocation());
                    player.setTeleportingAppointment(null);
                }

            }
            
            if(player.getPermissionAppointment() != null)
            {
                if(player.getPermissionAppointment().getTime() > 0)
                {
                    TribeCraft.getPermissionManager().playerAdd(player.getCraftPlayer(), player.getPermissionAppointment().getPermission());
                    player.getPermissionAppointment().setTime(player.getPermissionAppointment().getTime() - 1);
                }
                else
                {
                    TribeCraft.getPermissionManager().playerRemove(player.getCraftPlayer(), player.getPermissionAppointment().getPermission());
                    player.setPermissionAppointment(null);
                }
            }
        }
    }
}
