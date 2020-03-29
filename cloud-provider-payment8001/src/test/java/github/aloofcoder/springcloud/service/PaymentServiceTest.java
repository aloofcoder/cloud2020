package github.aloofcoder.springcloud.service;

import github.aloofcoder.springcloud.entities.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private IPaymentService paymentService;

    @Test
    public void createPaymentTest() {
        Payment payment = new Payment();
        payment.setSerial("lkjl23223");
        paymentService.create(payment);
    }
}
