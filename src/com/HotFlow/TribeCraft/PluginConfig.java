package com.HotFlow.TribeCraft;

import com.HotFlow.TribeCraft.Player.VIP.VIP;
import com.HotFlow.TribeCraft.Player.VIP.VIP0;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author HotFlow
 */
public class PluginConfig
{

    private final ServerConfig serverConfig;
    private final CommandsInfo commandsInfo;
    private final VIPInfo vipInfo;

    public PluginConfig()
    {
        this.serverConfig = new ServerConfig();
        this.commandsInfo = new CommandsInfo();
        this.vipInfo = new VIPInfo();
    }

    /**
     * ��ȡ����������
     *
     * @return
     */
    public ServerConfig getServerConfig()
    {
        return this.serverConfig;
    }

    /**
     * ��ȡָ����Ϣ
     *
     * @return
     */
    public CommandsInfo getCommandsInfo()
    {
        return this.commandsInfo;
    }

    /**
     * ��ȡVIP��Ϣ
     *
     * @return
     */
    public VIPInfo getVIPInfo()
    {
        return this.vipInfo;
    }

    public class ServerConfig
    {
        private final DispenserItemBansConfiguration dispenserItemBans;
        private final NetherPortalEntityBansConfiguration netherPortalEntityBans;
        private final Boolean blockCantFloating;
        private final Boolean cleanRedstoneClock;
        private final HeightWaterRemovesConfiguration heightWaterRemoves;
        private final HeightLavaRemovesConfiguration heightLavaRemoves;

        public ServerConfig()
        {

            List<Integer> itemIDs = new ArrayList<Integer>();

            for (Integer itemID : Main.config.getIntegerList("ȫ������.����������.��ֹ��������Ʒ�б�"))
            {
                itemIDs.add(itemID);
            }

            this.dispenserItemBans = new DispenserItemBansConfiguration(itemIDs);

            List<String> entityNames = new ArrayList<String>();

            for (String entityName : Main.config.getStringList("ȫ������.����������.��ֹ����ͨ���������б�"))
            {
                entityNames.add(entityName);
            }

            this.netherPortalEntityBans = new NetherPortalEntityBansConfiguration(entityNames);

            this.blockCantFloating = Main.config.getBoolean("ȫ������.����������.��ֹ���շ���.����");

            this.cleanRedstoneClock = Main.config.getBoolean("ȫ������.����������.�����Ƶ��ʯ.����");

            this.heightWaterRemoves = new HeightWaterRemovesConfiguration(Main.config.getBoolean("ȫ������.����������.����߿���ˮ.����"),
                    Main.config.getInt("ȫ������.����������.����߿���ˮ.Դ�����߶�"),
                    Main.config.getInt("ȫ������.����������.����߿���ˮ.��ˮ������������")
            );

            this.heightLavaRemoves = new HeightLavaRemovesConfiguration(Main.config.getBoolean("ȫ������.����������.����߿��ҽ�.����"),
                    Main.config.getInt("ȫ������.����������.����߿��ҽ�.Դ�����߶�"),
                    Main.config.getInt("ȫ������.����������.����߿��ҽ�.�ҽ�������������")
            );

        }

        /**
         * ��ȡ��ֹ��������Ʒ�б�
         *
         * @return
         */
        public DispenserItemBansConfiguration getDispenserItemBans()
        {
            return this.dispenserItemBans;
        }

        /**
         * ��ȡ��ֹ����ͨ���������б�
         *
         * @return
         */
        public NetherPortalEntityBansConfiguration getNetherPortalEntityBans()
        {
            return this.netherPortalEntityBans;
        }

        /**
         * �Ƿ��ֹ���շ���
         *
         * @return
         */
        public Boolean isBlockCantFloating()
        {
            return this.blockCantFloating;
        }

        /**
         * �Ƿ������Ƶ��ʯ
         *
         * @return
         */
        public Boolean isCleanRedstoneClock()
        {
            return this.cleanRedstoneClock;
        }

        /**
         * ��ȡ����߿���ˮ
         *
         * @return
         */
        public HeightWaterRemovesConfiguration getHeightWaterRemoves()
        {
            return this.heightWaterRemoves;
        }

        public class PermissionDetectorConfigraution
        {

            private final OPDetector opDetector;
            private final CreativeDetector creativeDetector;

            public PermissionDetectorConfigraution(OPDetector opDetector, CreativeDetector creativeDetector)
            {
                this.opDetector = opDetector;
                this.creativeDetector = creativeDetector;
            }

            /**
             * ��ȡOP���
             *
             * @return
             */
            public OPDetector getOPDetector()
            {
                return this.opDetector;
            }

            /**
             * ��ȡ������
             *
             * @return
             */
            public CreativeDetector getCreativeDetector()
            {
                return this.creativeDetector;
            }

            public class OPDetector
            {

                public final Boolean enable;
                public final List<String> whiteList = new ArrayList<String>();

