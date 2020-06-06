package fun.luomo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import fun.luomo.pojo.Carts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fun.luomo.pojo.Cart;
import fun.luomo.service.CartService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

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


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Cart> all = cartService.findAll();
        Carts carts = new Carts();
        carts.setCartProductVoList(all);
        int price = 0;
        boolean isAllSelect = true;
        for (Cart cart : all) {
            if(cart.getProduct_selected().equals("true")){
                price += cart.getQuantity() * cart.getProduct_price();
            }
            if (cart.getProduct_selected().equals("false")) {
                isAllSelect = false;
            }
        }
        carts.setCartTotalPrice(price);
        carts.setSelectedAll(isAllSelect);
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
        return new Result(true, StatusCode.OK, 0, "增加成功");
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
        return new Result(true, StatusCode.OK, 0, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        cartService.deleteById(id);
        return new Result(true, StatusCode.OK, 0, "删除成功");
    }

}
