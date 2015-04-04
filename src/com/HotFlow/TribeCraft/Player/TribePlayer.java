package com.HotFlow.TribeCraft.Player;

import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Player.Extension.PermissionAppointment;
import com.HotFlow.TribeCraft.Player.Extension.TeleportAppointment;
import com.HotFlow.TribeCraft.Player.VIP.VIP;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class TribePlayer
{
    private final Player player;
    private Location gateLoc1;
    private Location gateLoc2;
    private final List<VIP> vips = new ArrayList<VIP>();
    private DeathInventory deathInventory = new DeathInventory();
    private TeleportAppointment teleportAppointment;
    private PermissionAppointment permissionAppointment;
    
    public TribePlayer(Player player)
    {
        this.player = player;
    }
    
    /**
     * ��ȡ org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer
     * @return 
     */
    public Player getCraftPlayer()
    {
        return this.player;
    }
    
    /**
     * ���ö��ڴ���
     * @param appointment
     */
    public void setTeleportingAppointment(TeleportAppointment appointment)
    {
        this.teleportAppointment = appointment;
    }
    
    /**
     * ��ȡ���ڴ���
     * @return 
     */
    public TeleportAppointment getTeleportingAppointment()
    {
        return this.teleportAppointment;
    }
    
    /**
     * ���ö���Ȩ��
     * @param appointment 
     */
    public void setPermissionAppointment(PermissionAppointment appointment)
    {
        this.permissionAppointment = appointment;
    }
    
    /**
     * ��ȡ����Ȩ��
     * @return 
     */
    public PermissionAppointment getPermissionAppointment()
    {
        return this.permissionAppointment;
    }
    
    /**
     * ��ȡ������ѡ���1
     * @return 
     */
    public Location getGateSelectedLocation1()
    {
        return this.gateLoc1;
    }
    
    /**
     * ���ô�����ѡ���1
     * @param loc 
     */
    public void setGateSelectedLocation1(Location loc)
    {
        this.gateLoc1 = loc;
    }
    
    /**
     * ��ȡ������ѡ���2
     * @return 
     */
    public Location getGateSelectedLocation2()
    {
        return this.gateLoc2;
    }
    
    /**
     * ���ô�����ѡ���2
     * @param loc 
     */
    public void setGateSelectedLocation2(Location loc)
    {
        this.gateLoc2 = loc;
    }
    
    /**
     * ��ȡVIP�б�
     * @return 
     */
    public List<VIP> getVIPList()
    {
        Collections.sort(this.vips,new Comparator<VIP>()
        {
            @Override
            public int compare(VIP vip1,VIP vip2)
            {
                return vip2.getLevel() - vip1.getLevel();
            }
        });
        
        return this.vips;
    }
    
    /**
     * ��������ʱ������Ʒ
     * @param inventory
     */
    public void setDeathProtectedItems(DeathInventory inventory)
    {
        this.deathInventory = inventory;
    }
    
    /**
     * ��ȡ����ʱ������Ʒ
     * @return 
     */
    public DeathInventory getDeathProtectedItems()
    {
        return this.deathInventory;
    }
}
