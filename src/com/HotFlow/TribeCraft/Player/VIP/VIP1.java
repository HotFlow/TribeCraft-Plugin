package com.HotFlow.TribeCraft.Player.VIP;

import com.HotFlow.TribeCraft.Main;

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
        return Main.config.getDouble("ȫ������.��������." + this.getClass().getName() + ".��Ʒ�������");
    }

    public double getArmorDropChance()
    {
        return Main.config.getDouble("ȫ������.��������." + this.getClass().getName() + ".װ���������");
    }

    public double getExpDropPercentage()
    {
        return Main.config.getDouble("ȫ������.��������." + this.getClass().getName() + ".�������ٷֱ�");
    }
}
