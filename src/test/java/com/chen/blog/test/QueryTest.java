package com.chen.blog.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName QueryTest
 * @Author ChenYicheng
 * @Description 测试Query接口下的
 * @Date 2022/2/9 9:27
 */
public class QueryTest {

    public static void main(String[] args) {

/*        int i = 10;
        pass(i);
        System.out.println("print in main , i is " + i);*/


/*        Person p1 = new Person();
        p1.name = "ccc";
        reference(p1);
        p1.introduction();*/

        String name = new String("cyc");
        passB(name);
        System.out.println(name);

    }

    public static void reference(Person person) {
        person.name = person.name + "haha";
    }

    public static void pass(int j) {
        j = 20;
        System.out.println("print in pass , j is " + j);
    }

    public static void passB(String name) {
        name = "hc";
        System.out.println(name);
    }

}

class Person {

    public String name;

    public void introduction() {
        System.out.println(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
