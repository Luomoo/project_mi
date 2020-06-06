package fun.luomo.controller;

import java.util.List;
import java.util.Map;

import fun.luomo.pojo.User;
import fun.luomo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    public BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        user = userService.login(user.getUsername(), user.getPassword());
        if (user == null) {
            return new Result(true,0, StatusCode.OK, "登录失败");
        }
        String token = jwtUtil.createJWT(user.getId(), user.getUsername(), "user");

        return new Result(true, 0,StatusCode.OK, "登录成功", token);
    }

    /**
     * 发送验证码
     */
    @PostMapping("/sendsms/{phone}")
    public Result sendSms(@PathVariable String phone) throws Exception {
        userService.sendSms(phone);
        return new Result(true, StatusCode.OK, 0, "发送成功");
    }

    /**
     * 注册
     *
     * @param code
     * @return
     */
    @PostMapping("/register/{code}")
    public Result regist(@PathVariable String code, @RequestBody User user) throws Exception {

        String checkCodeRedis = redisTemplate.opsForValue().get("checkCode_" + user.getPhone());

        if (checkCodeRedis.isEmpty()) {
            return new Result(false, 0, StatusCode.ERROR, "验证码错误");
        }
        if (!checkCodeRedis.equals(code)) {
            return new Result(false, 0, StatusCode.ERROR, "验证码错误");
        }

        userService.add(user);
        return new Result(true, StatusCode.OK, 0, "注册成功");

    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, 0, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, 0, "查询成功", userService.findById(id));
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
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, 0, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, 0, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) throws Exception {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.add(user);
        return new Result(true, StatusCode.OK, 0, "增加成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, 0, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {

        userService.deleteById(id);
        return new Result(true, StatusCode.OK, 0, "删除成功");
    }

}
