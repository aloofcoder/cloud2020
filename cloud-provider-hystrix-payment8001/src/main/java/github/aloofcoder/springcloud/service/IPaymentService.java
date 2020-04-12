package github.aloofcoder.springcloud.service;

public interface IPaymentService {

    String getPayment_OK(Long id);

    String getPayment_TimeOut(Long id);

    String paymentCircuitBreaker(Integer id);
}
