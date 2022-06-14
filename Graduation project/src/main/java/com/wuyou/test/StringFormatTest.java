package com.wuyou.test;

public class StringFormatTest {

    public static void main(String[] args) {

        String s = "001";
//        String format = String.format( "%05s", s);
        String format = String.format("%05d", 1);
        System.out.println(format);
        String name = "youxiong";
        name = String.format("%-16s", name);
        System.out.println(name+"length"+name.length());
    }

}
