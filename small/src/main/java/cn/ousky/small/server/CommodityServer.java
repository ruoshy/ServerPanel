package cn.ousky.small.server;

import cn.ousky.small.entity.Commodity;
import cn.ousky.small.mapper.ICommodityMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommodityServer {
    @Resource
    private ICommodityMapper commodityMapper;

    /**
     * 通过id查找商品信息
     *
     * @param id
     * @return
     */
    public Commodity findById(int id) {
        return commodityMapper.findById(id);
    }

    /**
     * 随机获取品牌信息
     *
     * @param size 数量
     * @return
     */
    public List<Commodity> getRoundBrandColumn(int size) {
        return commodityMapper.getRoundCommodity(size);
    }

    /**
     * 与store表连接
     * 获取商品信息与店铺信息
     * @return
     */
    public List<Commodity> andStoreRound() {
        return commodityMapper.andStoreRound();
    }

    /**
     * 模糊匹配并分页
     * 获取商品信息
     *
     * @param txt  匹配符
     * @param page 页位置
     * @return
     */
    public List<Commodity> search(String txt, int page) {
        int size = 20;
        int pos = (page - 1) * size;
        return commodityMapper.search(txt, pos, size);
    }

    /**
     * 模糊匹配
     * 获取总数
     *
     * @param txt
     * @return
     */
    public int getLikeCount(String txt) {
        return commodityMapper.getLikeCount(txt);
    }

    /**
     * 通过商品id查找店铺信息
     *
     * @param id
     * @return
     */
    public int findStoreIdById(int id) {
        return commodityMapper.findStoreIdById(id);
    }
}
