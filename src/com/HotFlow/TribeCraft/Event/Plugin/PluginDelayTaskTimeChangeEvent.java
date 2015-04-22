package com.HotFlow.TribeCraft.Event.Plugin;

import com.HotFlow.TribeCraft.Timer.Task.DelayTask;
import org.bukkit.event.Cancellable;
import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public class PluginDelayTaskTimeChangeEvent extends PluginEvent implements Cancellable
{
    private final DelayTask task;
    private final int time;
    private Boolean cancelled = false;

    public PluginDelayTaskTimeChangeEvent(Plugin plugin, DelayTask task, int time)
    {
        super(plugin);
        this.task = task;
        this.time = time;
    }

    /**
     * ��ȡ��ʱִ����
     *
     * @return
     */
    public DelayTask getTask()
    {
        return this.task;
    }

    /**
     * ��ȡ������ʱ��
     *
     * @return
     */
    public int getTime()
    {
        return this.time;
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
