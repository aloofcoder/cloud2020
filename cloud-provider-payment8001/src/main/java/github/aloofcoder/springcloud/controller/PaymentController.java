package github.aloofcoder.springcloud.controller;

import github.aloofcoder.springcloud.entities.CommonResult;
import github.aloofcoder.springcloud.entities.Payment;
import github.aloofcoder.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hanle
 * @date 2020-03-25
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******** 输入结果：{}", result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****** 查询结果：{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录：id=" + id);
        }
    }
}
