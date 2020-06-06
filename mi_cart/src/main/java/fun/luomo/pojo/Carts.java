package fun.luomo.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Luomo
 * create 2020/6/6 10:51
 */
public class Carts {
    private List<Cart> cartProductVoList;
    private Boolean selectedAll;
    private Integer cartTotalPrice;

    public List<Cart> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<Cart> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public Boolean getSelectedAll() {
        return selectedAll;
    }

    public void setSelectedAll(Boolean selectedAll) {
        this.selectedAll = selectedAll;
    }

    public Integer getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(Integer cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

}
