package fun.luomo.client;

import fun.luomo.api.ProductApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Luomo
 * create 2020/6/6 20:31
 */
@FeignClient(value = "mi-goods")
public interface GoodsClient extends ProductApi {
}
