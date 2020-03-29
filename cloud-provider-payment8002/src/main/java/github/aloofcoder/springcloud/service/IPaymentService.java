package github.aloofcoder.springcloud.service;

import github.aloofcoder.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author hanle
 * @date 2020-03-25
 */
public interface IPaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
