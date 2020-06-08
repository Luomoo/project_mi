package fun.luomo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luomo
 * create 2020/6/6 19:15
 */
@Component
public class GatewayFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String header = request.getHeader("Authorization");
        System.out.println("filterGatewayFilter:" + header);
       /* if (StringUtils.isNotEmpty(header)) {
            currentContext.addZuulRequestHeader("Authorization", header);

        }*/
        return null;
    }
}
