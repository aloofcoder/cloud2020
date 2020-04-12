package github.aloofcoder.springcloud.service;

import github.aloofcoder.springcloud.service.fallback.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String getPaymentOk(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String getPaymentTimeOut(@PathVariable("id") Long id);

}
