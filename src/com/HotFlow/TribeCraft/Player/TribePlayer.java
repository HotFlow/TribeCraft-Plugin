package com.HotFlow.TribeCraft.Player;

import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.NBT.CompoundTag;
import com.HotFlow.TribeCraft.NBT.DoubleTag;
import com.HotFlow.TribeCraft.NBT.ListTag;
import com.HotFlow.TribeCraft.NBT.NBTInputStream;
import com.HotFlow.TribeCraft.NBT.NBTOutputStream;
import com.HotFlow.TribeCraft.NBT.StringTag;
import com.HotFlow.TribeCraft.NBT.Tag;
import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
import com.HotFlow.TribeCraft.Player.VIP.VIP;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class TribePlayer
{

    private final UUID uuid;
    private Location gateLoc1;
    private Location gateLoc2;
    private final List<VIP> vips = new ArrayList<VIP>();
    private DeathInventory deathInventory = new DeathInventory();
    private int deathExp;
    private final List<DelayTask> delayTaskList = new ArrayList<DelayTask>();
    private int dollar;

    public TribePlayer(UUID uuid)
    {
        this.uuid = uuid;
    }

    /**
     * ��ȡ org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer
     *
     * @return
     */
    public Player getCraftPlayer()
    {
        for(Player player : getServer().getOnlinePlayers())
        {
            if(player.getUniqueId().equals(uuid))
            {
                return player;
            }
        }
        
        return null;
    }

    /**
     * ��ȡ������ѡ���1
     *
     * @return
     */
    public Location getGateSelectedLocation1()
    {
        return this.gateLoc1;
    }

    /**
     * ���ô�����ѡ���1
     *
     * @param loc
     */
    public void setGateSelectedLocation1(Location loc)
    {
        this.gateLoc1 = loc;
    }

    /**
     * ��ȡ������ѡ���2
     *
     * @return
     */
    public Location getGateSelectedLocation2()
    {
        return this.gateLoc2;
    }

    /**
     * ���ô�����ѡ���2
     *
     * @param loc
     */
    public void setGateSelectedLocation2(Location loc)
    {
        this.gateLoc2 = loc;
    }

    /**
     * ��ȡVIP�б�
     *
     * @return
     */
    public List<VIP> getVIPList()
    {
        Collections.sort(this.vips, new Comparator<VIP>()
        {
            @Override
            public int compare(VIP vip1, VIP vip2)
            {
                return vip2.getLevel() - vip1.getLevel();
            }

        });

        return this.vips;
    }

    /**
     * ��������ʱ������Ʒ
     *
     * @param inventory
     */
    public void setDeathProtectedItems(DeathInventory inventory)
    {
        this.deathInventory = inventory;
    }

    /**
     * ��ȡ����ʱ������Ʒ
     *
     * @return
     */
    public DeathInventory getDeathProtectedItems()
    {
        return this.deathInventory;
    }

    /**
     * ��������ʱ��������
     *
     * @param exp
     */
    public void setDeathProtectedExp(int exp)
    {
        this.deathExp = exp;
    }

    /**
     * ��ȡ����ʱ��������
     *
     * @return
     */
    public int getDeathProtectedExp()
    {
        return this.deathExp;
    }

    /**
     * ��ȡԤԼִ�����б�
     *
     * @return
     */
    public List<DelayTask> getDelayTaskList()
    {
        return this.delayTaskList;
    }

    /**
     * ���ԤԼִ����
     *
     * @param task
     */
    public void addDelayTask(DelayTask task)
    {
        this.delayTaskList.add(task);
    }

    /**
     * �Ƴ�ԤԼִ����
     *
     * @param task
     */
    public void removeDelayTask(DelayTask task)
    {
        this.delayTaskList.remove(task);
    }

    /**
     * ��ȡ��һ��˿���
     *
     * @return
     */
    public double getKnockbackResistant()
    {
        try
        {
            File playerFile = new File(System.getProperty("user.dir"),
                    "world\\playerdata\\"
                    + this.getCraftPlayer().getUniqueId().toString()
                    + ".dat");

            this.getCraftPlayer().saveData();

            NBTInputStream playerInputStream = new NBTInputStream(new FileInputStream(playerFile));
            CompoundTag playerTag = (CompoundTag) playerInputStream.readTag();
            playerInputStream.close();

            Map<String, Tag> playerData = new LinkedHashMap<String, Tag>(((CompoundTag) playerTag).getValue());

            List<Tag> attributes = ((ListTag) playerData.get("Attributes")).getValue();

            for (Tag attribute : attributes)
            {
                if (((StringTag) ((CompoundTag) attribute).getValue().get("Name")).getValue().equalsIgnoreCase("generic.knockbackResistance"))
                {
                    Double base = ((DoubleTag) ((CompoundTag) attribute).getValue().get("Base")).getValue();
                    return base;
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return 0.0;
    }

    /**
     * ������һ��˿���
     *
     * @param value
     */
    public void setKnockbackResistant(Double value)
    {
        try
        {
            File playerFile = new File(System.getProperty("user.dir"),
                    "world\\playerdata\\"
                    + this.getCraftPlayer().getUniqueId().toString()
                    + ".dat");

            this.getCraftPlayer().saveData();

            NBTInputStream playerInputStream = new NBTInputStream(new FileInputStream(playerFile));
            CompoundTag playerTag = (CompoundTag) playerInputStream.readTag();
            playerInputStream.close();

            Map<String, Tag> playerData = new LinkedHashMap<String, Tag>(((CompoundTag) playerTag).getValue());

            List<Tag> attributes = ((ListTag) playerData.get("Attributes")).getValue();

            for (int i = 0; i < attributes.size(); i++)
            {
                Tag attribute = attributes.get(i);

                if (((StringTag) ((CompoundTag) attribute).getValue().get("Name")).getValue().equalsIgnoreCase("generic.knockbackResistance"))
                {
                    ((CompoundTag) ((ListTag) playerData.get("Attributes")).getValue().get(i)).getValue().put("Base", new DoubleTag("Base", value));

                    CompoundTag newTag = new CompoundTag("", playerData);

                    NBTOutputStream playerOutputStream = new NBTOutputStream(new FileOutputStream(playerFile));
                    playerOutputStream.writeTag(newTag);
                    playerOutputStream.close();
                    getServer().getPlayer(uuid).loadData();
                    return;
                }
            }

        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ��ȡ��ҵ������
     *
     * @return ��ҵ������
     */
    public int getDollar()
    {
        return dollar;
    }

    /**
     * ������ҵ��
     *
     * @param CountOfAdd
     * @return ���Ӻ������
     */
    public int addDollar(int CountOfAdd)
    {
        dollar = dollar + CountOfAdd;
        return dollar;
    }

    /**
     * ������ҵ������
     *
     * @param CountOfTake
     * @return ���ٺ������
     */
    public int TakeDollar(int CountOfTake)
    {
        dollar = dollar + CountOfTake;
        return dollar;
    }

    /**
     * ������ҵ������
     *
     * @param dollar
     */
    public void setDollar(int dollar)
    {
        this.dollar = dollar;
    }
}
