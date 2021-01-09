package xyz.ganbug.flashsale.controller;

import io.lettuce.core.cluster.PubSubClusterEndpoint;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xyz.ganbug.flashsale.entity.Result;
import xyz.ganbug.flashsale.service.PurchaseService;

@RestController
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("html/test.html");
    }

    @PostMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity){
        boolean success = purchaseService.purchase(userId, productId, quantity);
        String message = success ? "购买成功" : "购买失败";
        return new Result(success, message);
    }

    @ExceptionHandler
    public void handleException(Throwable t){
        t.printStackTrace();
    }
}
