package com.chen.blog.leetcode10;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * @author 陈奕成
 * @create 2022 04 27 23:58
 */
public class Demo3 {

    public static long byte2Long(byte[] b) {
        return
                ((b[0]&0xff)<<56)|
                        ((b[1]&0xff)<<48)|
                        ((b[2]&0xff)<<40)|
                        ((b[3]&0xff)<<32)|

                        ((b[4]&0xff)<<24)|
                        ((b[5]&0xff)<<16)|
                        ((b[6]&0xff)<<8)|
                        (b[7]&0xff);
    }

    public static short byte2Short(byte[] b){
        return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
    }

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.corget.com/DownloadServer/download/Files/order");
            URLConnection urlConnection = url.openConnection();
            int len = urlConnection.getContentLength();
            BufferedInputStream is = new BufferedInputStream(urlConnection.getInputStream());

            byte[] data = new byte[1024];
            int offset = 0;

            while ((offset = is.read(data,0,1024))!= -1) {
//                System.out.println(data[0]);
                byte[] bytes1 = Arrays.copyOfRange(data, 1, 9);
                long l = byte2Long(bytes1);
                System.out.println("id:" + l);

                byte[] bytes2 = Arrays.copyOfRange(data, 9, 11);
                short sh1 = byte2Short(bytes2);
//                System.out.println(sh1);

                byte[] bytes3 = Arrays.copyOfRange(data, 11, 13);
                short sh2 = byte2Short(bytes3);
//                System.out.println(sh2);
                String s = new String(data,13,offset);
                int i = s.indexOf("http://");
                String substring = s.substring(i);
                System.out.println("url:"+substring.substring(0,substring.indexOf('\0')));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
