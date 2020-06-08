package fun.luomo.listener;

import com.aliyuncs.exceptions.ClientException;
import fun.luomo.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Luomo
 * create 2020/6/4 9:31
 */
@Component
@RabbitListener(queues = "sms")
public class listener {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private Environment env;

    @RabbitHandler
    public void executeSms(Map<String, String> map) throws ClientException {
        String phone = map.get("phone");
        String checkCode = map.get("checkCode");
        System.out.println("phone" + phone);
        System.out.println("checkCode" + checkCode);

        String param = "{\"code\":" + checkCode + "}";
        System.out.println(param);
       /* smsUtil.sendSms(phone, env.getProperty("luomo.sms.verifyCodeTemplate"),env.getProperty("luomo.sms.signName"),
                param); */
    }


}
