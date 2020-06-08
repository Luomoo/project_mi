package fun.luomo.api;

import entity.Result;
import fun.luomo.pojo.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Luomo
 * create 2020/6/6 21:22
 */
public interface ProductApi {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Result<Product> findById(@PathVariable("id") String id);
}
