package cn.ousky.small.server;

import cn.ousky.small.entity.ShoppingCart;
import cn.ousky.small.mapper.IShoppingCartMapper;
import cn.ousky.small.model.Message;
import cn.ousky.small.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShoppingCartServer {

    @Resource
    private IShoppingCartMapper shoppingCartMapper;

    @Resource
    private ResultUtil resultUtil;

    /**
     * 添加购物车若已存在对应则追加数量
     * 若不存在则添加记录
     *
     * @param shoppingCart 购物车信息
     * @return
     */
    public Message addRecord(ShoppingCart shoppingCart) {
        int code;
        //检查商品在购物车是否已存在
        ShoppingCart sc = shoppingCartMapper.findByUidAndCid(shoppingCart);
        if (sc != null) {
            // 若存在在原基础数量上增加
            sc.setNum(sc.getNum() + shoppingCart.getNum());
            // 更新
            code = shoppingCartMapper.update(sc);
        } else {
            // 若不存在添加记录
            code = shoppingCartMapper.insert(shoppingCart);
        }
        return resultUtil.getStateMessage(null, code);
    }
}
