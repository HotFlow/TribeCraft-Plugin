package com.HotFlow.TribeCraft.Manager;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author HotFlow
 */
public class PlayerManager {
	private final HashMap<String, TribePlayer> players = new HashMap<String, TribePlayer>();

	/**
	 * ��ȡ���
	 * 
	 * @param name
	 * @return
	 */
	public TribePlayer getPlayer(String name) {
		return this.players.get(name);
	}

	/**
	 * �������
	 * 
	 * @param name
	 * @param player
	 */
	public void setPlayer(String name, TribePlayer player) {
		this.players.put(name, player);
	}

	/**
	 * ��ȡ�������
	 * 
	 * @return
	 */
	public List<TribePlayer> getPlayers() {
		List<TribePlayer> playerList = new ArrayList<TribePlayer>();

		for (TribePlayer player : this.players.values()) {
			playerList.add(player);
		}
		return playerList;
	}

	public Boolean hasPlayer(String name) {
		for (TribePlayer player : this.getPlayers()) {
			if (player.getCraftPlayer().getName().equalsIgnoreCase(name)) {
				return true;
			}
		}

		return false;
	}
}
