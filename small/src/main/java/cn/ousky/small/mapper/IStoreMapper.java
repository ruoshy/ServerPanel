package cn.ousky.small.mapper;

import cn.ousky.small.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * IStore表Mapper
 */
@Mapper
public interface IStoreMapper {

    /**
     * 通过Store_Id匹配店铺信息
     *
     * @param id 店铺id
     * @return Store
     */
    @Select("select * from Store where Store_Id=#{id}")
    Store findById(int id);
}
