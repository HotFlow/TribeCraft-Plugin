package com.HotFlow.TribeCraft.Manager;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class PlayerManager
{
    private final HashMap<String, TribePlayer> players = new HashMap<String, TribePlayer>();

    /**
     * ��ȡ���
     *
     * @param name
     * @return
     */
    public TribePlayer getPlayer(String name)
    {
        for (TribePlayer player : this.getPlayers())
        {
            if (player.getCraftPlayer().getName().equals(name))
            {
                return player;
            }
        }

        return null;
    }

    /**
     * ��ȡ���
     *
     * @param player
     * @return
     */
    public TribePlayer getPlayer(Player player)
    {
        for (TribePlayer p : this.getPlayers())
        {
            if (p.getCraftPlayer().getUniqueId().equals(player.getUniqueId()))
            {
                return p;
            }
        }

        return null;
    }

    /**
     * �������
     *
     * @param name
     * @param player
     */
    public void setPlayer(String name, TribePlayer player)
    {
        this.players.put(name, player);
    }

    /**
     * ��ȡ�������
     *
     * @return
     */
    public List<TribePlayer> getPlayers()
    {
        List<TribePlayer> playerList = new ArrayList<TribePlayer>();

        for (TribePlayer player : this.players.values())
        {
            playerList.add(player);
        }
        return playerList;
    }

    /**
     * ӵ�����
     *
     * @param name
     * @return
     */
    public Boolean hasPlayer(String name)
    {
        for (TribePlayer player : this.getPlayers())
        {
            if (player.getCraftPlayer().getName().equals(name))
            {
                return true;
            }
        }

        return false;
    }
}
