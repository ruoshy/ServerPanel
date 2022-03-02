package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "xxxxxxxxxxxxxxxx";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC4Wlwh9eG+nRyi4IHvm/0XJVOj3rhWkfoicv9onbb/S2xcKDvA0g+ePx3vDRWIRECWKZYrO1siQBPNFtwY+a0B0E3Lo6lWwcZB/n0Ky++cHxJJGfkaNt1NrF1LWqE6bz2iom6c5VQY6DVMXSybph23rsAIT/FWWOEDAASjaU/AIjEN8mUL7zsMLRzIMpoXT0JvTQGdJ8QVZGi88Js9uoD5EzdU5min9ghqKwYpXPuZuKElaWPkdJh/ggJLV5d/DOHvCYoJB7U72pZuxooTbB/VpbCxTGGe0hpwD6/uPu7175LeMXnuj4QCaLIgbWLwrpkWhpILupLWJvVIbkuUarLPAgMBAAECggEBAJlHN/dckveDdf3OoPpyPfpypJpJYgdIlCr0Znu7Nh4JvezZ3uSxQx8V53OM82w7I2A2746+aUWO9We+K/3wCfLKGQTJ7NAfiRHx4FGLwouSIgf5um4bU+FzAJbYodkOCLciSsnUuxmCaF430hEQCwFgIHZI0aYze9Kcvz6HHzHlUfPpqICBlci38VXnOm5ttObRVzA1MB+JNn7FH482rFHYLwIyLhP36HSQVCFqbSKNJlupTJ934zT+0tPKNwIOGxuVOpV1QlDLriqt0CN3IzvmAE9L9VmNw5xBFFCvGj0uDV56gFPjmbSn9u6QBP5SMAgdXUJB70dyq0mi8YGiiQECgYEA5onsE4UOX9CVPfgm18sSZ/yYSGqw39rCxSzBr5Byg9h1OLYehGg+EdiwNa044pTzMVgrvCdqujdBbvlO+rTRxEY3t3iB4htTAIQfz9mcb7JgDEB5sohfXoJyy5lPFAyyoQDlBvWDbDoQp0PWhfJugphSeb9Ex8aRt+pMRDUxiY8CgYEAzLadohuqxTdM6p10O+VZNHFOBdDm1iZsWlHcJdQYjKlgdGs6QtcGp42iDLwUZOEOC+NOBajQw6iZ1yO0lAzash8FwTANcQCJGAik5C7vEXCugzgPJUQn0mwyC3pAhtd/I3lXng/jyPdFKtgL5S9grBuJYMEz2kuH9Zt60VTjIsECgYA03goiS9AJDvc6l86oi+wzpoxxhu3619r0hpgAH9ry+0+ear1C1fjt9Mgr9wZlh4zhkvuskUl7XLKHtWp0OcUlAolCbMM0S9nT83isHJYwUzKNfLerxnRAYx/aKtUknujT3TWoS+MQjZxGD1uBqh/HmRDI1KD8Q0Alcfh9Z8ltZwKBgHyOA/01PkM+cXMymB01X4BUDDfnsfvMZxLRSdROZx12lPRgpZMzh538xANhkDsM3+IFzFwPKe5SMEBBPkbyR0InM/YmSZdezwsrf0BC+jyvRc8bFcQD4wLZx91J5eblSTsJQnEuk9ab9J6r0xkAKZ6uJnkTNHx2hmO+1Qx2zLhBAoGAFs5QBfiofUf58SDnCtVQCJKPbcZwEX+4zbcZRAC+TdR8dtep4o7Y35w/kjxxpxk5EXR0Nt7FEVT6RoKTLiwL0vN6Aj4cm7fYZycXuhbrleoGRhJSA7ZRIMitxnedR0y9Pie+/gMGFdtdKkZYPijvWDMhKD1bAw7ApuiBLW3fFzo=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwouvyWbSi1Kg81O7dZRPL8VjyWPYm+oi2Ekc06jNoverbdgtI770bKtbCuTMyf/o86JbPJ01teinUlcr+8NkpV1B1RlGcXos95q8YJwSeiqYKrQ/tLT5lz6Bi3qrORVHvrd/5a4EU8DyQUqaHbJoYbJqJjgnEeWYo5C8uGMx9rxX35p8wtE/tuntTquswdHDzsUY6hbmeeaMs1R4DHMjmjQ11pcK9EP1mTyKmztxj12xSYMKwMzAJFiAPi65weVi9CWmjWKfhihMLuxAxyVwN6g0cXF2bcv9jSQF7uTZKy5ptsziX+raGiDfytr3KPIYhjM22V9DB3Lw42U4JCAHiwIDAQAB";

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.ousky.cn/SaltedFish/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.ousky.cn/SaltedFish/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "/log";


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}