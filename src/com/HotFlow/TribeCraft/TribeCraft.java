package com.HotFlow.TribeCraft;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.HotFlow.TribeCraft.Configuration.TribeConfiguration;
import com.HotFlow.TribeCraft.Configuration.Writter;
import com.HotFlow.TribeCraft.Manager.PlayerManager;
import com.HotFlow.TribeCraft.Manager.PortalGateManager;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.World.Area;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;

/**
 * @author HotFlow
 * @author a404510
 */
public class TribeCraft extends JavaPlugin {
	public static TribeCraft plugin;
	public static File dataFile;
	public static TribeConfiguration data = new TribeConfiguration();
	private static final PlayerManager playerManager = new PlayerManager();
	private static final PortalGateManager portalGateManager = new PortalGateManager();
	public static final Logger logger = Logger.getLogger("HotFlow");
	public static final String prefix = "[���岿��]";
	public static Residence residence;
	private static ResidenceManager residenceManager;
	private static Economy economyManager;
	private static Permission permissionManager;
	private static Chat chatManager;

	@Override
	public void onEnable() {
		TribeCraft.plugin = this;
		TribeCraft.dataFile = new File(getDataFolder(), "data.yml");

		this.loadData();

		if (TribeCraft.setupResidence()) {
			TribeCraft.logger.log(Level.INFO, prefix + " ���ϵͳ��װ�ɹ�!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " ���ϵͳ��װʧ�ܳɹ�!");
		}

		if (TribeCraft.setupEconomy()) {
			TribeCraft.logger.log(Level.INFO, prefix + " ����ϵͳ��װ�ɹ�!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " ����ϵͳ��װʧ�ܳɹ�!");
		}

		if (TribeCraft.setupPermission()) {
			TribeCraft.logger.log(Level.INFO, prefix + " Ȩ��ϵͳ��װ�ɹ�!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " Ȩ��ϵͳ��װʧ�ܳɹ�!");
		}

		if (TribeCraft.setupChat()) {
			TribeCraft.logger.log(Level.INFO, prefix + " Ƶ��ϵͳ��װ�ɹ�!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " Ƶ��ϵͳ��װʧ�ܳɹ�!");
		}

		getServer().getPluginManager().addPermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().admin);
		getServer().getPluginManager().addPermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().user);
		getCommand("Tribe").setExecutor(
				new com.HotFlow.TribeCraft.CommandExecutor.UserExecutor());
		getCommand("TribeAdmin").setExecutor(
				new com.HotFlow.TribeCraft.CommandExecutor.AdminExecutor());

		getServer().getPluginManager().registerEvents(
				new com.HotFlow.TribeCraft.Listener.Listeners(), this);
	}

	@Override
	public void onDisable() {
		getServer().getPluginManager().removePermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().admin);
		getServer().getPluginManager().removePermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().user);
		this.saveData();
	}

	/**
	 * ��ȡ��ҹ�������
	 * 
	 * @return
	 */
	public static PlayerManager getPlayerManager() {
		return TribeCraft.playerManager;
	}

	/**
	 * ��ȡ�����Ź�������
	 * 
	 * @return
	 */
	public static PortalGateManager getPortalGateManager() {
		return TribeCraft.portalGateManager;
	}

	/**
	 * ��ȡ��ع�������
	 * 
	 * @return
	 */
	public static ResidenceManager getResidenceManager() {
		return TribeCraft.residenceManager;
	}

	/**
	 * ��ȡ���ù�������
	 * 
	 * @return
	 */
	public static Economy getEconomyManager() {
		return TribeCraft.economyManager;
	}

	/**
	 * ��ȡȨ�޹�������
	 * 
	 * @return
	 */
	public static Permission getPermissionManager() {
		return TribeCraft.permissionManager;
	}

	/**
	 * ��ȡƵ����������
	 * 
	 * @return
	 */
	public static Chat getChatManager() {
		return TribeCraft.chatManager;
	}

