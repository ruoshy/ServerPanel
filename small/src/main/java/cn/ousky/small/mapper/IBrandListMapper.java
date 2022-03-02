package cn.ousky.small.mapper;

import cn.ousky.small.entity.BrandList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * BrandList表Mapper
 */
@Mapper
public interface IBrandListMapper {

    /**
     * 随机获取品牌信息
     *
     * @return List<BrandList>
     */
    @Select("select * from BrandList order by rand() limit 30")
    List<BrandList> getRoundBrandColumn();


}