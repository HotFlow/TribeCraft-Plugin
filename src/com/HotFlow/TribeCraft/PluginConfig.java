package com.HotFlow.TribeCraft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HotFlow
 */
public class PluginConfig
{
    private final ServerConfig serverConfig;
    private final CommandsInfo commandsInfo;

    public PluginConfig()
    {
        this.serverConfig = new ServerConfig();
        this.commandsInfo = new CommandsInfo();
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

    private class ServerConfig
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

            for (Integer itemID : TribeCraft.config.getIntegerList("ȫ������.����������.��ֹ��������Ʒ�б�"))
            {
                itemIDs.add(itemID);
            }

            this.dispenserItemBans = new DispenserItemBansConfiguration(itemIDs);

            List<String> entityNames = new ArrayList<String>();

            for (String entityName : TribeCraft.config.getStringList("ȫ������.����������.��ֹ����ͨ���������б�"))
            {
                entityNames.add(entityName);
            }

            this.netherPortalEntityBans = new NetherPortalEntityBansConfiguration(entityNames);

            this.blockCantFloating = TribeCraft.config.getBoolean("ȫ������.����������.��ֹ���շ���.����");

            this.cleanRedstoneClock = TribeCraft.config.getBoolean("ȫ������.����������.������Ƶ��ʯ.����");

            this.heightWaterRemoves = new HeightWaterRemovesConfiguration(TribeCraft.config.getBoolean("ȫ������.����������.�����߿���ˮ.����"),
                    TribeCraft.config.getInt("ȫ������.����������.�����߿���ˮ.Դ�����߶�"),
                    TribeCraft.config.getInt("ȫ������.����������.�����߿���ˮ.��ˮ������������")
            );

            this.heightLavaRemoves = new HeightLavaRemovesConfiguration(TribeCraft.config.getBoolean("ȫ������.����������.�����߿��ҽ�.����"),
                    TribeCraft.config.getInt("ȫ������.����������.�����߿��ҽ�.Դ�����߶�"),
                    TribeCraft.config.getInt("ȫ������.����������.�����߿��ҽ�.�ҽ�������������")
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
         * �Ƿ�������Ƶ��ʯ
         *
         * @return
         */
        public Boolean isCleanRedstoneClock()
        {
            return this.cleanRedstoneClock;
        }

        /**
         * ��ȡ�����߿���ˮ
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
                    TribeCraft.config.getBoolean("ȫ������.�û�ָ��.Survival.����"),
                    TribeCraft.config.getString("ȫ������.�û�ָ��.Survival.�������"),
                    TribeCraft.config.getString("ȫ������.�û�ָ��.Survival.�����"),
                    TribeCraft.config.getBoolean("ȫ������.�û�ָ��.Survival.Ŀ�ĵؿ�Ϊ���"),
                    TribeCraft.config.getInt("ȫ������.�û�ָ��.Survival.������X"),
                    TribeCraft.config.getInt("ȫ������.�û�ָ��.Survival.������Y"),
                    TribeCraft.config.getInt("ȫ������.�û�ָ��.Survival.������Z"));
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
}