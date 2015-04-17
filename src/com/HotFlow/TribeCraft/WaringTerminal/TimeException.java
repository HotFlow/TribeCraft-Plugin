package com.HotFlow.TribeCraft.WaringTerminal;

/**
 *
 * @author thtTNT
 */
public class TimeException extends Exception {

    private TimeWaringType WaringType;

    public enum TimeWaringType {

        year, month, day, hour, minute, second;
    }

    public TimeException(TimeWaringType type) {
        this.WaringType = type;
    }

    /**
     * ��ȡʱ�������Ϣ
     *
     * @return ʱ�������Ϣ
     */
    public TimeWaringType getWaringType() {
        return WaringType;
    }
}
