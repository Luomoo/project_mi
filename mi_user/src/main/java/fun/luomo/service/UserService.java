package fun.luomo.service;

import fun.luomo.dao.UserDao;
import fun.luomo.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.NumberUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /**
     * 发行短信验证码
     * @param phone
     * @throws Exception
     */
    public void sendSms(String phone) throws Exception {
        User checkPhone = userDao.findByPhone(phone);
        if (checkPhone != null) {
            throw new Exception("手机号已存在");
        }
        String checkCode = NumberUtils.generateCode(6);
        redisTemplate.opsForValue().set("checkCode_" + phone, checkCode, 10, TimeUnit.MINUTES);

        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("checkCode", checkCode);

        rabbitTemplate.convertAndSend("sms",map);
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param user
     */
    public void add(User user) throws Exception {
        user.setId(idWorker.nextId() + "");
        User username = userDao.findByUsername(user.getUsername());
        if (username != null) {
            throw new Exception("用户名已存在");
        }
        User phone = userDao.findByPhone(user.getPhone());
        if (phone != null) {
            throw new Exception("手机号已存在");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreated(new Date());
        redisTemplate.delete("checkCode_" + user.getPhone());
        userDao.save(user);
    }

    /**
     * 修改
     *
     * @param user
     */
    public void update(User user) {
        userDao.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        String token = (String) request.getAttribute("claims_admin");
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("权限不足");
        }
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 用户名
                if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.like(root.get("username").as(String.class), "%" + (String) searchMap.get("username") + "%"));
                }
                // 密码，加密存储
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 注册手机号
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


}
