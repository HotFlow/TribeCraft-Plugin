package com.HotFlow.TribeCraft.Manager;

import com.HotFlow.TribeCraft.Timer.Task.DelayTask;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HotFlow
 */
public class DelayTaskManager
{
    private final List<DelayTask> tasks = new ArrayList<DelayTask>();

    /**
     * ��ȡ������ʱִ����
     *
     * @return
     */
    public List<DelayTask> getTasks()
    {
        return this.tasks;
    }
}
