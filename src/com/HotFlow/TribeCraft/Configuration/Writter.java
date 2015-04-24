package com.HotFlow.TribeCraft.Configuration;

import com.HotFlow.TribeCraft.Main;
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
public class Writter
{
    private final File file;

    public Writter(File file)
    {
        this.file = file;
    }

    public void write(int index, String s)
    {
        if (!this.file.exists())
        {
            return;
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            reader.close();
            file.delete();

            if (index >= lines.size())
            {
                lines.add(s);
            }

            for (int i = 0; i < lines.size(); i++)
            {
                if (i == index)
                {
                    lines.set(index, s);
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++)
            {
                writer.write(new String(lines.get(i).getBytes("gb2312"), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex)
        {
            Main.logger.warning(ex.toString());
        }
    }

    public void write(String s)
    {
        if (!this.file.exists())
        {
            return;
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            lines.add(s);

            reader.close();
            file.delete();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++)
            {
                writer.write(new String(lines.get(i).getBytes("gb2312"), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex)
        {
            Main.logger.warning(ex.toString());
        }
    }

    public void genData()
    {
        this.write(0, "�������б�:");
    }

    public void genConfig()
    {
        this.write("ȫ������:");
        this.write("  ����������:");
        this.write("    ��������: true");
        this.write("    Ȩ�޼��:");
        this.write("      OP���:");
        this.write("        ����: true");
        this.write("        ������:");
        this.write("        - HotFlow");
        this.write("        - SilenceFlow");
        this.write("        - thtTNT");
        this.write("      ������:");
        this.write("        ����: true");
        this.write("        ������: []");
        this.write("    ��ֹ��������Ʒ�б�:");
        this.write("    - 326");
        this.write("    - 327");
        this.write("    - 351");
        this.write("    ��ֹ����ͨ���������б�:");
        this.write("    - MINECART");
        this.write("    - CHESTMINECART");
        this.write("    - HOPPERMINECART");
        this.write("    �����Ƶ��ʯ:");
        this.write("      ����: true");
        this.write("    ����߿���ˮ:");
        this.write("      ����: true");
        this.write("      Դ�����߶�: 120");
        this.write("      ��ˮ������������: 10");
        this.write("    ����߿��ҽ�:");
        this.write("      ����: true");
        this.write("      Դ�����߶�: 120");
        this.write("      �ҽ�������������: 10");
        this.write("    ��������:");
        this.write("      ����: true");
        this.write("      ����: 360");
        this.write("    ����ʵ������:");
        this.write("      ����: true");
        this.write("      ʵ���б�:");
        this.write("        CHICKEN: 8");
        this.write("        COW: 8");
        this.write("        MUSHROOMCOW: 8");
        this.write("        SHEEP: 8");
        this.write("        PIG: 8");
        this.write("        WOLF: 8");
        this.write("        HORSE: 8");
        this.write("        SNOWMAN: 8");
        this.write("        IRONGOLEM: 8");
        this.write("        VILLAGER: 8");
        this.write("        SQUID: 8");
        this.write("    ����������Ʒ: true");
        this.write("    ���������:");
        this.write("      ����: true");
        this.write("      ����: 300");
        this.write("      ������Ϣ: '&c&l[������] &f���Ƴ� %RemovedAmount% ����Ʒ!'");
        this.write("      ��ǰ�����б�:");
        this.write("        60: '&c&l[������] &f����������һ���Ӻ�������е�����Ʒ!'");
        this.write("      ��Ʒ������:");
        this.write("      - 56");
        this.write("      - 57");
        this.write("      - 129");
        this.write("      - 133");
        this.write("      - 138");
        this.write("      - 203");
        this.write("      - 264");
        this.write("      - 276");
        this.write("      - 277");
        this.write("      - 278");
        this.write("      - 279");
        this.write("      - 310");
        this.write("      - 311");
        this.write("      - 312");
        this.write("      - 313");
        this.write("      - 4414");
        this.write("      - 4415");
        this.write("    �Զ���������:");
        this.write("      ����: true");
        this.write("      ����: 300");
        this.write("  ��������:");
        this.write("    ��ͨ�û�:");
        this.write("      ��Ʒ�������: 0.50");
        this.write("      װ���������: 0.50");
        this.write("      �������ٷֱ�: 0.50");
        this.write("    VIP1:");
        this.write("      ��Ʒ�������: 0.45");
        this.write("      װ���������: 0.45");
        this.write("      �������ٷֱ�: 0.45");
        this.write("    VIP2:");
        this.write("      ��Ʒ�������: 0.40");
        this.write("      װ���������: 0.40");
        this.write("      �������ٷֱ�: 0.40");
        this.write("    VIP3:");
        this.write("      ��Ʒ�������: 0.35");
        this.write("      װ���������: 0.35");
        this.write("      �������ٷֱ�: 0.35");
        this.write("    VIP4:");
        this.write("      ��Ʒ�������: 0.30");
        this.write("      װ���������: 0.30");
        this.write("      �������ٷֱ�: 0.30");
        this.write("    VIP5:");
        this.write("      ��Ʒ�������: 0.25");
        this.write("      װ���������: 0.25");
        this.write("      �������ٷֱ�: 0.25");
        this.write("    VIP6:");
        this.write("      ��Ʒ�������: 0.20");
        this.write("      װ���������: 0.20");
        this.write("      �������ٷֱ�: 0.20");
        this.write("    VIP7:");
        this.write("      ��Ʒ�������: 0.15");
        this.write("      װ���������: 0.15");
        this.write("      �������ٷֱ�: 0.15");
        this.write("    VIP8:");
        this.write("      ��Ʒ�������: 0.10");
        this.write("      װ���������: 0.10");
        this.write("      �������ٷֱ�: 0.10");
        this.write("    VIP9:");
        this.write("      ��Ʒ�������: 0.05");
        this.write("      װ���������: 0.05");
        this.write("      �������ٷֱ�: 0.05");
        this.write("    VIP10:");
        this.write("      ��Ʒ�������: 0.01");
        this.write("      װ���������: 0.01");
        this.write("      �������ٷֱ�: 0.01");
        this.write("  �û�ָ��:");
        this.write("    Survival:");
        this.write("      ����: true");
        this.write("      �������: Main");
        this.write("      �����: main");
        this.write("      Ŀ�ĵؿ�Ϊ���: false");
        this.write("      ������X: 3000");
        this.write("      ������Y: 100");
        this.write("      ������Z: 3000");
        this.write("  �������:");
        this.write("    Vault:");
        this.write("      Permission: true");
        this.write("      Economy: true");
        this.write("      Chat: true");
        this.write("    Residence: true");
    }
}
