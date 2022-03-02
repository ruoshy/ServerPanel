package cn.ousky.small.mapper;

import cn.ousky.small.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Order表Mapper
 */
@Mapper
public interface IOrderMapper {

    /**
     * 添加订单
     *
     * @param order 订单信息
     * @return int
     */
    @Insert("INSERT INTO `Order` (Commodity_Id,User_Id,Store_Id,Money,Freight,Create_Date,Evaluate_State,Address,FinalId) " +
            "values(#{commodity_id},#{user_id},#{store_id},#{money},#{freight},#{create_date},#{evaluate_state},#{address},#{finalId})")
    int addOrder(Order order);


    /**
     * 添加支付日期
     *
     * @param order 订单信息
     * @return int
     */
    @Update("update set `Order` set pay_date=#{pay_date}")
    int updatePaydate(Order order);

    /**
     * 添加评价信息
     *
     * @param order 订单信息
     * @return int
     */
    @Update("update set `Order` set evaluate_content=#{evaluate_content},Evaluate_Time=#{evaluate_time},star=#{star}")
    int updateEvaluate(Order order);
}
