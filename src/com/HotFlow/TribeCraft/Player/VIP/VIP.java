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
}
