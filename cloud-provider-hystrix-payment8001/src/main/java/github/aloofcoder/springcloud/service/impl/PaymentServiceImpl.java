package github.aloofcoder.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
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


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")    // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******* id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功, 流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能为负数，请稍后重试，/(ㄒoㄒ)/~~   id：" + id;
    }
}
