package github.aloofcoder.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import github.aloofcoder.springcloud.service.IPaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Override
    public String getPayment_OK(Long id) {

        return "线程池：" + Thread.currentThread().getName() + "  payment_ok,id=" + id + "\t" + " 哈哈!!!";
    }

    @HystrixCommand(fallbackMethod = "getPayment_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String getPayment_TimeOut(Long id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "  payment_timeout,id=" + id + "\t" + " 欧欧!!!， 耗时：" + timeNumber;
    }

    public String getPayment_TimeOutHandler(Long id) {
        return "线程池：" + Thread.currentThread().getName() + "  payment_timeoutHandler,id=" + id + "\t" + "呃呃呃呃!!!";
    }
}
