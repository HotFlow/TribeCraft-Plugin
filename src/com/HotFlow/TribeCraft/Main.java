package com.HotFlow.TribeCraft;

import com.HotFlow.TribeCraft.Configuration.PluginConfig;
import com.HotFlow.TribeCraft.Configuration.TribeConfiguration;
import com.HotFlow.TribeCraft.Configuration.Writter;
import com.HotFlow.TribeCraft.Manager.DelayTaskManager;
import com.HotFlow.TribeCraft.Manager.PlayerManager;
import com.HotFlow.TribeCraft.Manager.PortalGateManager;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.Timer.ServerTimer;
import com.HotFlow.TribeCraft.World.Area;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author HotFlow
 */
public class Main extends JavaPlugin
{
    public static Main plugin;
    public static File configFile;
    public static File dataFile;
    public final static TribeConfiguration config = new TribeConfiguration();
    public final static TribeConfiguration data = new TribeConfiguration();
    private static final PlayerManager playerManager = new PlayerManager();
    private static final PortalGateManager portalGateManager = new PortalGateManager();
    private static final DelayTaskManager delayTaskManager = new DelayTaskManager();
    private static PluginConfig pluginConfig;
    public static final Logger logger = Logger.getLogger("HotFlow");
    public static final String prefix = "[���岿��]";
    public static Residence residence;
    private static ResidenceManager residenceManager;
    private static Economy economyManager;
    private static Permission permissionManager;
    private static Chat chatManager;
    public static ServerTimer serverTimer;
    public static List<Location> Active_RedStone_List;
    public static final HashMap<Block, Block> Source_Height_Water = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> Source_Height_Lava = new HashMap<Block, Block>();

    @Override
    public void onEnable()
    {
        Main.plugin = this;
        Main.configFile = new File(getDataFolder(), "config.yml");
        Main.dataFile = new File(getDataFolder(), "data.yml");
        Main.serverTimer = new ServerTimer();
        Main.serverTimer.getTimerTask().start();

        this.loadData();
        this.loadConfig();

        if (Main.setupResidence())
        {
            Main.logger.log(Level.INFO, prefix + " ���ϵͳ��װ�ɹ�!");
        }
        else
        {
            Main.logger.log(Level.SEVERE, prefix + " ���ϵͳ��װʧ��!");
        }

        if (Main.setupEconomy())
        {
            Main.logger.log(Level.INFO, prefix + " ����ϵͳ��װ�ɹ�!");
        }
        else
        {
            Main.logger.log(Level.SEVERE, prefix + " ����ϵͳ��װʧ��!");
        }

        if (Main.setupPermission())
        {
            Main.logger.log(Level.INFO, prefix + " Ȩ��ϵͳ��װ�ɹ�!");
        }
        else
        {
            Main.logger.log(Level.SEVERE, prefix + " Ȩ��ϵͳ��װʧ��!");
        }

        if (Main.setupChat())
        {
            Main.logger.log(Level.INFO, prefix + " Ƶ��ϵͳ��װ�ɹ�!");
        }
        else
        {
            Main.logger.log(Level.SEVERE, prefix + " Ƶ��ϵͳ��װʧ��!");
        }

        getServer().getPluginManager().addPermission(new com.HotFlow.TribeCraft.Permissions.Permissions().admin);
        getServer().getPluginManager().addPermission(new com.HotFlow.TribeCraft.Permissions.Permissions().user);
        getCommand("Tribe").setExecutor(new com.HotFlow.TribeCraft.CommandExecutor.UserExecutor());
        getCommand("TribeAdmin").setExecutor(new com.HotFlow.TribeCraft.CommandExecutor.AdminExecutor());
        getServer().getPluginManager().registerEvents(new com.HotFlow.TribeCraft.Listener.Listeners(), this);

        Main.Active_RedStone_List = new ArrayList<Location>();
    }

