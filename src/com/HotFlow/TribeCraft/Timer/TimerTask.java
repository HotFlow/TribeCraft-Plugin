package com.HotFlow.TribeCraft.Timer;

import com.HotFlow.TribeCraft.Event.Plugin.PluginTimeChangeEvent;

import static org.bukkit.Bukkit.getServer;

import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public final class TimerTask 
{
    private final int taskID;
    private TaskState taskState;
    
    public TimerTask(final Plugin plugin,final ServerTimer timer)
    {
        this.taskState = TaskState.Suspending;
        
        @SuppressWarnings("deprecation")
		int taskID = getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(taskState.equals(TaskState.Running))
                {
                    PluginTimeChangeEvent event = new PluginTimeChangeEvent(plugin ,timer.getTime() + 1);
                    getServer().getPluginManager().callEvent(event);
                    
                    if(event.isCancelled())
                    {
                        return;
                    }
                    
                    timer.setTime(event.getTime());
                }
                else
                {
                    return;
                }
            }
            
        },0L,20L);
        
        this.taskID = taskID;
    }
    
    /**
     * ��ʼ��ʱ
     */
    public void start()
    {
        this.taskState = TaskState.Running;
    }
    
    /**
     * ��ͣ��ʱ
     */
    public void suspend()
    {
        this.taskState = TaskState.Suspending;
    }
    
    /**
     * ֹͣ��ʱ
     * ֹͣ������ʹ��start�ָ�
     */
    public void stop()
    {
        getServer().getScheduler().cancelTask(this.taskID);
    }
    
    /**
     * ��ȡִ������ǰ״̬
     * @return 
     */
    public TaskState getCurrentState()
    {
        return this.taskState;
    }
    
    /**
     * ��ȡʱ��ִ����ID
     * @return 
     */
    public int getTaskID()
    {
        return this.taskID;
    }
}
