package cn.ousky.small.controll;

import cn.ousky.small.server.*;
import cn.ousky.small.entity.BrandList;
import cn.ousky.small.entity.Commodity;
import cn.ousky.small.entity.ShoppingCart;
import cn.ousky.small.model.Message;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ContentController {

    @Resource
    private BrandListServer brandListSeriver; // 品牌

    @Resource
    private CommodityServer commoditySeriver; // 商品

    @Resource
    private StoreServer storeSeriver; // 店铺

    @Resource
    private ShoppingCartServer shoppingCartSeriver; // 购物车

    /**
     * 随机获取品牌信息
     *
     * @return List<BrandList>
     */
    @RequestMapping("/getBrandColumn")
    public List<BrandList> getRoundBrandColumn() {
        return brandListSeriver.getRoundBrandColumn();
    }

    /**
     * 随机获取商品信息
     *
     * @param size 数量
     * @return List<Commodity>
     */
    @RequestMapping("/getLikeCommodity")
    public List<Commodity> getRoundCommodity(@RequestParam(value = "size", required = false, defaultValue = "100") int size) {
        return commoditySeriver.getRoundBrandColumn(size);
    }

    /**
     * 随机获取商品与其店铺信息
     *
     * @return List<Commodity>
     */
    @RequestMapping("/getCategory")
    public List<Commodity> getCategory() {
        return commoditySeriver.andStoreRound();
    }

    /**
     * 搜索并分页
     *
     * @param txt  匹配符
     * @param page 页位置
     * @return List<Commodity>
     */
    @RequestMapping("/search")
    public List<Commodity> search(@RequestParam("t") String txt, @RequestParam("page") int page) {
        return commoditySeriver.search(txt, page);
    }

    /**
     * 获取搜索商品总数
     *
     * @param txt 匹配符
     * @return int
     */
    @RequestMapping("/getCommodityCount")
    public int getCommodityLikeCount(@RequestParam("t") String txt) {
        return commoditySeriver.getLikeCount(txt);
    }

    /**
     * 获取店铺信息
     *
     * @param sid 店铺id
     * @return Store
     */
    @RequestMapping("/getShopInformation")
    public String getShopInformation(@RequestParam("sid") int sid) {
        return storeSeriver.findById(sid).toString();
    }

    /**
     * 获取商品信息
     *
     * @param cid 商品id
     * @return Commodity
     */
    @RequestMapping("/getCommodity")
    public String getCommodityP(@RequestParam("cid") int cid) {
        return commoditySeriver.findById(cid).toString();
    }

    /**
     * 添加购物车
     *
     * @param uid 用户id
     * @param cid 商品id
     * @param num 数量
     * @return Message
     */
    @RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
    public Message addShoppingCart(@RequestParam("userId") int uid,
                                   @RequestParam("cid") int cid,
                                   @RequestParam("num") int num) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser_id(uid);
        shoppingCart.setCommodity_id(cid);
        shoppingCart.setNum(num);
        return shoppingCartSeriver.addRecord(shoppingCart);
    }
}
