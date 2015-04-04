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
public class Writter
{
    private final File file;
    
    public Writter(File file)
    {
        this.file = file;
    }
    
    public void write(int index,String s)
    {
        if(!this.file.exists())
        {
            return;
        }
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while((line = reader.readLine())!=null)
            {
                lines.add(line);
            }
            reader.close();
            file.delete();
            
            if(index >= lines.size())
            {
                lines.add(s);
            }
            
            for(int i =0;i<lines.size();i++)
            {
                if(i == index)
                {
                    lines.set(index,s);
                }
            }
            
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int i = 0;i<lines.size();i++)
            {
                writer.write(new String( lines.get(i).getBytes( "gb2312" ), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException ex)
        {
            TribeCraft.logger.warning(ex.toString());
        }
    }
    
    public void genData()
    {
        this.write(0, "�������б�:");
    }
    
    public void genConfig()
    {
        this.write(0, "ȫ������:");
        this.write(1, "  �û�ָ��:");
        this.write(2, "    Survival:");
        this.write(3, "      ����: true");
        this.write(4, "      �������: Main");
        this.write(5, "      �����: main");
        this.write(6, "      Ŀ�ĵؿ�Ϊ���: false");
        this.write(7, "      ������X: 3000");
        this.write(8, "      ������Y: 100");
        this.write(9, "      ������Z: 3000");
    }
}
