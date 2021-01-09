package xyz.ganbug.flashsale.service;

public interface PurchaseService {
    boolean purchase(Long userId, Long productId, int quantity);
}
