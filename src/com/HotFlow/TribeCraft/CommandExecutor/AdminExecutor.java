package com.HotFlow.TribeCraft.CommandExecutor;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.Utils.System.ISystem;
import com.HotFlow.TribeCraft.World.Area;

/**
 * @author HotFlow
 */
public class AdminExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("gate")) {
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("create")) {
							if ((TribeCraft.getPlayerManager()
									.getPlayer(player.getName())
									.getGateSelectedLocation1() == null)
									|| (TribeCraft.getPlayerManager()
											.getPlayer(player.getName())
											.getGateSelectedLocation2() == null)) {
								player.sendMessage("��a"
										+ com.HotFlow.TribeCraft.TribeCraft.prefix
										+ "������ľ��ѡ��������!");
								return false;
							}

							for (PortalGate gate : TribeCraft
									.getPortalGateManager().getPortalGates()) {
								if (Area.isAreaOverlappingArea(new Area(
										TribeCraft.getPlayerManager()
												.getPlayer(player.getName())
												.getGateSelectedLocation1(),
										TribeCraft.getPlayerManager()
												.getPlayer(player.getName())
												.getGateSelectedLocation1()),
										gate.getFrom())) {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "���������д�����!");
									return false;
								}
							}

							if (args.length > 3) {
								if (TribeCraft.getPortalGateManager()
										.getPortalGate(args[2]) != null) {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�������Ѵ���!");
									return false;
								}

								if (ISystem.integer.isInt(args[3])) {
									PortalGate gate = new PortalGate(args[2]);
									gate.setFrom(new Area(
											TribeCraft
													.getPlayerManager()
													.getPlayer(player.getName())
													.getGateSelectedLocation1(),
											TribeCraft
													.getPlayerManager()
													.getPlayer(player.getName())
													.getGateSelectedLocation2()));
									if (Integer.parseInt(args[3]) == 0) {
										gate.setType(PortalGateType.Location);
									} else if (Integer.parseInt(args[3]) == 1) {
										gate.setType(PortalGateType.Random);
									} else {
										gate.setType(PortalGateType.Command);
									}

									TribeCraft.getPortalGateManager()
											.addPortalGate(gate);
									player.sendMessage("��a"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�ɹ�����������!");
									return true;
								} else {
									PortalGate gate = new PortalGate(args[2]);
									gate.setFrom(new Area(
											TribeCraft
													.getPlayerManager()
													.getPlayer(player.getName())
													.getGateSelectedLocation1(),
											TribeCraft
													.getPlayerManager()
													.getPlayer(player.getName())
													.getGateSelectedLocation2()));
									TribeCraft.getPortalGateManager()
											.addPortalGate(gate);
									player.sendMessage("��a"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�ɹ�����������!");
									return true;
								}
							} else if (args.length > 2) {
								if (TribeCraft.getPortalGateManager()
										.getPortalGate(args[2]) != null) {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�������Ѵ���!");
									return false;
								}
								PortalGate gate = new PortalGate(args[2]);
								gate.setFrom(new Area(TribeCraft
										.getPlayerManager()
										.getPlayer(player.getName())
										.getGateSelectedLocation1(), TribeCraft
										.getPlayerManager()
										.getPlayer(player.getName())
										.getGateSelectedLocation2()));
								TribeCraft.getPortalGateManager()
										.addPortalGate(gate);
								player.sendMessage("��a"
										+ com.HotFlow.TribeCraft.TribeCraft.prefix
										+ "�ɹ�����������!");
								return true;
							} else {
								player.sendMessage("��6/triadmin gate create [��������] <��������[1,2,3]>");
								return false;
							}
						} else if (args[1].equalsIgnoreCase("remove")) {
							if (args.length > 2) {
								if (TribeCraft
										.getPortalGateManager()
										.removePortalGate(
												TribeCraft
														.getPortalGateManager()
														.getPortalGate(args[2]))) {
									player.sendMessage("��a"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�ɹ�ɾ��������!");
									return true;
								} else {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�����Ų�����!");
									return false;
								}
							} else {
								player.sendMessage("��6/triadmin gate remove [��������]");
								return false;
							}
						} else if (args[1].equalsIgnoreCase("list")) {
							StringBuilder sb = new StringBuilder();

							for (PortalGate gate : TribeCraft
									.getPortalGateManager().getPortalGates()) {
								sb.append("[").append(gate.getName())
										.append("]").append(",");
							}

							if (sb.length() > 0) {
								sb.deleteCharAt(sb.length() - 1);
							} else {
								sb.append("[]");
							}

							player.sendMessage("��2�������б�:" + sb.toString());
							return true;
						} else if (args[1].equalsIgnoreCase("check")) {
							if (args.length > 2) {
								if (TribeCraft.getPortalGateManager()
										.getPortalGate(args[2]) != null) {
									PortalGate gate = TribeCraft
											.getPortalGateManager()
											.getPortalGate(args[2]);
									player.sendMessage("��2=====================================");
									player.sendMessage("��2����������: "
											+ gate.getName());
									player.sendMessage("��2��������: "
											+ gate.getType().name());
									player.sendMessage("��2������ʾ: "
											+ gate.getMessage());
									player.sendMessage("��2���ͳ�����1: "
											+ "["
											+ "����="
											+ gate.getFrom().getLocation1()
													.getWorld().getName()
											+ ","
											+ "x="
											+ gate.getFrom().getLocation1()
													.getX()
											+ ","
											+ "y="
											+ gate.getFrom().getLocation1()
													.getY()
											+ ","
											+ "z="
											+ gate.getFrom().getLocation1()
													.getZ() + "]");
									player.sendMessage("��2���ͳ�����2: "
											+ "["
											+ "����="
											+ gate.getFrom().getLocation2()
													.getWorld().getName()
											+ ","
											+ "x="
											+ gate.getFrom().getLocation2()
													.getX()
											+ ","
											+ "y="
											+ gate.getFrom().getLocation2()
													.getY()
											+ ","
											+ "z="
											+ gate.getFrom().getLocation2()
													.getZ() + "]");
									player.sendMessage("��2����Ŀ�ĵ�: "
											+ (gate.getTo() == null ? "��δ����"
													: "["
															+ "����="
															+ gate.getTo()
																	.getWorld()
																	.getName()
															+ ","
															+ "x="
															+ gate.getTo()
																	.getX()
															+ ","
															+ "y="
															+ gate.getTo()
																	.getY()
															+ ","
															+ "z="
															+ gate.getTo()
																	.getZ()
															+ "]"));
									player.sendMessage("��2���ʹ�������: ");
									for (String c : gate.getCommands()) {
										player.sendMessage("- " + c);
									}
									player.sendMessage("��2=====================================");
									return true;
								} else {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�����Ų�����!");
									return false;
								}
							} else {
								for (PortalGate gate : TribeCraft
										.getPortalGateManager()
										.getPortalGates()) {
									if (Area.isAreaContainLocation(
											gate.getFrom(),
											player.getLocation())) {
										player.sendMessage("��2=====================================");
										player.sendMessage("��2����������: "
												+ gate.getName());
										player.sendMessage("��2��������: "
												+ gate.getType().name());
										player.sendMessage("��2������ʾ: "
												+ gate.getMessage());
										player.sendMessage("��2���ͳ�����1: "
												+ "["
												+ "����="
												+ gate.getFrom().getLocation1()
														.getWorld().getName()
												+ ","
												+ "x="
												+ gate.getFrom().getLocation1()
														.getX()
												+ ","
												+ "y="
												+ gate.getFrom().getLocation1()
														.getY()
												+ ","
												+ "z="
												+ gate.getFrom().getLocation1()
														.getZ() + "]");
										player.sendMessage("��2���ͳ�����2: "
												+ "["
												+ "����="
												+ gate.getFrom().getLocation2()
														.getWorld().getName()
												+ ","
												+ "x="
												+ gate.getFrom().getLocation2()
														.getX()
												+ ","
												+ "y="
												+ gate.getFrom().getLocation2()
														.getY()
												+ ","
												+ "z="
												+ gate.getFrom().getLocation2()
														.getZ() + "]");
										player.sendMessage("��2����Ŀ�ĵ�: "
												+ (gate.getTo() == null ? "��δ����"
														: "["
																+ "����="
																+ gate.getTo()
																		.getWorld()
																		.getName()
																+ ","
																+ "x="
																+ gate.getTo()
																		.getX()
																+ ","
																+ "y="
																+ gate.getTo()
																		.getY()
																+ ","
																+ "z="
																+ gate.getTo()
																		.getZ()
																+ "]"));
										player.sendMessage("��2���ʹ�������: ");
										for (String c : gate.getCommands()) {
											player.sendMessage("- " + c);
										}
										player.sendMessage("��2=====================================");
										return true;
									}
								}

								player.sendMessage("��6/triadmin gate check [��������]");
								return false;

							}
						} else if (args[1].equalsIgnoreCase("message")) {
							if (args.length > 3) {
								if (TribeCraft.getPortalGateManager()
										.getPortalGate(args[2]) != null) {
									TribeCraft.getPortalGateManager()
											.getPortalGate(args[2])
											.setMessage(args[3]);
									player.sendMessage("��a"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "��ʾ��Ϣ���óɹ�!");
									return true;
								} else {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�����Ų����ڣ�");
									return false;
								}
							} else {
								player.sendMessage("��6/triadmin gate message [��������] [��ʾ��Ϣ]");
								return false;
							}
						} else if (args[1].equalsIgnoreCase("command")) {
							if (args.length > 4) {
								if (TribeCraft.getPortalGateManager()
										.getPortalGate(args[2]) != null) {
									if (args[3].equalsIgnoreCase("add")) {
										List<String> commands = TribeCraft
												.getPortalGateManager()
												.getPortalGate(args[2])
												.getCommands();
										StringBuilder sb = new StringBuilder();

										for (int i = 4; i < args.length; i++) {
											sb.append(args[i]).append(" ");
										}

										if (sb.length() > 0) {
											sb.deleteCharAt(sb.length() - 1);
										}

										commands.add(sb.toString());

										TribeCraft.getPortalGateManager()
												.getPortalGate(args[2])
												.setCommands(commands);
										player.sendMessage("��a"
												+ com.HotFlow.TribeCraft.TribeCraft.prefix
												+ "�ɹ��������!");
										return true;
									} else if (args[3]
											.equalsIgnoreCase("remove")) {
										List<String> commands = TribeCraft
												.getPortalGateManager()
												.getPortalGate(args[2])
												.getCommands();

										StringBuilder sb = new StringBuilder();

										for (int i = 4; i < args.length; i++) {
											sb.append(args[i]).append(" ");
										}

										if (sb.length() > 0) {
											sb.deleteCharAt(sb.length() - 1);
										}

										if (commands.remove(sb.toString())) {
											player.sendMessage("��a"
													+ com.HotFlow.TribeCraft.TribeCraft.prefix
													+ "�ɹ�ɾ������!");
										} else {
											player.sendMessage("��4"
													+ com.HotFlow.TribeCraft.TribeCraft.prefix
													+ "�������!");
										}

										TribeCraft.getPortalGateManager()
												.getPortalGate(args[2])
												.setCommands(commands);
										return true;
									} else {
										player.sendMessage("��6/triadmin gate command [��������] [add/remove] [����]");
										return false;
									}
								} else {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�����Ų�����!");
									return false;
								}
							} else {
								player.sendMessage("��6/triadmin gate command [��������] [add/remove] [����]");
								return false;
							}
						} else if (args[1].equalsIgnoreCase("location")) {
							if (args.length > 2) {
								if (TribeCraft.getPortalGateManager()
										.getPortalGate(args[2]) != null) {
									TribeCraft.getPortalGateManager()
											.getPortalGate(args[2])
											.setTo(player.getLocation());
									player.sendMessage("��a"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�ɹ����ô��͵�!");
									return true;
								} else {
									player.sendMessage("��4"
											+ com.HotFlow.TribeCraft.TribeCraft.prefix
											+ "�����Ų�����!");
									return false;
								}
							} else {
								player.sendMessage("��6/triadmin gate location [��������]");
								return false;
							}
						} else {
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate create" + ChatColor.WHITE
									+ ": " + "����һ��������.");
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate remove" + ChatColor.WHITE
									+ ": " + "�Ƴ�һ��������.");
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate list" + ChatColor.WHITE
									+ ": " + "�г�����������.");
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate check" + ChatColor.WHITE
									+ ": " + "�鿴��������Ϣ.");
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate message"
									+ ChatColor.WHITE + ": " + "������ʾ��Ϣ.");
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate command"
									+ ChatColor.WHITE + ": " + "���ô�������.");
							player.sendMessage(ChatColor.GOLD
									+ "/triadmin gate location"
									+ ChatColor.WHITE + ": " + "����Ϊ���͵�.");
							return false;
						}
					} else {
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate create" + ChatColor.WHITE
								+ ": " + "����һ��������.");
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate remove" + ChatColor.WHITE
								+ ": " + "�Ƴ�һ��������.");
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate list" + ChatColor.WHITE
								+ ": " + "�г�����������.");
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate check" + ChatColor.WHITE
								+ ": " + "�鿴��������Ϣ.");
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate message" + ChatColor.WHITE
								+ ": " + "������ʾ��Ϣ.");
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate command" + ChatColor.WHITE
								+ ": " + "���ô�������.");
						player.sendMessage(ChatColor.GOLD
								+ "/triadmin gate location" + ChatColor.WHITE
								+ ": " + "����Ϊ���͵�.");
						return false;
					}
				} else {
					player.sendMessage(ChatColor.GOLD + "/tribeadmin gate"
							+ ChatColor.WHITE + ": ������ָ��.");
					return false;
				}
			} else {
				player.sendMessage(ChatColor.GOLD + "/tribeadmin gate"
						+ ChatColor.WHITE + ": ������ָ��.");
				return false;
			}
		} else {
			TribeCraft.logger.info("������ֻ������ҷ���!");
			return false;
		}
	}

}
