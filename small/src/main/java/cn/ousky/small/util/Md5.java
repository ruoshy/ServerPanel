package cn.ousky.small.util;


import org.springframework.util.DigestUtils;

public class Md5 {
    public static String getMd5(String str) {
        String encode = DigestUtils.md5DigestAsHex(str.getBytes());

        System.out.println(encode);
        return encode;
    }
}
