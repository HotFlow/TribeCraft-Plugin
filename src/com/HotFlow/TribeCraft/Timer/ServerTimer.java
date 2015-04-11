package com.HotFlow.TribeCraft.Timer;

import com.HotFlow.TribeCraft.TribeCraft;

/**
 * @author HotFlow
 */
public final class ServerTimer {
	private final TimerTask task;
	private int time;

	public ServerTimer() {
		this.task = new TimerTask(TribeCraft.plugin, this);
		this.time = 0;
	}

	/**
	 * ��ȡ��Ϸʱ��
	 * 
	 * @return
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * ������Ϸʱ��
	 * 
	 * @param second
	 */
	public void setTime(int second) {
		this.time = second;
	}

	/**
	 * ��ȡʱ��ִ����
	 * 
	 * @return
	 */
	public TimerTask getTimerTask() {
		return this.task;
	}

}
