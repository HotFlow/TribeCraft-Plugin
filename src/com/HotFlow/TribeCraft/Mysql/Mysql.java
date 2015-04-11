package com.HotFlow.TribeCraft.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.Timer.TimerTask;
import com.HotFlow.TribeCraft.Timer.ServerTimer;

/**
 * 
 * @author a404510
 *
 */
public class Mysql {
	public String ip;
	public String username;
	public String password;
	public int port;
	public String schema;
	private Connection conn;
	public boolean isConnection;
	private TimerTask ConnectionTime=new TimerTask(TribeCraft.plugin, ServerTimer);

	public Mysql(String ip, int port, String username, String password,
			String schema) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.schema = schema;
	}

	/**
	 * ����mysql
	 * 
	 * @return �Ƿ�ɹ�
	 * 
	 */
	public synchronized Boolean connect() {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + port
					+ "/" + schema, username, password);
			if (conn.isClosed()) {
				isConnection=false;
				return false;
			} else {
				isConnection=true;
				return true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		isConnection=false;
		return false;
	}

	/**
	 * ������
	 * 
	 * @param slots
	 * @return �Ƿ�ɹ�
	 */
	public synchronized boolean createTalbe(String name, Slot[] slots) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < slots.length; i++) {
			Slot slot = slots[i];
			sb.append(slot.flag);
			if (i < slots.length - 1) {
				sb.append(", ");
			}
		}
		try {
			PreparedStatement sql = this.conn.prepareStatement("CREATE TABLE "
					+ this.schema + "." + name + "(" + sb.toString()
					+ ");");
			return sql.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �������
	 * 
	 * @param key
	 * @param value
	 * @return �������
	 */
	public synchronized int insert(String table,String key, String value) {
		try {
			PreparedStatement sql = this.conn.prepareStatement("Insert into "
					+ this.schema + " " + table + "(" + key + ")"
					+ " vaule " + value + ");");
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
