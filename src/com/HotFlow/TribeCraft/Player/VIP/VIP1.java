package com.HotFlow.TribeCraft.Player.VIP;

/**
 * @author HotFlow
 */
public class VIP1 extends VIP0
{
    private double itemDropChance;
    private double armorDropChance;
    private double expDropPercentage;
    
    public int getLevel()
    {
        return 1;
    }

    public double getItemDropChance()
    {
        return this.itemDropChance;
    }

    public double getArmorDropChance()
    {
        return this.armorDropChance;
    }

    public double getExpDropPercentage()
    {
        return this.expDropPercentage;
    }

    public void setItemDropChance(double chance)
    {
        this.itemDropChance = chance;
    }

    public void setArmorDropChance(double chance)
    {
        this.armorDropChance = chance;
    }

    public void setExpDropPercentage(double percentage)
    {
        this.expDropPercentage = percentage;
    }
}
