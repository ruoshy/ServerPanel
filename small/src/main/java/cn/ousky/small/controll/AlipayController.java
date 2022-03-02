package cn.ousky.small.controll;

import cn.ousky.small.server.AlipayServer;
import cn.ousky.small.model.OrderDetailed;
import cn.ousky.small.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AlipayController {

    @Resource
    private AlipayServer alipaySeriver;

    @Resource
    private ResultUtil resultUtil;

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestBody OrderDetailed orderDetailed) {
        String body;
        try {
            body = alipaySeriver.handOrder(orderDetailed);
        } catch (Exception e) {
            return resultUtil.getStateMessage("订单发送异常", 0).toString();
        }
        return body;
    }
}
