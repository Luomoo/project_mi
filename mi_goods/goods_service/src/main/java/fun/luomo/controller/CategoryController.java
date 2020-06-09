package fun.luomo.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import fun.luomo.pojo.CP;
import fun.luomo.pojo.Category;
import fun.luomo.service.CategoryService;
import fun.luomo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/findCP")
    public Result finCP() {
        List<CP> cp = categoryService.findCP();
        return new Result(true, 0, StatusCode.OK, "查询成功",  cp);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {

        return new Result(true, 0, StatusCode.OK, "查询成功",  categoryService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, 0, StatusCode.OK, "查询成功", categoryService.findById(id));
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
        Page<Category> pageList = categoryService.findSearch(searchMap, page, size);
        return new Result(true, 0, StatusCode.OK, "查询成功", new PageResult<Category>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, 0, StatusCode.OK, "查询成功", categoryService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param category
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return new Result(true, 0, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param category
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Category category, @PathVariable String id) {
        category.setId(id);
        categoryService.update(category);
        return new Result(true, 0, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        categoryService.deleteById(id);
        return new Result(true, 0, StatusCode.OK, "删除成功");
    }

}
