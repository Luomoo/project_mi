package fun.luomo.service;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;

import entity.Result;
import fun.luomo.client.GoodsClient;
import fun.luomo.pojo.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import fun.luomo.dao.CartDao;
import fun.luomo.pojo.Cart;
import util.JwtUtil;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GoodsClient goodsClient;

    /**
     * 查询选中数量
     * @return
     */
    public int findSelectCount(String userId){
        return cartDao.findSelectCount(userId);
    }

    /**
     * 查询所有数量
     * @return
     */
    public int findAllCount(String userId){
        return cartDao.findAllCount(userId);
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    /**
     * 查询当前用户全部列表
     *
     * @return
     */
    public List<Cart> findAllByUserId(String id ) {
        return cartDao.findAllByUserId(id);
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Cart> findSearch(Map whereMap, int page, int size) {
        Specification<Cart> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return cartDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Cart> findSearch(Map whereMap) {
        Specification<Cart> specification = createSpecification(whereMap);
        return cartDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Cart findById(String id) {
        return cartDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param cart
     */
    public void add(Cart cart) {
        cart.setId(idWorker.nextId() + "");
        String userId = verificationAndGetUserId();
        cart.setUserId(userId);
        cart.setQuantity(1);
        setProductInfo(cart);

      /*  cart.setProduct_name(data.get("name"));
        cart.setProduct_subtitle(data.get("subtitle"));
        cart.setProduct_main_image(data.get("main_image"));
        cart.setProduct_price(Integer.parseInt(data.get("price")));
        cart.setProduct_name(data.get("name"));
        cart.setProduct_status("1");
        cart.setProduct_total_price(Integer.parseInt(data.get("price")));
        cart.setProduct_stock(data.get("stock"));*/
        /**
         * "productName": "iphone7",
         * "productSubtitle": "双十一促销",
         * "productMainImage": "mainimage.jpg",
         * "productPrice": 7199.22,
         * "productStatus": 1,
         * "productTotalPrice": 86390.64,
         * "productStock": 86,
         */


        cartDao.save(cart);
    }

    private String verificationAndGetUserId() {
        String userId = (String) request.getAttribute("claims_userId");
        String role = (String) request.getAttribute("claims_user");
        if (StringUtils.isEmpty(userId) && !role.equals("user")) {
            throw new RuntimeException("权限不足");
        }
        return userId;
    }

    private void setProductInfo(Cart cart) {
        Product product = goodsClient.findById(cart.getProductId()).getData();
        cart.setProduct_name(product.getName());
        cart.setProduct_subtitle(product.getSubtitle());
        cart.setProduct_main_image(product.getMainImage());
        cart.setProduct_price(product.getPrice());
        cart.setProduct_name(product.getName());
        cart.setProduct_status("1");
        cart.setProduct_total_price(product.getPrice());
        cart.setProduct_stock(product.getStock() + "");
        cart.setProduct_selected("true");
    }

    /**
     * 修改
     *
     * @param cart
     */
    public void update(Cart cart) {
        cartDao.update(cart.getQuantity(), cart.getId());
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        cartDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Cart> createSpecification(Map searchMap) {

        return new Specification<Cart>() {

            @Override
            public Predicate toPredicate(Root<Cart> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 
                if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + (String) searchMap.get("userId") + "%"));
                }
                // 
                if (searchMap.get("productId") != null && !"".equals(searchMap.get("productId"))) {
                    predicateList.add(cb.like(root.get("productId").as(String.class), "%" + (String) searchMap.get("productId") + "%"));
                }
                // 
                if (searchMap.get("product_name") != null && !"".equals(searchMap.get("product_name"))) {
                    predicateList.add(cb.like(root.get("product_name").as(String.class), "%" + (String) searchMap.get("product_name") + "%"));
                }
                // 
                if (searchMap.get("product_subtitle") != null && !"".equals(searchMap.get("product_subtitle"))) {
                    predicateList.add(cb.like(root.get("product_subtitle").as(String.class), "%" + (String) searchMap.get("product_subtitle") + "%"));
                }
                // 
                if (searchMap.get("product_main_image") != null && !"".equals(searchMap.get("product_main_image"))) {
                    predicateList.add(cb.like(root.get("product_main_image").as(String.class), "%" + (String) searchMap.get("product_main_image") + "%"));
                }
                // 
                if (searchMap.get("product_status") != null && !"".equals(searchMap.get("product_status"))) {
                    predicateList.add(cb.like(root.get("product_status").as(String.class), "%" + (String) searchMap.get("product_status") + "%"));
                }
                // 
                if (searchMap.get("product_stock") != null && !"".equals(searchMap.get("product_stock"))) {
                    predicateList.add(cb.like(root.get("product_stock").as(String.class), "%" + (String) searchMap.get("product_stock") + "%"));
                }
                // 
                if (searchMap.get("product_selected") != null && !"".equals(searchMap.get("product_selected"))) {
                    predicateList.add(cb.like(root.get("product_selected").as(String.class), "%" + (String) searchMap.get("product_selected") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public void selectAll() {
        List<Cart> all = cartDao.findAll();
        for (Cart cart : all) {
            cart.setProduct_selected("true");
        }
    }

    public void unSelectAll() {
        List<Cart> all = cartDao.findAll();
        for (Cart cart : all) {
            cart.setProduct_selected("false");
        }
    }

    public int sumProducts() {
        int count = 0;
        List<Cart> list = cartDao.findAll();
        for (Cart cart : list) {
            count += cart.getQuantity();
        }
        return count;
    }

    public void SelectOne(Cart cart) {
        cartDao.selectOne("true", cart.getId());

    }

    public void unSelectOne(Cart cart) {
        cartDao.unSelectOne("false", cart.getId());
    }
}
