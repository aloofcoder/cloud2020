package github.aloofcoder.springcloud.service.fallback;

import github.aloofcoder.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;


@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String getPaymentOk(Long id) {
        return "--------- PaymentFallbackService - fallback - getPaymentOk，/(ㄒoㄒ)/~~ ！！！";
    }

    @Override
    public String getPaymentTimeOut(Long id) {
        return "--------- PaymentFallbackService - fallback - getPaymentTimeOut, /(ㄒoㄒ)/~~ ！！！";
    }
}
