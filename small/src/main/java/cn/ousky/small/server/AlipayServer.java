package cn.ousky.small.server;

import cn.ousky.small.entity.Order;
import cn.ousky.small.model.Baby;
import cn.ousky.small.model.OrderDetailed;
import cn.ousky.small.model.Pay;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AlipayServer {
    @Resource
    private CommodityServer commoditySeriver;

    @Resource
    private OrderServer orderSeriver;

    /**
     * 处理订单
     *
     * @param orderDetailed 订单明细
     * @return 支付页面
     * @throws Exception
     */
    public String handOrder(OrderDetailed orderDetailed) throws Exception {
        // 用户id
        int user = orderDetailed.getUser();
        // 收货地址
        String address = JSON.toJSONString(orderDetailed.getAddress());
        // 订单号
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime date = LocalDateTime.now();
        String finalId = formatter.format(date) + user;

        // 订单日期
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // 生成订单
        int price = 0;
        for (Pay pay : orderDetailed.getPay()) {
            for (Baby baby : pay.getBaby()) {
                Order order = new Order();
                order.setMoney(baby.getPrice() * baby.getNum());
                order.setFreight(baby.getFreight());
                order.setCommodity_id(baby.getId());
                order.setFinalId(finalId);
                order.setCreate_date(time);
                order.setAddress(address);
                order.setUser_id(user);
                order.setStore_id(commoditySeriver.findStoreIdById(baby.getId()));
                if (orderSeriver.addOrder(order) == 0) {
                    throw new Exception();
                }
                price += order.getMoney();
            }
        }
        // 返回支付页面
        return getPayBody(finalId, price);
    }

    /**
     * 获得支付页面
     *
     * @param finalId 订单id
     * @param price   订单总价
     * @return 支付页面
     * @throws Exception
     */
    public String getPayBody(String finalId, int price) throws Exception {
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(finalId.getBytes("ISO-8859-1"), "UTF-8");
        // 付款金额，必填
        String total = price + "";
        String total_amount = new String(total.getBytes("ISO-8859-1"), "UTF-8");
        // 订单名称，必填
        String sub = "咸鱼商城";
        String subject = sub;
        // String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),
        // "UTF-8");
        // 商品描述，可空
        String bo = "";
        String body = new String(bo.getBytes("ISO-8859-1"), "UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        String result;
        result = alipayClient.pageExecute(alipayRequest).getBody();
        // 输出
        return result;
    }
}
