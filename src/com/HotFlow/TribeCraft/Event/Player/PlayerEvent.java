package com.HotFlow.TribeCraft.Event.Player;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Jerry
 */
public class PlayerEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private final TribePlayer player;

    public PlayerEvent(TribePlayer player)
    {
        this.player = player;
    }

    /**
     * ��ȡ���
     *
     * @return
     */
    public TribePlayer getPlayer()
    {
        return this.player;
    }

    /**
     * ��ȡ������
     *
     * @return
     */
    public HandlerList getHandlers()
    {
        return handlers;
    }

    /**
     * ��ȡ�������б�
     *
     * @return
     */
    public static HandlerList getHandlerList()
    {
        return handlers;
    }

}
