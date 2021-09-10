package com.chen.blog;

import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName TestMd5Code
 * @Author ChenYicheng
 * @Description 测试MD5加密
 * @Date 2021/9/10 12:43
 */
public class TestMd5Code {

    public static String code(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            //16位加密
//            return buf.toString().substring(8,24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        String code = code("123456");
        System.out.println(code);
    }
}
