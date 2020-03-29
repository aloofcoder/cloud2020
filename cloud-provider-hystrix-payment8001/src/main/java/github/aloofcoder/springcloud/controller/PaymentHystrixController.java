package github.aloofcoder.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import github.aloofcoder.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentHystrixController {

    @Autowired
    private IPaymentService paymentService;

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getPaymentOk(@PathVariable("id") Long id) {
        String result = paymentService.getPayment_OK(id);
        log.info("****** result：" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentTimeOut(@PathVariable("id") Long id) {
        String result = paymentService.getPayment_TimeOut(id);
        log.info("****** result：" + result);
        return result;
    }
}
