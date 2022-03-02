package cn.ousky.small.mapper;

import cn.ousky.small.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * ShoppingCart表Mapper
 */
@Mapper
public interface IShoppingCartMapper {

    /**
     * 通过用户编号以及商品编号查询购物车信息
     *
     * @param shoppingCart 购物车信息
     * @return ShoppingCart
     */
    @Select("select * from ShoppingCart where User_Id=#{user_id} And Commodity_Id=#{commodity_id}")
    ShoppingCart findByUidAndCid(ShoppingCart shoppingCart);

    /**
     * 向购物车表插入数据
     *
     * @param shoppingCart 购物车信息
     * @return int
     */
    @Insert("insert into ShoppingCart(Commodity_Id,User_Id,Num) values(#{commodity_id},#{user_id},#{num})")
    int insert(ShoppingCart shoppingCart);

    /**
     * 更新购物车
     *
     * @param shoppingCart 购物车信息
     * @return int
     */
    @Update("update ShoppingCart set num=#{num} where ShoppingCart_id=#{shoppingCart_id}")
    int update(ShoppingCart shoppingCart);
}
