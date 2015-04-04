package com.HotFlow.TribeCraft.Event.Plugin;

import org.bukkit.event.Cancellable;
import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public class PluginTimeChangeEvent extends PluginEvent implements Cancellable
{
    private int time;
    private Boolean cancelled = false;
    
    public PluginTimeChangeEvent(Plugin plugin,int time) 
    {
        super(plugin);
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

    public boolean isCancelled() 
    {
        return this.cancelled;
    }

    public void setCancelled(boolean bln) 
    {
        this.cancelled = bln;
    }
    
}
