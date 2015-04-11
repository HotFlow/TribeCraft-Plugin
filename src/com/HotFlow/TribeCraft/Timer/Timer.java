package com.HotFlow.TribeCraft.Timer;

public class Timer extends Thread{
  private boolean isWork=false;
  private long time;
  public Timer(){
	  start();
  }
  public void run(){
	  while (isWork){
		  time++;
		  try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  }
  }
  /**
   * ����/��ͣ ��ʱ��
   * @param isWork �Ƿ�����
   */
  public void setWork(boolean isWork){
	  this.isWork=isWork;
  }
  /**
   * ��ȡ��ʱ��ʱ��
   * @return ��ʱ��ʱ��
   */
  public long getTime(){
	  return this.time;
  }
  /**
   * ���ü�ʱ��ʱ��
   * @param time ��ʱ��ʱ��
   */
  public void setTime(long time){
	  this.time=time;
  }
}
