package com.HotFlow.TribeCraft.Player.VIP;

/**
 * @author HotFlow
 */
public interface VIP {
	/**
	 * ��ȡVIP�ȼ�
	 * 
	 * @return
	 */
	public int getLevel();

	/**
	 * �Ƿ���Է���
	 * 
	 * @return
	 */
	public Boolean canFly();

	/**
	 * ��ȡ�������
	 * 
	 * @return
	 */
	public double getChanceOfDrops();
}
