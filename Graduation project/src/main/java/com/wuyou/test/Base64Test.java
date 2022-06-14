package com.wuyou.test;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Java8 之后，Util包的Base64比apache的更高效
 */
public class Base64Test {

    private static final String UTF_8 = "UTF-8";
    //
    private static Base64.Encoder encoder;
    private static Base64.Decoder decoder;
    private static Base64.Decoder urlDecoder;

    static{
        encoder = Base64.getEncoder();
        decoder = Base64.getDecoder();
        urlDecoder = Base64.getUrlDecoder();
    }

    public static byte[] encode(byte[] bytes ){
        return encoder.encode(bytes);
    }

    public static String encode(String str){
        byte[] encode = encoder.encode(str.getBytes());
        try {
            return new String(encode, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode2String(byte[] bytes){
        return encoder.encodeToString(bytes);
    }

    public static byte[] encode2Bytes(String str){
        return encoder.encode(str.getBytes());
    }

    public static byte[] urlDecode(byte[] bytes){
        return urlDecoder.decode(bytes);
    }

    public static byte[] urlDecode2Bytes(String str){
        return urlDecoder.decode(str.getBytes());
    }

    public static String urlDecode2String(byte[] bytes){
        try {
            return new String(urlDecoder.decode(bytes), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlDecode(String str){
        byte[] decode = urlDecoder.decode(str.getBytes());
        try {
            return new String(decode, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        String content = "test";
        String encode = encode(content);
        System.out.println(encode);
        String s = urlDecode(encode);
        System.out.println(s);

    }
}
