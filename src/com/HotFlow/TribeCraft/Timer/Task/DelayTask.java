package com.HotFlow.TribeCraft.Timer.Task;

/**
 * @author HotFlow
 */
public abstract class DelayTask
{
    private int time;
    private final String description;

    public DelayTask(int time)
    {
        this.time = time;
        this.description = null;
    }

    public DelayTask(int time, String description)
    {
        this.time = time;
        this.description = description;
    }

    /**
     * ִ�д���
     */
    public void run()
    {
    }

    /**
     * ��ȡʱ��
     *
     * @return
     */
    public int getTime()
    {
        return this.time;
    }

    /**
     * ����ʱ��
     *
     * @param time
     */
    public void setTime(int time)
    {
        this.time = time;
    }

    /**
     * ��ȡ����
     *
     * @return
     */
    public String getDescription()
    {
        return this.description;
    }
}
