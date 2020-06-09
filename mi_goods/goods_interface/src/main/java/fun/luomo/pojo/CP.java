package fun.luomo.pojo;

import java.util.List;

/**
 * @author Luomo
 * create 2020/6/9 9:53
 */
public class CP {
    private String id;
    private String name;

    private List<Product> productList;

    public CP(String id, String name, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.productList = productList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
