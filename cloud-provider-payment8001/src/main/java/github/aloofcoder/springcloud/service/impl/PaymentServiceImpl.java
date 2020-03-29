package github.aloofcoder.springcloud.service.impl;

import github.aloofcoder.springcloud.dao.PaymentDao;
import github.aloofcoder.springcloud.entities.Payment;
import github.aloofcoder.springcloud.service.IPaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanle
 * @date 2020-03-25
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
