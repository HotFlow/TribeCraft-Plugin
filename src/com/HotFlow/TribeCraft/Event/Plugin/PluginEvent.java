package com.HotFlow.TribeCraft.Event.Plugin;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public class PluginEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Plugin plugin;

	public PluginEvent(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * ��ȡ���
	 * 
	 * @return
	 */
	public Plugin getPlugin() {
		return this.plugin;
	}

	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * ��ȡ�������б�
	 * 
	 * @return
	 */
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
