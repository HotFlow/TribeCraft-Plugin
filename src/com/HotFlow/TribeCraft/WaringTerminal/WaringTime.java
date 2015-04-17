/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HotFlow.TribeCraft.WaringTerminal;

import com.HotFlow.TribeCraft.WaringTerminal.TimeException.TimeWaringType;
import java.util.Calendar;

/**
 *
 * @author thtTNT ʹ�ñ���ʱ��ʱ����밴��������ʱ�����˳�������ã�������ܻᵼ�±���
 */
class WaringTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public void getNow() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);
    }

    /**
     * ���ô�������
     *
     * @param ��������
     * @throws TimeException ʱ���������
     */
    public void setYear(int year) throws TimeException {
        if (year < 0) {
            throw new TimeException(TimeWaringType.year);
        } else {
            this.year = year;
        }
    }

    /**
     * ���ô�����·�
     *
     * @param ������·�
     * @throws TimeException ʱ���������
     */
    public void setMonth(int month) throws TimeException {
        if (month < 1 || month > 12) {
            throw new TimeException(TimeWaringType.month);
        } else {
            this.month = month;
        }
    }

    /**
     * ���ô��������
     *
     * @param ���������
     * @throws TimeException ʱ���������
     */
    public void setDay(int day) throws TimeException {
        if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8 || this.month == 10 || this.month == 12) {
            if (day < 1 || day > 31) {
                throw new TimeException(TimeWaringType.day);
            } else {
                this.day = day;
            }
            if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
                if (day < 1 || day > 31) {
                    throw new TimeException(TimeWaringType.day);
                } else {
                    this.day = day;
                }
                if (((this.year % 100) != 0) && ((this.year % 4) == 0) || ((this.year & 400) == 0)) {
                    if (day < 1 || day > 29) {
                        throw new TimeException(TimeWaringType.day);
                    } else {
                        this.day = day;
                    }
                } else {
                    if (day < 1 || day > 28) {
                        throw new TimeException(TimeWaringType.day);
                    } else {
                        this.day = day;
                    }
                }
            }
        }
    }

    /**
     * ���ô���ķ�
     *
     * @param ����ķ�
     * @throws TimeException ʱ���������
     */
    public void setMinute(int minute) throws TimeException {
        if (minute < 0 || minute > 60) {
            throw new TimeException(TimeWaringType.minute);
        } else {
            this.minute = minute;
        }
    }

    /**
     * ���ô������
     *
     * @param �������
     * @throws TimeException ʱ���������
     */
    public void setSecond(int s) throws TimeException {
        if (s < 0 || s > 60) {
            throw new TimeException(TimeWaringType.second);
        } else {
            this.second = s;
        }
    }

    /**
     * ���ô����Сʱ
     *
     * @param �����Сʱ
     * @throws TimeException ʱ���������
     */
    public void setHour(int hour) throws TimeException {
        if (hour < 0 || hour > 24) {
            throw new TimeException(TimeWaringType.hour);
        } else {
            this.hour = hour;
        }
    }

    /**
     * ��ȡ��������
     *
     * @return ��������
     */
    public int getYear() {
        return this.year;
    }

    /**
     * ��ȡ������·�
     *
     * @return ������·�
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * ��ȡ���������
     *
     * @return ���������
     */
    public int getDay() {
        return this.day;
    }

    /**
     * ��ȡ����ķ���
     *
     * @return ����ķ���
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * ��ȡ�����Сʱ
     *
     * @return �����Сʱ
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * ��ȡ�������
     *
     * @return �������
     */
    public int getSecond() {
        return this.second;
    }
}
