package com.HotFlow.TribeCraft.CommandExecutor;

import com.HotFlow.TribeCraft.Player.Extension.TeleportAppointment;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.World.Area;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class UserExecutor implements CommandExecutor
{
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
        if(sender instanceof Player)
        {
            final Player player = (Player) sender;
            final TribePlayer tribePlayer = TribeCraft.getPlayerManager().getPlayer(player.getName());
            
            if(args.length > 0)
            {
                if(args[0].equalsIgnoreCase("survival"))
                {
                    if(tribePlayer.getTeleportingAppointment() != null)
                    {
                        player.sendMessage("�����ظ�ʹ�ô�����!");
                        return false;
                    }
                    
                    final Area area = new Area(
                            TribeCraft.getResidenceManager().getByName(TribeCraft.getPluginManager().getSurvivalProperties().mainTown).getArea(TribeCraft.getPluginManager().getSurvivalProperties().subArea).getHighLoc(),
                            TribeCraft.getResidenceManager().getByName(TribeCraft.getPluginManager().getSurvivalProperties().mainTown).getArea(TribeCraft.getPluginManager().getSurvivalProperties().subArea).getLowLoc());
                    if((player.getWorld().equals(getServer().getWorld("world"))) && (Area.isAreaContainLocation(area, player.getLocation())))
                    {
                        getServer().getScheduler().runTask(TribeCraft.plugin, new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                while(true)
                                {
                                    Random random = new Random();
                                    int x = random.nextInt(TribeCraft.getPluginManager().getSurvivalProperties().maxX) + 1;
                                    int z = random.nextInt(TribeCraft.getPluginManager().getSurvivalProperties().maxZ) + 1;
                                    int i = player.getWorld().getMaxHeight();

                                    for(int y = i;y > 0;y--)
                                    {
                                        Block block = new Location(player.getWorld(),(double)x,(double)y,(double)z).getBlock();
                                        
                                        if(block != null)
                                        {
                                            if((!block.getType().equals(Material.AIR)) && (!block.getType().equals(Material.LAVA)) && (!block.getType().equals(Material.WATER)) && (!block.getType().equals(Material.BEDROCK)))
                                            {
                                                if((!block.getType().equals(Material.WOOD)) && (!block.getType().equals(Material.LEAVES)))
                                                {
                                                    if(y > TribeCraft.getPluginManager().getSurvivalProperties().maxY)
                                                    {
                                                        break;
                                                    }

                                                    final Location location = new Location(player.getWorld(),x,y+1,z);

                                                    if(!Area.isAreaContainLocation(area, location))
                                                    {
                                                        if(TribeCraft.getResidenceManager().getByLoc(location) == null)
                                                        {
                                                            if(TribeCraft.getPermissionManager().playerHas(player, "Tribe.user.survival"))
                                                            {
                                                                player.sendMessage(ChatColor.GOLD + "���ڴ���...");
                                                                player.teleport(location);
                                                                return;
                                                            }
                                                            else
                                                            {
                                                                player.sendMessage(ChatColor.GOLD + "���ͽ���" + 10 + " ���ڿ�ʼ.��Ҫ�ƶ�");
                                                                tribePlayer.setTeleportingAppointment(new TeleportAppointment(10,location));
                                                                return;
                                                            }
                                                        } 
                                                    }
                                                    else
                                                    {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                    else
                    {
                        player.sendMessage("���Ѿ���������!");
                        return false;
                    }
                }
                else
                {
                    player.sendMessage("/tribe survival: ������ͳ����Ƿ�Χ��");
                    return false;
                }
            }
            else
            {
                player.sendMessage("/tribe survival: ������ͳ����Ƿ�Χ��");
                return false;
            }
        }
        else
        {
            TribeCraft.logger.info("������ֻ������ҷ���!");
            return false;
        }
        return false;
    }
}
