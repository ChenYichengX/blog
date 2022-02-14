package com.chen.blog.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName TestPattern
 * @Author ChenYicheng
 * @Description 测试正则表达式
 * @Date 2022/2/8 10:43
 */
public class TestPattern {
    
    /**
     * @Author ChenYicheng
     * @Description 测试 final 修饰引用类型变量，不可再指向其他变量
     * @Date 2022/2/8 14:41
     */
    @Test
    public void test4(){
        final String st1 = "234s";
        String str2 = st1;
//        st1 = new String("ss"); // 报错：final 修饰引用类型变量，不能再让其指向另一个对象
    }

    /**
     * @Author ChenYicheng
     * @Description 测试 4.0 - 3.6
     * @Date 2022/2/8 11:19
     */
    @Test
    public void test3() {
        System.out.println(4.0 - 3.6);
        BigDecimal bigDecimal = new BigDecimal(4.0);
        BigDecimal bigDecimal2 = new BigDecimal(3.6);
        System.out.println(bigDecimal.subtract(bigDecimal2));


        double a = 4.0D;
        double b = 3.6D;

        System.out.println(a - b);

        float c = 4.0f;
        float d = 3.6f;

        System.out.println(c - d);

        // 数在内存中如何存储？补码的形式；补码等于反码+1；
        // 原因：
        //　　　　1.避免了0的编码有两个
        //
        //　　　　2.符号位和有效值位可以一起处理，减法通过加法就可以实现，即简化了计算机的结构设计也提高了运算速度。
        //
        //　　　　补：计算机的加减运算都是通过加法实现的，乘除运算都是通过乘法实现的(当然有的计算机有乘法器，有的计算机无乘法器，乘法也是由加法器实现的)
    }

    @Test
    public void test1() {
        // StringBuffer 线程安全；
        // StringBuilder 线程不安全；
        String str = "成都市(成华区)(武侯区)(高新区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);

        if (m.find()) {
            System.out.println(m.group());
            System.out.println();
        }
    }

    @Test
    public void test2() {
        String tempStr = "";
        String str = "14";
        try {
            tempStr = new String(str.getBytes("ISO-8859-1"), "GBK");
            tempStr = tempStr.trim();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(tempStr);
    }
}
