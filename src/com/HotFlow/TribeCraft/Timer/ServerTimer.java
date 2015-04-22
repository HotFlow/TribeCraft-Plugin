package com.HotFlow.TribeCraft.Timer;

import com.HotFlow.TribeCraft.Main;

/**
 * @author HotFlow
 */
public final class ServerTimer
{
    private final ServerTimerRunnable task;
    private int time;

    public ServerTimer()
    {
        this.task = new ServerTimerRunnable(Main.plugin, this);
        this.time = 0;
    }

    /**
     * ��ȡ��Ϸʱ��
     *
     * @return
     */
    public int getTime()
    {
        return this.time;
    }

    /**
     * ������Ϸʱ��
     *
     * @param second
     */
    public void setTime(int second)
    {
        this.time = second;
    }

    /**
     * ��ȡʱ��ִ����
     *
     * @return
     */
    public ServerTimerRunnable getTimerTask()
    {
        return this.task;
    }

}
