package com.HotFlow.TribeCraft.Player.VIP;

import com.HotFlow.TribeCraft.TribeCraft;

/**
 * @author HotFlow
 */
public class VIP1 implements VIP
{
    public int getLevel()
    {
        return 1;
    }

    public double getItemDropChance()
    {
        return TribeCraft.config.getDouble("ȫ������.��������." + this.getClass().getName() + ".��Ʒ�������");
    }

    public double getArmorDropChance()
    {
        return TribeCraft.config.getDouble("ȫ������.��������." + this.getClass().getName() + ".װ���������");
    }

    public double getExpDropPercentage()
    {
        return TribeCraft.config.getDouble("ȫ������.��������." + this.getClass().getName() + ".�������ٷֱ�");
    }
}
