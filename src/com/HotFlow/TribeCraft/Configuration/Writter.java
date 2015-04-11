package com.HotFlow.TribeCraft.Configuration;

import com.HotFlow.TribeCraft.TribeCraft;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author HotFlow
 */
public class Writter {
	private final File file;

	public Writter(File file) {
		this.file = file;
	}

	public void write(int index, String s) {
		if (!this.file.exists()) {
			return;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
			file.delete();

			if (index >= lines.size()) {
				lines.add(s);
			}

			for (int i = 0; i < lines.size(); i++) {
				if (i == index) {
					lines.set(index, s);
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < lines.size(); i++) {
				writer.write(new String(lines.get(i).getBytes("gb2312"),
						"gb2312"));
				writer.newLine();
			}
			writer.close();
		} catch (IOException ex) {
			TribeCraft.logger.warning(ex.toString());
		}
	}

	public void genData() {
		this.write(0, "�������б�:");
	}

	public void genConfig() {
		this.write(0, "ȫ������:");
		this.write(1, "  ��������:");
		this.write(2, "    ��ͨ�û�:");
		this.write(3, "      ��Ʒ�������: 0.50");
		this.write(4, "      װ���������: 0.50");
		this.write(5, "      �������ٷֱ�: 0.50");
		this.write(6, "    VIP1:");
		this.write(7, "      ��Ʒ�������: 0.45");
		this.write(8, "      װ���������: 0.45");
		this.write(9, "      �������ٷֱ�: 0.45");
		this.write(10, "    VIP2:");
		this.write(11, "      ��Ʒ�������: 0.40");
		this.write(12, "      װ���������: 0.40");
		this.write(13, "      �������ٷֱ�: 0.40");
		this.write(14, "    VIP3:");
		this.write(15, "      ��Ʒ�������: 0.35");
		this.write(16, "      װ���������: 0.35");
		this.write(17, "      �������ٷֱ�: 0.35");
		this.write(18, "    VIP4:");
		this.write(19, "      ��Ʒ�������: 0.30");
		this.write(20, "      װ���������: 0.30");
		this.write(21, "      �������ٷֱ�: 0.30");
		this.write(22, "    VIP5:");
		this.write(23, "      ��Ʒ�������: 0.25");
		this.write(24, "      װ���������: 0.25");
		this.write(25, "      �������ٷֱ�: 0.25");
		this.write(26, "    VIP6:");
		this.write(27, "      ��Ʒ�������: 0.20");
		this.write(28, "      װ���������: 0.20");
		this.write(29, "      �������ٷֱ�: 0.20");
		this.write(30, "    VIP7:");
		this.write(31, "      ��Ʒ�������: 0.15");
		this.write(32, "      װ���������: 0.15");
		this.write(33, "      �������ٷֱ�: 0.15");
		this.write(34, "    VIP8:");
		this.write(35, "      ��Ʒ�������: 0.10");
		this.write(36, "      װ���������: 0.10");
		this.write(37, "      �������ٷֱ�: 0.10");
		this.write(38, "    VIP9:");
		this.write(39, "      ��Ʒ�������: 0.5");
		this.write(40, "      װ���������: 0.5");
		this.write(41, "      �������ٷֱ�: 0.5");
		this.write(42, "    VIP10:");
		this.write(43, "      ��Ʒ�������: 0.1");
		this.write(44, "      װ���������: 0.1");
		this.write(45, "      �������ٷֱ�: 0.1");
		this.write(46, "  �û�ָ��:");
		this.write(47, "    Survival:");
		this.write(48, "      ����: true");
		this.write(49, "      �������: Main");
		this.write(50, "      �����: main");
		this.write(51, "      Ŀ�ĵؿ�Ϊ���: false");
		this.write(52, "      ������X: 3000");
		this.write(53, "      ������Y: 100");
		this.write(54, "      ������Z: 3000");
	}
}
