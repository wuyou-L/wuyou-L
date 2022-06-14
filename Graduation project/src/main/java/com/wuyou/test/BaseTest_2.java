package com.wuyou.test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BaseTest_2 {


    /**
     * @Author fankunfeng
     * @Date 2019-01-22 14:39:23
     * @Describe Java8之后Util包Base64编码，比apache方式效率更高
     */
    public static final String UTF_8 = "UTF-8";
    public static Base64.Encoder encoder;
    //即为安全的编码方式，替换“+” “/” “-”为“_”
    public static Base64.Encoder urlEncoder;
    public static Base64.Decoder decoder;
    public static Base64.Decoder urlDecoder;

    static {
        encoder = Base64.getEncoder();
        urlEncoder = Base64.getUrlEncoder();
        decoder = Base64.getDecoder();
        urlDecoder = Base64.getUrlDecoder();
    }

    //encode
    public static byte[] encode(byte[] bytes) {
        return encoder.encode(bytes);
    }

    public static String encode(String string) {
        byte[] encode = encode(string.getBytes());
        try {
            return new String(encode, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode2String(byte[] bytes) {
        return encoder.encodeToString(bytes);
    }

    public static byte[] encode2Byte(String string) {
        return encode(string.getBytes());
    }

    //urlEncoder
    public static byte[] urlEncode(byte[] bytes) {
        return urlEncoder.encode(bytes);
    }

    public static String urlEncode(String string) {
        byte[] encode = urlEncode(string.getBytes());
        try {
            return new String(encode, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlEncode2String(byte[] bytes) {
        return urlEncoder.encodeToString(bytes);
    }

    public static byte[] urlEncode2Byte(String string) {
        return urlEncode(string.getBytes());
    }

    //decode
    public static byte[] decode(byte[] bytes) {
        return decoder.decode(bytes);
    }

    public static byte[] decode2Byte(String string) {
        return decoder.decode(string.getBytes());
    }

    public static String decode2String(byte[] bytes) {
        try {
            return new String(decoder.decode(bytes), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String string) {
        byte[] decode = decode(string.getBytes());
        try {
            return new String(decode, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //urlDecode
    public static byte[] urlDecode(byte[] bytes) {
        return urlDecoder.decode(bytes);
    }

    public static byte[] urlDecode2Byte(String string) {
        return urlDecode(string.getBytes());
    }

    public static String urlDecode2String(byte[] bytes) {
        try {
            return new String(urlDecode(bytes), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlDecode(String string) {
        byte[] decode = urlDecode(string.getBytes());
        try {
            return new String(decode, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "test";
        String encode = encode(str);
        System.out.println(encode);
        String decode = decode(str);
        System.out.println(decode);
    }
}
