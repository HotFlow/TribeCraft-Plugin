package com.HotFlow.TribeCraft.Manager;

import com.HotFlow.TribeCraft.TribeCraft;

/**
 * @author HotFlow
 */
public class PluginManager 
{
    private final SurvivalConfiguration survivalConfig;
    
    public PluginManager()
    {
        this.survivalConfig = new SurvivalConfiguration(TribeCraft.config.getBoolean("ȫ������.�û�ָ��.Survival.����"),
                                                TribeCraft.config.getString("ȫ������.�û�ָ��.Survival.�������"),
                                                TribeCraft.config.getString("ȫ������.�û�ָ��.Survival.�����"),
                                                TribeCraft.config.getBoolean("ȫ������.�û�ָ��.Survival.Ŀ�ĵؿ�Ϊ���"),
                                                TribeCraft.config.getInt("ȫ������.�û�ָ��.Survival.������X"),
                                                TribeCraft.config.getInt("ȫ������.�û�ָ��.Survival.������Y"),
                                                TribeCraft.config.getInt("ȫ������.�û�ָ��.Survival.������Z"));
    }
    
    /**
     * ��ȡsurvivalָ�����Ϣ
     * @return 
     */
    public SurvivalConfiguration getSurvivalProperties()
    {
        return this.survivalConfig;
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
        
        public SurvivalConfiguration(Boolean enable,String mainTown,String subArea,Boolean canInResidence,int maxX,int maxY,int maxZ)
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
