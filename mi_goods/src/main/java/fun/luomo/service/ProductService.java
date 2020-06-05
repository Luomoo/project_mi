package fun.luomo.service;

import fun.luomo.dao.ProductDao;
import fun.luomo.pojo.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */

    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 条件查询+分页
     *
     * @param page
     * @param size
     * @return
     */
    public Page<Product> findProduct4Page(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productDao.findAll(pageRequest);
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Product> findSearch(Map whereMap, int page, int size) {
        Specification<Product> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Product> findSearch(Map whereMap) {
        Specification<Product> specification = createSpecification(whereMap);
        return productDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Product findById(String id) {
        return productDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param product
     */
    public void add(Product product) {
//        System.out.println(product.getId());
        if (product.getId() == null) {
            product.setId(idWorker.nextId() + "");
        }
        productDao.save(product);
    }

    /**
     * 修改
     *
     * @param product
     */
    public void update(Product product) {
        productDao.save(product);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        productDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Product> createSpecification(Map searchMap) {

        return new Specification<Product>() {

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 
                if (searchMap.get("subtitle") != null && !"".equals(searchMap.get("subtitle"))) {
                    predicateList.add(cb.like(root.get("subtitle").as(String.class), "%" + (String) searchMap.get("subtitle") + "%"));
                }
                // 
                if (searchMap.get("main_image") != null && !"".equals(searchMap.get("main_image"))) {
                    predicateList.add(cb.like(root.get("main_image").as(String.class), "%" + (String) searchMap.get("main_image") + "%"));
                }
                // 
                if (searchMap.get("sub_images") != null && !"".equals(searchMap.get("sub_images"))) {
                    predicateList.add(cb.like(root.get("sub_images").as(String.class), "%" + (String) searchMap.get("sub_images") + "%"));
                }
                // 
                if (searchMap.get("detail") != null && !"".equals(searchMap.get("detail"))) {
                    predicateList.add(cb.like(root.get("detail").as(String.class), "%" + (String) searchMap.get("detail") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