    @Override
    public void onDisable()
    {
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
    public static PlayerManager getPlayerManager()
    {
        return Main.playerManager;
    }

    /**
     * ��ȡ�����Ź�������
     *
     * @return
     */
    public static PortalGateManager getPortalGateManager()
    {
        return Main.portalGateManager;
    }

    /**
     * ��ȡ��ʱִ������������
     *
     * @return
     */
    public static DelayTaskManager getDelayTaskManager()
    {
        return Main.delayTaskManager;
    }

    /**
     * ��ȡ���������Ϣ
     *
     * @return
     */
    public static PluginConfig getPluginConfig()
    {
        return Main.pluginConfig;
    }

    /**
     * ��ȡ��ع�������
     *
     * @return
     */
    public static ResidenceManager getResidenceManager()
    {
        return Main.residenceManager;
    }

    /**
     * ��ȡ���ù�������
     *
     * @return
     */
    public static Economy getEconomyManager()
    {
        return Main.economyManager;
    }

    /**
     * ��ȡȨ�޹�������
     *
     * @return
     */
    public static Permission getPermissionManager()
    {
        return Main.permissionManager;
    }

    /**
     * ��ȡƵ����������
     *
     * @return
     */
    public static Chat getChatManager()
    {
        return Main.chatManager;
    }

    /**
     * ��������
     */
    private void loadData()
    {
        if (!Main.dataFile.exists())
        {
            try
            {
                Main.dataFile.getParentFile().mkdir();
                Main.dataFile.createNewFile();
                Writter writter = new Writter(Main.dataFile);
                writter.genData();
            }
            catch (IOException ex)
            {
                Main.logger.info(ex.toString());
            }
        }

        try
        {
            Main.data.load(Main.dataFile);
        }
        catch (IOException ex)
        {
            Main.logger.info(ex.toString());
        }
        catch (InvalidConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfigurationSection gateSections = data.getConfigurationSection("�������б�");

        if (gateSections != null)
        {
            Set<String> keys = gateSections.getKeys(false);

            if (keys != null)
            {
                for (String key : keys)
                {
                    PortalGate gate = new PortalGate(key);

                    String message = Main.data.getString("�������б�." + key + ".��ʾ��Ϣ");

                    gate.setMessage(message);
                    gate.setCommands(Main.data.getStringList("�������б�." + key + ".��������"));

                    Area area = new Area(new Location(getServer().getWorld(Main.data.getString("�������б�." + key + ".���ͳ�����.����")),
                            Double.parseDouble(Main.data.getString("�������б�." + key + ".���ͳ�����.�����1").split(",")[0]),
                            Double.parseDouble(Main.data.getString("�������б�." + key + ".���ͳ�����.�����1").split(",")[1]),
                            Double.parseDouble(Main.data.getString("�������б�." + key + ".���ͳ�����.�����1").split(",")[2])),
                            new Location(getServer().getWorld(Main.data.getString("�������б�." + key + ".���ͳ�����.����")),
                                    Double.parseDouble(Main.data.getString("�������б�." + key + ".���ͳ�����.�����2").split(",")[0]),
                                    Double.parseDouble(Main.data.getString("�������б�." + key + ".���ͳ�����.�����2").split(",")[1]),
                                    Double.parseDouble(Main.data.getString("�������б�." + key + ".���ͳ�����.�����2").split(",")[2])));

                    gate.setFrom(area);

                    if (Main.data.getString("�������б�." + key + ".��������").equalsIgnoreCase("���͵�"))
                    {
                        gate.setType(PortalGateType.Location);
                    }
                    else if (Main.data.getString("�������б�." + key + ".��������").equalsIgnoreCase("�����"))
                    {
                        gate.setType(PortalGateType.Random);
                    }
                    else
                    {
                        gate.setType(PortalGateType.Command);
                    }

                    Location location = new Location(getServer().getWorld(Main.data.getString("�������б�." + key + ".���͵����.����")),
                            Double.parseDouble(Main.data.getString("�������б�." + key + ".���͵����.�����").split(",")[0]),
                            Double.parseDouble(Main.data.getString("�������б�." + key + ".���͵����.�����").split(",")[1]),
                            Double.parseDouble(Main.data.getString("�������б�." + key + ".���͵����.�����").split(",")[2]));

                    gate.setTo(location);

                    Main.portalGateManager.addPortalGate(gate);
                }
            }
        }
    }

    /**
     * ��������
     */
    private void saveData()
    {
        for (PortalGate gate : Main.getPortalGateManager().getPortalGates())
        {
            if (gate.getTo() != null)
            {
                Main.data.set("�������б�." + gate.getName() + ".��ʾ��Ϣ", gate.getMessage());
                Main.data.set("�������б�." + gate.getName() + ".��������", gate.getCommands());
                Main.data.set("�������б�." + gate.getName() + ".���ͳ�����.����", gate.getFrom().getLocation1().getWorld().getName());
                Main.data.set("�������б�." + gate.getName() + ".���ͳ�����.�����1", gate.getFrom().getLocation1().getX() + "," + gate.getFrom().getLocation1().getY() + "," + gate.getFrom().getLocation1().getZ());
                Main.data.set("�������б�." + gate.getName() + ".���ͳ�����.�����2", gate.getFrom().getLocation2().getX() + "," + gate.getFrom().getLocation2().getY() + "," + gate.getFrom().getLocation2().getZ());
                Main.data.set("�������б�." + gate.getName() + ".���͵����.����", gate.getTo().getWorld().getName());
                Main.data.set("�������б�." + gate.getName() + ".���͵����.�����", gate.getTo().getX() + "," + gate.getTo().getY() + "," + gate.getTo().getZ());

                if (gate.getType().equals(PortalGateType.Location))
                {
                    Main.data.set("�������б�." + gate.getName() + ".��������", "���͵�");
                }
                else if (gate.getType().equals(PortalGateType.Random))
                {
                    Main.data.set("�������б�." + gate.getName() + ".��������", "�����");
                }
                else
                {
                    Main.data.set("�������б�." + gate.getName() + ".��������", "�����");
                }
            }
            else
            {
                Main.logger.log(Level.SEVERE, Main.prefix + "������" + gate.getName() + "û�д��͵㣬�����档");
            }
        }

        try
        {
            Main.data.save(dataFile);
        }
        catch (IOException ex)
        {
            Main.logger.info(ex.toString());
        }
    }

    /**
     * ��������
     */
    private void loadConfig()
    {
        if (!Main.configFile.exists())
        {
            try
            {
                Main.configFile.getParentFile().mkdir();
                Main.configFile.createNewFile();
                Writter writter = new Writter(Main.configFile);
                writter.genConfig();
            }
            catch (IOException ex)
            {
                Main.logger.info(ex.toString());
            }
        }

        try
        {
            Main.config.load(Main.configFile);
        }
        catch (IOException ex)
        {
            Main.logger.info(ex.toString());
        }
        catch (InvalidConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Main.pluginConfig = new PluginConfig();
    }

    /**
     * ��װ���ϵͳ
     */
    private static Boolean setupResidence()
    {
        if (Bukkit.getServer().getPluginManager().getPlugin("Residence") == null)
        {
            return false;
        }

        Main.residence = (Residence) Bukkit.getPluginManager().getPlugin("Residence");

        Main.residenceManager = Residence.getResidenceManager();

        if (Main.residenceManager == null)
        {
            return false;
        }

        if (!Main.residence.isEnabled())
        {
            return false;
        }

        return (Main.residence != null);
    }

    /**
     * ��װ����ϵͳ
     *
     * @return
     */
    public static Boolean setupEconomy()
    {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }

        RegisteredServiceProvider economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

        if (economyProvider != null)
        {
            Main.economyManager = (Economy) economyProvider.getProvider();
        }

        return Main.economyManager != null;
    }

    /**
     * ��װȨ��ϵͳ
     *
     * @return
     */
    public static Boolean setupPermission()
    {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }

        RegisteredServiceProvider permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);

        if (permissionProvider != null)
        {
            Main.permissionManager = (Permission) permissionProvider.getProvider();
        }

        return Main.permissionManager != null;
    }

    /**
     * ��װƵ��ϵͳ
     *
     * @return
     */
    public static Boolean setupChat()
    {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }

        RegisteredServiceProvider chatProvider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);

        if (chatProvider != null)
        {
            Main.chatManager = (Chat) chatProvider.getProvider();
        }

        return Main.chatManager != null;
    }
}
