package fun.luomo.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import fun.luomo.client.GoodsClient;
import fun.luomo.pojo.Cart;
import fun.luomo.pojo.Carts;
import fun.luomo.pojo.Product;
import fun.luomo.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        String userId = (String) request.getAttribute("claims_userId");
        String role = (String) request.getAttribute("claims_user");
        if (StringUtils.isEmpty(userId)) {
            if (!"user".equals(role)) {
                throw new RuntimeException("权限不足");
            }

        }
        List<Cart> all = cartService.findAllByUserId(userId);
        Carts carts = new Carts();
        carts.setCartProductVoList(all);
        int price = 0;
        boolean isAllSelect = true;
        int selectCount = 0;
        int allCount = 0;
        for (Cart cart : all) {
            if (cart.getProduct_selected().equals("true")) {
                price += cart.getQuantity() * cart.getProduct_price();
                selectCount += cart.getQuantity();
            }
            if (cart.getProduct_selected().equals("false")) {
                isAllSelect = false;
            }
            allCount += cart.getQuantity();
        }
        carts.setCartTotalPrice(price);
        carts.setSelectedAll(isAllSelect);
        carts.setTotalCount(allCount);
        carts.setSelectCount(selectCount);
        return new Result(true, StatusCode.OK, 0, "查询成功", carts);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, 0, "查询成功", cartService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Cart> pageList = cartService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, 0, "查询成功", new PageResult<Cart>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, 0, "查询成功", cartService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param cart
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Cart cart) {
        cartService.add(cart);
        Result all = findAll();
        return new Result(true, StatusCode.OK, 0, "增加成功", all);
    }

    /**
     * 修改
     *
     * @param cart
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Cart cart, @PathVariable String id) {
        cart.setId(id);
        cartService.update(cart);
        Result all = findAll();
        return new Result(true, StatusCode.OK, 0, "修改成功", all);
    }

    /**
     * 选中某一个
     *
     * @return
     */
    @RequestMapping(value = "/SelectOne", method = RequestMethod.PUT)
    public Result SelectOne(@RequestBody Cart cart) {
        cartService.SelectOne(cart);
        return new Result(true, StatusCode.OK, 0, "选择");
    }

    /**
     * 取消选中某一个
     *
     * @return
     */
    @RequestMapping(value = "/unSelectOne", method = RequestMethod.PUT)
    public Result unSelectOne(@RequestBody Cart cart) {
        cartService.unSelectOne(cart);
        return new Result(true, StatusCode.OK, 0, "取消选择");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        cartService.deleteById(id);
        Result all = findAll();
        return new Result(true, StatusCode.OK, 0, "删除成功", all);
    }

    /**
     * 全选中
     *
     * @param id
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.PUT)
    public Result selectAll(@PathVariable String id) {
        cartService.selectAll();
        return new Result(true, StatusCode.OK, 0, "全选");
    }

    /**
     * 全不选
     *
     * @param id
     */
    @RequestMapping(value = "/unSelectAll", method = RequestMethod.PUT)
    public Result unSelectAll(@PathVariable String id) {
        cartService.unSelectAll();
        return new Result(true, StatusCode.OK, 0, "全不选");
    }

    /**
     * 数量
     */
    @RequestMapping(value = "/products/sum", method = RequestMethod.GET)
    public Result sumProducts() {
        int sum = cartService.sumProducts();
        return new Result(true, StatusCode.OK, 0, "商品数量", sum);
    }


}
