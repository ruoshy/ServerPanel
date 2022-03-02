package cn.ousky.small.server;


import cn.ousky.small.entity.BrandList;
import cn.ousky.small.mapper.IBrandListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandListServer {

    @Resource
    private IBrandListMapper iBrandListMapper;

    /**
     * 随机获取品牌信息
     *
     * @return
     */
    public List<BrandList> getRoundBrandColumn() {
        return iBrandListMapper.getRoundBrandColumn();
    }
}