package com.HotFlow.TribeCraft.CommandExecutor;

import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.Utils.System.ISystem;
import com.HotFlow.TribeCraft.World.Area;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class AdminExecutor implements CommandExecutor
{

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (args.length > 0)
            {
                if (args[0].equalsIgnoreCase("gate"))
                {
                    if (args.length > 1)
                    {
                        if (args[1].equalsIgnoreCase("create"))
                        {
                            if ((Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation1() == null) || (Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation2() == null))
                            {
                                player.sendMessage("必须用木铲选择两个点!");
                                return false;
                            }

                            for (PortalGate gate : Main.getPortalGateManager().getPortalGates())
                            {
                                if (Area.isAreaOverlappingArea(new Area(
                                        Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation1(),
                                        Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation1()),
                                        gate.getFrom()))
                                {
                                    player.sendMessage("该区域已有传送门!");
                                    return false;
                                }
                            }

                            if (args.length > 3)
                            {
                                if (Main.getPortalGateManager().getPortalGate(args[2]) != null)
                                {
                                    player.sendMessage("传送门已存在!");
                                    return false;
                                }

                                if (ISystem.integer.isInt(args[3]))
                                {
                                    PortalGate gate = new PortalGate(args[2]);

                                    gate.setFrom(new Area(Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation1(),
                                            Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation2()));

                                    if (Integer.parseInt(args[3]) == 0)
                                    {
                                        gate.setType(PortalGateType.Location);
                                    }
                                    else if (Integer.parseInt(args[3]) == 1)
                                    {
                                        gate.setType(PortalGateType.Random);
                                    }
                                    else
                                    {
                                        gate.setType(PortalGateType.Command);
                                    }

                                    Main.getPortalGateManager().addPortalGate(gate);
                                    player.sendMessage("成功创建传送门!");
                                    return true;
                                }
                                else
                                {
                                    PortalGate gate = new PortalGate(args[2]);

                                    gate.setFrom(new Area(Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation1(),
                                            Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation2()));

                                    Main.getPortalGateManager().addPortalGate(gate);
                                    player.sendMessage("成功创建传送门!");
                                    return true;
                                }
                            }
                            else if (args.length > 2)
                            {
                                if (Main.getPortalGateManager().getPortalGate(args[2]) != null)
                                {
                                    player.sendMessage("传送门已存在!");
                                    return false;
                                }
                                PortalGate gate = new PortalGate(args[2]);

                                gate.setFrom(new Area(Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation1(),
                                        Main.getPlayerManager().getPlayer(player.getUniqueId()).getGateSelectedLocation2()));

                                Main.getPortalGateManager().addPortalGate(gate);
                                player.sendMessage("成功创建传送门!");
                                return true;
                            }
                            else
                            {
                                player.sendMessage("/triadmin gate create [传送门名] <传送类型[1,2,3]>");
                                return false;
                            }
                        }
                        else if (args[1].equalsIgnoreCase("remove"))
                        {
                            if (args.length > 2)
                            {
                                if (Main.getPortalGateManager().removePortalGate(Main.getPortalGateManager().getPortalGate(args[2])))
                                {
                                    player.sendMessage("成功删除传送门!");
                                    return true;
                                }
                                else
                                {
                                    player.sendMessage("传送门不存在!");
                                    return false;
                                }
                            }
                            else
                            {
                                player.sendMessage("/triadmin gate remove [传送门名]");
                                return false;
                            }
                        }
                        else if (args[1].equalsIgnoreCase("list"))
                        {
                            StringBuilder sb = new StringBuilder();

                            for (PortalGate gate : Main.getPortalGateManager().getPortalGates())
                            {
                                sb.append("[").append(gate.getName()).append("]").append(",");
                            }

                            if (sb.length() > 0)
                            {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            else
                            {
                                sb.append("[]");
                            }

                            player.sendMessage("传送门列表:" + sb.toString());
                            return true;
                        }
                        else if (args[1].equalsIgnoreCase("check"))
                        {
                            if (args.length > 2)
                            {
                                if (Main.getPortalGateManager().getPortalGate(args[2]) != null)
                                {
                                    PortalGate gate = Main.getPortalGateManager().getPortalGate(args[2]);

                                    player.sendMessage("=====================================");
                                    player.sendMessage("传送门名称: " + gate.getName());
                                    player.sendMessage("传送类型: " + gate.getType().name());
                                    player.sendMessage("传送提示: " + gate.getMessage());
                                    player.sendMessage("传送出发点1: " + "[" + "世界=" + gate.getFrom().getLocation1().getWorld().getName() + ","
                                            + "x=" + gate.getFrom().getLocation1().getX() + ","
                                            + "y=" + gate.getFrom().getLocation1().getY() + ","
                                            + "z=" + gate.getFrom().getLocation1().getZ() + "]");
                                    player.sendMessage("传送出发点2: " + "[" + "世界=" + gate.getFrom().getLocation2().getWorld().getName() + ","
                                            + "x=" + gate.getFrom().getLocation2().getX() + ","
                                            + "y=" + gate.getFrom().getLocation2().getY() + ","
                                            + "z=" + gate.getFrom().getLocation2().getZ() + "]");
                                    player.sendMessage("传送目的地: " + (gate.getTo() == null ? "尚未设置" : "[" + "世界=" + gate.getTo().getWorld().getName() + ","
                                            + "x=" + gate.getTo().getX() + ","
                                            + "y=" + gate.getTo().getY() + ","
                                            + "z=" + gate.getTo().getZ() + "]"));

                                    player.sendMessage("传送触发命令: ");
                                    for (String c : gate.getCommands())
                                    {
                                        player.sendMessage("- " + c);
                                    }
                                    player.sendMessage("=====================================");
                                    return true;
                                }
                                else
                                {
                                    player.sendMessage("传送门不存在!");
                                    return false;
                                }
                            }
                            else
                            {
                                for (PortalGate gate : Main.getPortalGateManager().getPortalGates())
                                {
                                    if (Area.isAreaContainLocation(gate.getFrom(), player.getLocation()))
                                    {
                                        player.sendMessage("=====================================");
                                        player.sendMessage("传送门名称: " + gate.getName());
                                        player.sendMessage("传送类型: " + gate.getType().name());
                                        player.sendMessage("传送提示: " + gate.getMessage());
                                        player.sendMessage("传送出发点1: " + "[" + "世界=" + gate.getFrom().getLocation1().getWorld().getName() + ","
                                                + "x=" + gate.getFrom().getLocation1().getX() + ","
                                                + "y=" + gate.getFrom().getLocation1().getY() + ","
                                                + "z=" + gate.getFrom().getLocation1().getZ() + "]");
                                        player.sendMessage("传送出发点2: " + "[" + "世界=" + gate.getFrom().getLocation2().getWorld().getName() + ","
                                                + "x=" + gate.getFrom().getLocation2().getX() + ","
                                                + "y=" + gate.getFrom().getLocation2().getY() + ","
                                                + "z=" + gate.getFrom().getLocation2().getZ() + "]");

                                        player.sendMessage("传送目的地: " + (gate.getTo() == null ? "尚未设置" : "[" + "世界=" + gate.getTo().getWorld().getName() + ","
                                                + "x=" + gate.getTo().getX() + ","
                                                + "y=" + gate.getTo().getY() + ","
                                                + "z=" + gate.getTo().getZ() + "]"));

                                        player.sendMessage("传送触发命令: ");
                                        for (String c : gate.getCommands())
                                        {
                                            player.sendMessage("- " + c);
                                        }
                                        player.sendMessage("=====================================");
                                        return true;
                                    }
                                }

                                player.sendMessage("/triadmin gate check [传送门名]");
                                return false;

                            }
                        }
                        else if (args[1].equalsIgnoreCase("message"))
                        {
                            if (args.length > 3)
                            {
                                if (Main.getPortalGateManager().getPortalGate(args[2]) != null)
                                {
                                    Main.getPortalGateManager().getPortalGate(args[2]).setMessage(args[3]);
                                    player.sendMessage("提示信息设置成功!");
                                    return true;
                                }
                                else
                                {
                                    player.sendMessage("传送门不存在！");
                                    return false;
                                }
                            }
                            else
                            {
                                player.sendMessage("/triadmin gate message [传送门名] [提示信息]");
                                return false;
                            }
                        }
                        else if (args[1].equalsIgnoreCase("command"))
                        {
                            if (args.length > 4)
                            {
                                if (Main.getPortalGateManager().getPortalGate(args[2]) != null)
                                {
                                    if (args[3].equalsIgnoreCase("add"))
                                    {
                                        List<String> commands = Main.getPortalGateManager().getPortalGate(args[2]).getCommands();
                                        StringBuilder sb = new StringBuilder();

                                        for (int i = 4; i < args.length; i++)
                                        {
                                            sb.append(args[i]).append(" ");
                                        }

                                        if (sb.length() > 0)
                                        {
                                            sb.deleteCharAt(sb.length() - 1);
                                        }

                                        commands.add(sb.toString());

                                        Main.getPortalGateManager().getPortalGate(args[2]).setCommands(commands);
                                        player.sendMessage("成功添加命令!");
                                        return true;
                                    }
                                    else if (args[3].equalsIgnoreCase("remove"))
                                    {
                                        List<String> commands = Main.getPortalGateManager().getPortalGate(args[2]).getCommands();

                                        StringBuilder sb = new StringBuilder();

                                        for (int i = 4; i < args.length; i++)
                                        {
                                            sb.append(args[i]).append(" ");
                                        }

                                        if (sb.length() > 0)
                                        {
                                            sb.deleteCharAt(sb.length() - 1);
                                        }

                                        if (commands.remove(sb.toString()))
                                        {
                                            player.sendMessage("成功删除命令!");
                                        }
                                        else
                                        {
                                            player.sendMessage("命令不存在!");
                                        }

                                        Main.getPortalGateManager().getPortalGate(args[2]).setCommands(commands);
                                        return true;
                                    }
                                    else
                                    {
                                        player.sendMessage("/triadmin gate command [传送门名] [add/remove] [命令]");
                                        return false;
                                    }
                                }
                                else
                                {
                                    player.sendMessage("传送门不存在!");
                                    return false;
                                }
                            }
                            else
                            {
                                player.sendMessage("/triadmin gate command [传送门名] [add/remove] [命令]");
                                return false;
                            }
                        }
                        else if (args[1].equalsIgnoreCase("location"))
                        {
                            if (args.length > 2)
                            {
                                if (Main.getPortalGateManager().getPortalGate(args[2]) != null)
                                {
                                    Main.getPortalGateManager().getPortalGate(args[2]).setTo(player.getLocation());
                                    player.sendMessage("成功设置传送点!");
                                    return true;
                                }
                                else
                                {
                                    player.sendMessage("传送门不存在!");
                                    return false;
                                }
                            }
                            else
                            {
                                player.sendMessage("/triadmin gate location [传送门名]");
                                return false;
                            }
                        }
                        else
                        {
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate create" + ChatColor.WHITE + ": " + "创建一个传送门.");
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate remove" + ChatColor.WHITE + ": " + "移除一个传送门.");
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate list" + ChatColor.WHITE + ": " + "列出传送门名称.");
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate check" + ChatColor.WHITE + ": " + "查看传送门信息.");
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate message" + ChatColor.WHITE + ": " + "设置提示信息.");
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate command" + ChatColor.WHITE + ": " + "设置触发命令.");
                            player.sendMessage(ChatColor.GOLD + "/triadmin gate location" + ChatColor.WHITE + ": " + "设置为传送点.");
                            return false;
                        }
                    }
                    else
                    {
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate create" + ChatColor.WHITE + ": " + "创建一个传送门.");
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate remove" + ChatColor.WHITE + ": " + "移除一个传送门.");
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate list" + ChatColor.WHITE + ": " + "列出传送门名称.");
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate check" + ChatColor.WHITE + ": " + "查看传送门信息.");
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate message" + ChatColor.WHITE + ": " + "设置提示信息.");
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate command" + ChatColor.WHITE + ": " + "设置触发命令.");
                        player.sendMessage(ChatColor.GOLD + "/triadmin gate location" + ChatColor.WHITE + ": " + "设置为传送点.");
                        return false;
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.GOLD + "/tribeadmin gate" + ChatColor.WHITE + ": 传送门指令.");
                    return false;
                }
            }
            else
            {
                player.sendMessage(ChatColor.GOLD + "/tribeadmin gate" + ChatColor.WHITE + ": 传送门指令.");
                return false;
            }
        }
        else
        {
            if (args.length > 0)
            {
                if (args[0].equals("op"))
                {
                    if (args.length > 1)
                    {
                        List<String> opList = Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().whiteList;
                        opList.add(args[1]);
                        Main.config.set("全局配置.服务器设置.权限检测.OP检测.白名单", opList);
                        Main.getPluginConfig().reload();
                        getServer().getOfflinePlayer(args[1]).setOp(true);
                        Main.getPluginConfig().save();
                        Main.logger.info("成功添加玩家到OP白名单!");
                        return true;
                    }
                    else
                    {
                        Main.logger.info("/tribeadmin op [玩家]");
                        return false;
                    }
                }
                else if (args[0].equals("deop"))
                {
                    if (args.length > 1)
                    {
                        List<String> opList = Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().whiteList;
                        opList.remove(args[1]);
                        Main.config.set("全局配置.服务器设置.权限检测.OP检测.白名单", opList);
                        Main.getPluginConfig().reload();
                        getServer().getOfflinePlayer(args[1]).setOp(false);
                        
                        if(getServer().getOfflinePlayer(args[1]).isOnline())
                        {
                            getServer().getPlayer(args[1]).setGameMode(GameMode.SURVIVAL);
                        }
                        
                        Main.getPluginConfig().save();
                        Main.logger.info("成功移除玩家从OP白名单!");
                        return true;
                    }
                    else
                    {
                        Main.logger.info("/tribeadmin deop [玩家]");
                        return false;
                    }
                }
                else if (args[0].equals("gamemode"))
                {
                    if (args.length > 1)
                    {
                        List<String> creativeList = Main.getPluginConfig().getServerConfig().getPermissionDetector().getCreativeDetector().whiteList;
                        creativeList.add(args[1]);
                        Main.config.set("全局配置.服务器设置.权限检测.创造检测.白名单", creativeList);
                        Main.getPluginConfig().reload();

                        if(getServer().getOfflinePlayer(args[1]).isOnline())
                        {
                            getServer().getPlayer(args[1]).setGameMode(GameMode.CREATIVE);
                        }
                        
                        Main.getPluginConfig().save();
                        Main.logger.info("成功添加玩家到创造白名单!");
                        return true;
                    }
                    else
                    {
                        Main.logger.info("/tribeadmin gamemode [玩家]");
                        return false;
                    }
                }
                else if (args[0].equals("degamemode"))
                {
                    if (args.length > 1)
                    {
                        List<String> creativeList = Main.getPluginConfig().getServerConfig().getPermissionDetector().getCreativeDetector().whiteList;
                        creativeList.remove(args[1]);
                        Main.config.set("全局配置.服务器设置.权限检测.创造检测", creativeList);
                        Main.getPluginConfig().reload();
                        
                        if(getServer().getOfflinePlayer(args[1]).isOnline())
                        {
                            getServer().getPlayer(args[1]).setGameMode(GameMode.SURVIVAL);
                        }
                        
                        Main.getPluginConfig().save();
                        Main.logger.info("成功移除玩家从创造白名单!");
                        return true;
                    }
                    else
                    {
                        Main.logger.info("/tribeadmin deop [玩家]");
                        return false;
                    }
                }
                else
                {
                    Main.logger.info("/tribeadmin op: 添加玩家到OP白名单.");
                    Main.logger.info("/tribeadmin deop: 将玩家从OP白名单中移除.");
                    Main.logger.info("/tribeadmin gamemode: 添加玩家到创造白名单.");
                    Main.logger.info("/tribeadmin degamemode: 将玩家从创造白名单中移除.");
                    return false;
                }
            }
            else
            {
                Main.logger.info("/tribeadmin op: 添加玩家到OP白名单.");
                Main.logger.info("/tribeadmin deop: 将玩家从OP白名单中移除.");
                Main.logger.info("/tribeadmin gamemode: 添加玩家到创造白名单.");
                Main.logger.info("/tribeadmin degamemode: 将玩家从创造白名单中移除.");
                return false;
            }
        }
    }

}
