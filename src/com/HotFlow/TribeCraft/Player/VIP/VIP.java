package com.HotFlow.TribeCraft.Player.VIP;

/**
 * @author HotFlow
 */
public interface VIP
{
    /**
     * ��ȡVIP�ȼ�
     *
     * @return
     */
    public int getLevel();

    /**
     * ��ȡ��Ʒ�������
     *
     * @return
     */
    public double getItemDropChance();

    /**
     * ��ȡװ���������
     *
     * @return
     */
    public double getArmorDropChance();

    /**
     * ��ȡ����������
     *
     * @return
     */
    public double getExpDropPercentage();

    /**
     * ������Ʒ�������
     *
     * @param chance
     */
    public void setItemDropChance(double chance);

    /**
     * ����װ���������
     *
     * @param chance
     */
    public void setArmorDropChance(double chance);

    /**
     * ���þ���������
     *
     * @param chance
     */
    public void setExpDropPercentage(double chance);
}
