package com.chen.blog.leetcode10;

/**
 * @author 陈奕成
 * @create 2022 04 27 23:30
 */
public class Demo {

        public static void main(String[] args){
            User user= new User(45494);
            if(user.hasPrivilege()){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }
        static class User{
            private int USER_PRIVILEGE_DELETE=512;

            private int privilege;

            public int getPrivilege() {
                return privilege;
            }

            public void setPrivilege(int privilege) {
                this.privilege = privilege;
            }

            public User() {}

            public User(int privilege) {
                this.privilege = privilege;
            }

            public boolean hasPrivilege(){
                System.out.println(this.privilege);
                System.out.println(Integer.toBinaryString(this.privilege));
                return false;
            }

        }
}
