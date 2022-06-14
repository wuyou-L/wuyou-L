package com.wuyou.test;

public class RandomTest {

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++ ){
            double random = Math.random() * 1000000;
            String str = random + "";
            String substring = str.substring(0, 6);
            System.out.println(substring);

            System.out.println(random);
        }
    }
}
