package xyz.ganbug.flashsale.service.impl;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import xyz.ganbug.flashsale.dao.ProductMapper;
import xyz.ganbug.flashsale.dao.PurchaseRecordMapper;
import xyz.ganbug.flashsale.entity.ProductDO;
import xyz.ganbug.flashsale.entity.PurchaseRecordDO;
import xyz.ganbug.flashsale.service.PurchaseService;


public class PurchaseServiceImpl implements PurchaseService {
    private final ProductMapper productMapper;
    private final PurchaseRecordMapper prMapper;

    public PurchaseServiceImpl(ProductMapper productMapper, PurchaseRecordMapper prMapper) {
        this.productMapper = productMapper;
        this.prMapper = prMapper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean purchase(Long userId, Long productId, int quantity) {
        ProductDO product = productMapper.getProduct(productId);
        if (product == null || product.getStock() < quantity){
            return false;
        }
        
        productMapper.decreaseProduct(productId, quantity);
        PurchaseRecordDO pr = initPurchaseRecordDo(userId, product, quantity);
        prMapper.insertPurchaseRecord(pr);
        return true;
    }

    private static PurchaseRecordDO initPurchaseRecordDo(Long userId, ProductDO product, int quantity) {
        PurchaseRecordDO pr = new PurchaseRecordDO();
        pr.setNote(product.getNote());
        pr.setPrice(product.getPrice());
        pr.setProductId(product.getId());
        pr.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        pr.setSum(sum);
        pr.setUserId(userId);
        return pr;
    }
}
