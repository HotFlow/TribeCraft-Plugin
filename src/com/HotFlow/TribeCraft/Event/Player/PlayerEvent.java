package com.HotFlow.TribeCraft.Event.Player;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Jerry
 */
public class PlayerEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * ��ȡ������
     * @return 
     */
    public HandlerList getHandlers()
    {
        return handlers;
    }
    
    /**
     * ��ȡ�������б�
     * @return 
     */
    public static HandlerList getHandlerList()
    {
        return handlers;
    }

}
