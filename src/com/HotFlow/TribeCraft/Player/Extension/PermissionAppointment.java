package com.HotFlow.TribeCraft.Player.Extension;

/**
 * @author HotFlow
 */
public class PermissionAppointment extends Appointment
{
    private String permission;
    
    public PermissionAppointment(int time,String permission)
    {
        super(time);
        this.permission = permission;
    }
    
    /**
     * ��ȡ����
     * @return 
     */
    public String getPermission()
    {
        return this.permission;
    }
    
    /**
     * ��������
     * @param permission 
     */
    public void setPermission(String permission)
    {
        this.permission = permission;
    }
}
