package com.HotFlow.TribeCraft.PortalGate;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import com.HotFlow.TribeCraft.World.Area;

/**
 * @author HotFlow
 */
public class PortalGate {
	private final String name;
	private String message;
	private List<String> commands;
	private PortalGateType type;
	private Area from;
	private Location to;

	public PortalGate(String name) {
		this.name = name;
		this.message = "";
		this.commands = new ArrayList<String>();
		this.type = PortalGateType.Location;
	}

	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ���ô���������
	 * 
	 * @param type
	 */
	public void setType(PortalGateType type) {
		this.type = type;
	}

	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	public PortalGateType getType() {
		return this.type;
	}

	/**
	 * ���ô�����ʾ
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * ��ȡ������ʾ
	 * 
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * ���ô��ʹ�������
	 * 
	 * @param commands
	 */
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	/**
	 * ��ȡ���ʹ�������
	 * 
	 * @return
	 */
	public List<String> getCommands() {
		return this.commands;
	}

	/**
	 * ��ȡ���ͳ�����
	 * 
	 * @return
	 */
	public Area getFrom() {
		return this.from;
	}

	/**
	 * ���ô��ͳ�����
	 * 
	 * @param area
	 */
	public void setFrom(Area area) {
		this.from = area;
	}

	/**
	 * ��ȡ����Ŀ�ĵ�
	 * 
	 * @return
	 */
	public Location getTo() {
		return this.to;
	}

	/**
	 * ���ô���Ŀ�ĵ�
	 * 
	 * @param to
	 */
	public void setTo(Location to) {
		this.to = to;
	}

}