                public OPDetector(Boolean enable, List<String> whiteList)
                {
                    this.enable = enable;

                    for (String name : whiteList)
                    {
                        this.whiteList.add(name);
                    }
                }
            }

            public class CreativeDetector
            {

                public final Boolean enable;
                public final List<String> whiteList = new ArrayList<String>();

                public CreativeDetector(Boolean enable, List<String> whiteList)
                {
                    this.enable = enable;

                    for (String name : whiteList)
                    {
                        this.whiteList.add(name);
                    }
                }
            }
        }

        public class DispenserItemBansConfiguration
        {

            public final List<Integer> itemIDs = new ArrayList<Integer>();

            public DispenserItemBansConfiguration(List<Integer> itemIDs)
            {
                for (Integer itemID : itemIDs)
                {
                    this.itemIDs.add(itemID);
                }
            }
        }

        public class NetherPortalEntityBansConfiguration
        {

            public final List<String> entityNames = new ArrayList<String>();

            public NetherPortalEntityBansConfiguration(List<String> entityNames)
            {
                for (String entityName : entityNames)
                {
                    this.entityNames.add(entityName);
                }
            }
        }

        public class HeightWaterRemovesConfiguration
        {

            public final Boolean enable;
            public final int height;
            public final int flowRange;

            public HeightWaterRemovesConfiguration(Boolean enable, int height, int flowRange)
            {
                this.enable = enable;
                this.height = height;
                this.flowRange = flowRange;
            }
        }

        public class HeightLavaRemovesConfiguration
        {

            public final Boolean enable;
            public final int height;
            public final int flowRange;

            public HeightLavaRemovesConfiguration(Boolean enable, int height, int flowRange)
            {
                this.enable = enable;
                this.height = height;
                this.flowRange = flowRange;
            }
        }

    }

    public class CommandsInfo
    {
        private final SurvivalConfiguration survival;

        public CommandsInfo()
        {
            this.survival = new SurvivalConfiguration(
                    Main.config.getBoolean("ȫ������.�û�ָ��.Survival.����"),
                    Main.config.getString("ȫ������.�û�ָ��.Survival.�������"),
                    Main.config.getString("ȫ������.�û�ָ��.Survival.�����"),
                    Main.config.getBoolean("ȫ������.�û�ָ��.Survival.Ŀ�ĵؿ�Ϊ���"),
                    Main.config.getInt("ȫ������.�û�ָ��.Survival.������X"),
                    Main.config.getInt("ȫ������.�û�ָ��.Survival.������Y"),
                    Main.config.getInt("ȫ������.�û�ָ��.Survival.������Z"));
        }

        /**
         * ��ȡsurvivalָ�����Ϣ
         *
         * @return
         */
        public SurvivalConfiguration getSurvival()
        {
            return this.survival;
        }

        public class SurvivalConfiguration
        {

            public final Boolean enable;
            public final String mainTown;
            public final String subArea;
            public final Boolean canInResidence;
            public final int maxX;
            public final int maxY;
            public final int maxZ;

            public SurvivalConfiguration(Boolean enable, String mainTown, String subArea, Boolean canInResidence, int maxX, int maxY, int maxZ)
            {
                this.enable = enable;
                this.mainTown = mainTown;
                this.subArea = subArea;
                this.canInResidence = canInResidence;
                this.maxX = maxX;
                this.maxY = maxY;
                this.maxZ = maxZ;
            }
        }
    }

    public class VIPInfo
    {
        private List<VIP> vips = new ArrayList<VIP>();

        public VIPInfo()
        {
            ConfigurationSection sections = Main.config.getConfigurationSection("ȫ������.��������");

            if (sections == null)
            {
                return;
            }

            Set<String> keys = sections.getKeys(false);

            if (keys == null)
            {
                return;
            }

            for (String key : keys)
            {
                try
                {
                    if(key.equals("��ͨ�û�"))
                    {
                        key = "VIP0";
                    }
                    
                    VIP vip = (VIP) Class.forName("com.HotFlow.TribeCraft.Player.VIP." + key).newInstance();
                    
                    if(key.equals("VIP0"))
                    {
                        key = "��ͨ�û�";
                    }
                    
                    vip.setItemDropChance(Main.config.getDouble("ȫ������.��������." + key + ".��Ʒ�������"));
                    vip.setArmorDropChance(Main.config.getDouble("ȫ������.��������." + key + ".װ���������"));
                    vip.setExpDropPercentage(Main.config.getDouble("ȫ������.��������." + key + ".�������ٷֱ�"));
                    vips.add(vip);
                }
                catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(PluginConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (InstantiationException ex)
                {
                    Logger.getLogger(PluginConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IllegalAccessException ex)
                {
                    Logger.getLogger(PluginConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public VIP getVIP(int level)
        {
            for (VIP vip : this.vips)
            {
                if (vip.getLevel() == level)
                {
                    return vip;
                }
            }

            return null;
        }
    }
}