	/**
	 * ��������
	 */
	private void loadData() {
		if (!TribeCraft.dataFile.exists()) {
			try {
				TribeCraft.dataFile.getParentFile().mkdir();
				TribeCraft.dataFile.createNewFile();
				Writter writter = new Writter(TribeCraft.dataFile);
				writter.genData();
			} catch (IOException ex) {
				TribeCraft.logger.info(ex.toString());
			}
		}

		try {
			TribeCraft.data.load(TribeCraft.dataFile);
		} catch (IOException ex) {
			TribeCraft.logger.info(ex.toString());
		} catch (InvalidConfigurationException ex) {
			Logger.getLogger(TribeCraft.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		ConfigurationSection gateSections = data
				.getConfigurationSection("�������б�");

		if (gateSections != null) {
			Set<String> keys = gateSections.getKeys(false);

			if (keys != null) {
				for (String key : keys) {
					PortalGate gate = new PortalGate(key);

					String message = TribeCraft.data.getString("�������б�." + key
							+ ".��ʾ��Ϣ");

					gate.setMessage(message);
					gate.setCommands(TribeCraft.data.getStringList("�������б�."
							+ key + ".��������"));

					Area area = new Area(
							new Location(getServer().getWorld(
									TribeCraft.data.getString("�������б�." + key
											+ ".���ͳ�����.����")),
									Double.parseDouble(TribeCraft.data
											.getString(
													"�������б�." + key
															+ ".���ͳ�����.�����1")
											.split(",")[0]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"�������б�." + key
															+ ".���ͳ�����.�����1")
											.split(",")[1]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"�������б�." + key
															+ ".���ͳ�����.�����1")
											.split(",")[2])), new Location(
									getServer().getWorld(
											TribeCraft.data.getString("�������б�."
													+ key + ".���ͳ�����.����")),
									Double.parseDouble(TribeCraft.data
											.getString(
													"�������б�." + key
															+ ".���ͳ�����.�����2")
											.split(",")[0]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"�������б�." + key
															+ ".���ͳ�����.�����2")
											.split(",")[1]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"�������б�." + key
															+ ".���ͳ�����.�����2")
											.split(",")[2])));

					gate.setFrom(area);

					if (TribeCraft.data.getString("�������б�." + key + ".��������")
							.equalsIgnoreCase("���͵�")) {
						gate.setType(PortalGateType.Location);
					} else if (TribeCraft.data.getString(
							"�������б�." + key + ".��������").equalsIgnoreCase("�����")) {
						gate.setType(PortalGateType.Random);
					} else {
						gate.setType(PortalGateType.Command);
					}

					Location location = new Location(
							getServer().getWorld(
									TribeCraft.data.getString("�������б�." + key
											+ ".���͵����.����")),
							Double.parseDouble(TribeCraft.data.getString(
									"�������б�." + key + ".���͵����.�����").split(",")[0]),
							Double.parseDouble(TribeCraft.data.getString(
									"�������б�." + key + ".���͵����.�����").split(",")[1]),
							Double.parseDouble(TribeCraft.data.getString(
									"�������б�." + key + ".���͵����.�����").split(",")[2]));

					gate.setTo(location);

					TribeCraft.portalGateManager.addPortalGate(gate);
				}
			}
		}
	}

	/**
	 * ��������
	 */
	private void saveData() {
		for (PortalGate gate : TribeCraft.getPortalGateManager()
				.getPortalGates()) {
			if (gate.getTo() != null) {
				TribeCraft.data.set("�������б�." + gate.getName() + ".��ʾ��Ϣ",
						gate.getMessage());
				TribeCraft.data.set("�������б�." + gate.getName() + ".��������",
						gate.getCommands());
				TribeCraft.data.set("�������б�." + gate.getName() + ".���ͳ�����.����",
						gate.getFrom().getLocation1().getWorld().getName());
				TribeCraft.data.set("�������б�." + gate.getName() + ".���ͳ�����.�����1",
						gate.getFrom().getLocation1().getX() + ","
								+ gate.getFrom().getLocation1().getY() + ","
								+ gate.getFrom().getLocation1().getZ());
				TribeCraft.data.set("�������б�." + gate.getName() + ".���ͳ�����.�����2",
						gate.getFrom().getLocation2().getX() + ","
								+ gate.getFrom().getLocation2().getY() + ","
								+ gate.getFrom().getLocation2().getZ());
				TribeCraft.data.set("�������б�." + gate.getName() + ".���͵����.����",
						gate.getTo().getWorld().getName());
				TribeCraft.data.set("�������б�." + gate.getName() + ".���͵����.�����",
						gate.getTo().getX() + "," + gate.getTo().getY() + ","
								+ gate.getTo().getZ());

				if (gate.getType().equals(PortalGateType.Location)) {
					TribeCraft.data.set("�������б�." + gate.getName() + ".��������",
							"���͵�");
				} else if (gate.getType().equals(PortalGateType.Random)) {
					TribeCraft.data.set("�������б�." + gate.getName() + ".��������",
							"�����");
				} else {
					TribeCraft.data.set("�������б�." + gate.getName() + ".��������",
							"�����");
				}
			} else {
				TribeCraft.logger.log(Level.SEVERE, TribeCraft.prefix + "������"
						+ gate.getName() + "û�д��͵㣬�����档");
			}
		}

		try {
			TribeCraft.data.save(dataFile);
		} catch (IOException ex) {
			TribeCraft.logger.info(ex.toString());
		}
	}

	/**
	 * ��װ���ϵͳ
	 */
	private static Boolean setupResidence() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Residence") == null) {
			return false;
		}

		TribeCraft.residence = (Residence) Bukkit.getPluginManager().getPlugin(
				"Residence");

		TribeCraft.residenceManager = Residence.getResidenceManager();

		if (TribeCraft.residenceManager == null) {
			return false;
		}

		if (!TribeCraft.residence.isEnabled()) {
			return false;
		}

		return (TribeCraft.residence != null);
	}

	/**
	 * ��װ����ϵͳ
	 * 
	 * @return
	 */
	public static Boolean setupEconomy() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider economyProvider = Bukkit.getServer()
				.getServicesManager().getRegistration(Economy.class);

		if (economyProvider != null) {
			TribeCraft.economyManager = (Economy) economyProvider.getProvider();
		}

		return TribeCraft.economyManager != null;
	}

	/**
	 * ��װȨ��ϵͳ
	 * 
	 * @return
	 */
	public static Boolean setupPermission() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider permissionProvider = Bukkit.getServer()
				.getServicesManager().getRegistration(Permission.class);

		if (permissionProvider != null) {
			TribeCraft.permissionManager = (Permission) permissionProvider
					.getProvider();
		}

		return TribeCraft.permissionManager != null;
	}

	/**
	 * ��װƵ��ϵͳ
	 * 
	 * @return
	 */
	public static Boolean setupChat() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider chatProvider = Bukkit.getServer()
				.getServicesManager().getRegistration(Chat.class);

		if (chatProvider != null) {
			TribeCraft.chatManager = (Chat) chatProvider.getProvider();
		}

		return TribeCraft.chatManager != null;
	}
}
