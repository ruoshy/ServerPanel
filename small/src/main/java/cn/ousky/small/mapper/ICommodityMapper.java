package cn.ousky.small.mapper;

import cn.ousky.small.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Commodity表Mapper
 */
@Mapper
public interface ICommodityMapper {


    /**
     * 查询通过Commodity_Id匹配店铺信息
     *
     * @param id 商品id
     * @return Commodity
     */
    @Select("select * from Commodity where Commodity_Id=#{id}")
    Commodity findById(int id);

    /**
     * 查询随机获取商品信息
     *
     * @param size 长度
     * @return List<Commodity>
     */
    @Select("select * from Commodity order by rand() limit #{size}")
    List<Commodity> getRoundCommodity(int size);


    /**
     * 查询与store表连接随机获取的商品信息
     *
     * @return List<Commodity>
     */
    @Select("select Store.store_name,commodity_id,Commodity.store_id,commodity_name,img_url,one_price " +
            "from Commodity INNER JOIN Store on Commodity.Store_Id=Store.Store_Id " +
            "order by rand() limit 8")
    @Results({
            @Result(property = "store.store_name", column = "store_name")
    })
    List<Commodity> andStoreRound();


    /**
     * 查询与store连接模糊搜索的商品信息
     *
     * @param txt  匹配符
     * @param pos  分页位置
     * @param size 长度
     * @return List<Commodity>
     */
    @Select("select Store.City,Store.Store_Name,Commodity_Id,Commodity_Name,Commodity.Store_Id," +
            "Classify,One_Price,Sales_Volume,Img_Url,User_Evaluate,Postage " +
            "from Commodity INNER JOIN Store on Commodity.Store_Id=Store.Store_Id " +
            "where Commodity_Name like concat('%',#{txt},'%') limit #{pos},#{size}")
    @Results({
            @Result(property = "store.city", column = "city"),
            @Result(property = "store.store_name", column = "store_name"),
            @Result(property = "store.store_id", column = "store_id"),

    })
    List<Commodity> search(String txt, int pos, int size);


    /**
     * 查询模糊匹配的商品总数
     *
     * @param txt 匹配符
     * @return int
     */
    @Select("select COUNT(*) from Commodity where Commodity_Name like concat('%',#{txt},'%')")
    int getLikeCount(String txt);

    /**
     * 通过商品id查找所属店铺id
     *
     * @param id 商品id
     * @return int
     */
    @Select("select Store_Id from Commodity where Commodity_Id=#{id}")
    int findStoreIdById(int id);
}
