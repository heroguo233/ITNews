package com.gavin.itnews;

import com.gavin.itnews.utils.MD5Utils;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by Gavin
 * on 2019/4/13 14:30
 */
public class MD5Test {


    @Test
    public void md5Test(){
        String s ="123456";
        String salt = MD5Utils.getSalt();
        System.out.println("salt = " + salt);
        String md5 = MD5Utils.getMD5(s, salt);
        System.out.println("md5 = " + md5);
        System.out.println("md5.length() = " + md5.length());
        String md52 = MD5Utils.getMD5(s, salt);
        System.out.println("md52 = " + md52);
        //24e1772491a61b62271c621822b1c324d1c425f17c1f51ab
    }
    @Test
    public void getByt(){
        String  s = "abcs21";
        byte[] bytes = s.getBytes();
        System.out.println("bytes = " + bytes.length);
    }
    @Test
    public void test11(){
        for (int i = 0; i < 50; i++) {
            md5Test();
        }
    }

}
