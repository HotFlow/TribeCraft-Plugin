package com.HotFlow.TribeCraft.Player.Extension;

/**
 * @author Jerry
 */
public class Appointment 
{
    private int time;
    
    public Appointment(int time)
    {
        this.time = time;
    }
    
    /**
     * ��ȡʱ��
     * @return 
     */
    public int getTime()
    {
        return this.time;
    }
    
    /**
     * ����ʱ��
     * @param time
     */
    public void setTime(int time)
    {
        this.time = time;
    }
}
