package cn.ousky.small.server;

import cn.ousky.small.entity.Order;
import cn.ousky.small.mapper.IOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServer {

    @Resource
    private IOrderMapper orderMapper;

    /**
     * 添加订单
     * @param order
     * @return
     */
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }
}
