package github.aloofcoder.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import github.aloofcoder.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/payment/ok/{id}")
    String getPaymentOk(@PathVariable("id") Long id) {
        String result = paymentHystrixService.getPaymentOk(id);
        log.info("****** result：" + result);
        return result;
    }

    @GetMapping("/consumer/hystrix/payment/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "getPaymentTimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    String getPaymentTimeOut(@PathVariable("id") Long id) {
        int a = 10 / 0;
        String result = paymentHystrixService.getPaymentTimeOut(id);
        log.info("****** result：" + result);
        return result;
    }

    String getPaymentTimeOutHandler(@PathVariable("id") Long id) {
        return "我是消费者80，对方支付系统繁忙，请10秒钟后重试";
    }

    public String payment_Global_FallbackMethod() {
        return "Global 异常处理信息，请稍后重试，/(ㄒoㄒ)/~~";
    }
}
