package tribewaring;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thtTNT
 */
public class TribeWaring {
     public static ServerSocket ss;
     public static String[] email=new String[0];
    public static String getNowTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return "[" + year + "-" + month + "-" + day + "   " + hour + ":" + minute + ":" + second + "] ";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getNowTime() + "���������Ѿ�����");
        Boolean isOpenPort = true;
        do {
            try {
                System.out.print(getNowTime() + "���������˿ڣ�");
                String port_string = sc.nextLine();
                System.out.println(getNowTime() + "���ڿ��Ŷ˿ڣ����Ժ�");
                int port = Integer.parseInt(port_string);
                ss = new ServerSocket(port);
                System.out.println(getNowTime()+"�ɹ�������������������");
                isOpenPort=false;
            } catch (NumberFormatException e) {
                System.out.println(getNowTime()+"������һ����ȷ�Ķ˿ں�");
            } catch (IOException ex) {
                System.out.println(getNowTime()+"�������˿ڱ�ռ�ã�����������˿�");
            }
        } while (isOpenPort);
        System.out.println(getNowTime()+"���������Ա���䣬����finishֹͣ����");
        Boolean isFinish=true;
        do{
            String email_read=sc.nextLine();
            if (email_read.equals("finish")){
                isFinish=false;
            }else{
                String [] emails=new String[email.length+1];
                for (int i=0;i<email.length;i++){
                emails[i]=email[i]; 
                }
                emails[email.length]=email_read;
                email=emails;
                isFinish=true;
            }
        }while(isFinish);
        
        for (int i=0;i<email.length;i++){
            System.out.println(getNowTime()+email[i]);
        }
    }

}
