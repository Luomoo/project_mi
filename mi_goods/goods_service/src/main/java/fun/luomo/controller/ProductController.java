package fun.luomo.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import fun.luomo.pojo.Product;
import fun.luomo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        String header = request.getHeader("Authorization");
        System.out.println("ProductController:" + header);
        return new Result(true, 0, StatusCode.OK, "查询成功", productService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Product> findById(@PathVariable("id") String id) {
        Product product = productService.findById(id);
        return new Result(true, 0, StatusCode.OK, "查询成功", product);
    }

    /**
     * 分页
     */
    @GetMapping(value = "/{page}/{size}")
    public Result findProduct4Page(@PathVariable int page, @PathVariable int size) {
        Page<Product> pageList = productService.findProduct4Page(page, size);
        return new Result(true, 0, StatusCode.OK, "查询成功",
                new PageResult<Product>(pageList.getTotalElements(), pageList.getContent()));
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
        Page<Product> pageList = productService.findSearch(searchMap, page, size);
        return new Result(true, 0, StatusCode.OK, "查询成功", new PageResult<Product>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, 0, StatusCode.OK, "查询成功", productService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param product
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Product product) {
        productService.add(product);
        return new Result(true, 0, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param product
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Product product, @PathVariable String id) {
        product.setId(id);
        productService.update(product);
        return new Result(true, 0, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        productService.deleteById(id);
        return new Result(true, 0, StatusCode.OK, "删除成功");
    }

}
