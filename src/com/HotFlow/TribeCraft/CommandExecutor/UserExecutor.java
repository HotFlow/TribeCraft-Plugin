package com.HotFlow.TribeCraft.CommandExecutor;

import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;
            final TribePlayer tribePlayer = TribeCraft.getPlayerManager().getPlayer(player.getName());

            if (args.length > 0)
            {
                if (args[0].equalsIgnoreCase("survival"))
                {
                    for (DelayTask task : tribePlayer.getDelayTaskList())
                    {
                        if (task.getDescription() != null)
                        {
                            if (task.getDescription().equalsIgnoreCase("Teleport"))
                            {
                                player.sendMessage("�����ظ�ʹ�ô�����!");
                                return false;
                            }
                        }
                    }

                    final Area area = new Area(
                            TribeCraft.getResidenceManager().getByName(TribeCraft.getPluginConfig().getSurvival().mainTown).getArea(TribeCraft.getPluginConfig().getSurvival().subArea).getHighLoc(),
                            TribeCraft.getResidenceManager().getByName(TribeCraft.getPluginConfig().getSurvival().mainTown).getArea(TribeCraft.getPluginConfig().getSurvival().subArea).getLowLoc());

                    if ((player.getWorld().equals(getServer().getWorld("world"))) && (Area.isAreaContainLocation(area, player.getLocation())))
                    {
                        getServer().getScheduler().runTask(TribeCraft.plugin, new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                while (true)
                                {
                                    Random random = new Random();
                                    int x = random.nextInt(TribeCraft.getPluginConfig().getSurvival().maxX) + 1;
                                    int z = random.nextInt(TribeCraft.getPluginConfig().getSurvival().maxZ) + 1;
                                    int h = player.getWorld().getMaxHeight();

                                    for (int y = h; y > 0; y--)
                                    {
                                        Block block = new Location(player.getWorld(), (double) x, (double) y, (double) z).getBlock();

                                        if (!(block.getType().equals(Material.AIR)) && !(block.getType().equals(Material.BEDROCK)))
                                        {
                                            if ((!block.getType().equals(Material.LAVA))
                                                    && (!block.getType().equals(Material.WATER))
                                                    && (!block.getType().equals(Material.STATIONARY_WATER))
                                                    && (!block.getType().equals(Material.STATIONARY_LAVA)))
                                            {
                                                if (y > TribeCraft.getPluginConfig().getSurvival().maxY)
                                                {
                                                    break;
                                                }

                                                final Location location = new Location(player.getWorld(), x, (y + 1), z);

                                                Block block1 = location.getBlock();

                                                if (!Area.isAreaContainLocation(area, location))
                                                {
                                                    if (TribeCraft.getResidenceManager().getByLoc(location) == null)
                                                    {
                                                        if (TribeCraft.getPermissionManager().playerHas(player, "Tribe.user.survival"))
                                                        {
                                                            player.sendMessage(ChatColor.GOLD + "���ڴ���...");
                                                            player.teleport(location);
                                                            return;
                                                        }
                                                        else
                                                        {
                                                            player.sendMessage(ChatColor.GOLD + "���ͽ���" + 10 + " ���ڿ�ʼ.��Ҫ�ƶ�");

                                                            tribePlayer.addDelayTask(new DelayTask(10, "Teleport")
                                                            {
                                                                @Override
                                                                public void run()
                                                                {
                                                                    tribePlayer.getCraftPlayer().sendMessage(ChatColor.GOLD + "׼������...");
                                                                    tribePlayer.getCraftPlayer().teleport(location);
                                                                }
                                                            });
                                                            return;
                                                        }
                                                    }
                                                    else
                                                    {
                                                        break;
                                                    }
                                                }
                                                else
                                                {
                                                    break;
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
