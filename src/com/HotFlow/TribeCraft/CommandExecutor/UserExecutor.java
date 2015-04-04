package com.HotFlow.TribeCraft.CommandExecutor;

import static org.bukkit.Bukkit.getServer;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.World.Area;

/**
 * @author HotFlow
 */
public class UserExecutor implements CommandExecutor {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			final TribePlayer tribePlayer = TribeCraft.getPlayerManager()
					.getPlayer(player.getName());

			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("survival")) {
					if (tribePlayer.teleportingTask != 0) {
						player.sendMessage("�����ظ�ʹ�ô�����!");
						return false;
					}

					Area area = new Area(TribeCraft.getResidenceManager()
							.getByName("Main").getArea("main").getHighLoc(),
							TribeCraft.getResidenceManager().getByName("Main")
									.getArea("main").getLowLoc());
					if ((player.getWorld()
							.equals(getServer().getWorld("world")))
							&& (Area.isAreaContainLocation(area,
									player.getLocation()))) {
						int heightestBlock = -1;
						Random random = new Random();
						int x;
						int z;
						int y = 65;
						do {
							x = random.nextInt(3000) + 1;
							z = random.nextInt(3000) + 1;
							int maxHeight = player.getWorld().getMaxHeight();
							for (int i = maxHeight; i > 0; i++) {
								if (player.getWorld().getBlockAt(x, i, z)
										.getTypeId() != 0) {
									heightestBlock = i;
									y = i;
									break;
								}
							}
						} while (heightestBlock != -1);
						final Location location = new Location(
								player.getWorld(), x, y, z);

						while (!Area.isAreaContainLocation(area, location)) {
							if (TribeCraft.getPermissionManager().playerHas(
									player, "Tribe.user.survival")) {
								player.sendMessage(ChatColor.GOLD + "���ڴ���...");
								player.teleport(location);
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "���ͽ���" + 10
										+ " ���ڿ�ʼ.��Ҫ�ƶ�");
								tribePlayer.teleportingTask = getServer()
										.getScheduler()
										.scheduleAsyncRepeatingTask(
												TribeCraft.plugin,
												new Runnable() {
													@Override
													public void run() {
														if (tribePlayer
																.getTeleportingTime() > 0) {
															player.sendMessage(ChatColor.GOLD
																	+ "���͵���ʱ: "
																	+ tribePlayer
																			.getTeleportingTime());
															tribePlayer
																	.setTeleportingTime(tribePlayer
																			.getTeleportingTime() - 1);
														} else {
															player.sendMessage(ChatColor.GOLD
																	+ "׼������...");
															tribePlayer
																	.setTeleportingTime(10);
															player.teleport(location);
															getServer()
																	.getScheduler()
																	.cancelTask(
																			tribePlayer.teleportingTask);
															tribePlayer.teleportingTask = 0;
														}
													}
												}, 0L, 20L);
								return true;
							}
						}
					} else {
						player.sendMessage("���Ѿ���������!");
						return false;
					}
				} else {
					player.sendMessage("/tribe survival: ������ͳ����Ƿ�Χ��");
					return false;
				}
			} else {
				player.sendMessage("/tribe survival: ������ͳ����Ƿ�Χ��");
				return false;
			}
		} else {
			TribeCraft.logger.info("������ֻ������ҷ���!");
			return false;
		}
		return false;
	}

}
