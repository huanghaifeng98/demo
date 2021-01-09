package xyz.ganbug.flashsale.dao;

import org.springframework.stereotype.Repository;
import xyz.ganbug.flashsale.entity.PurchaseRecordDO;

public interface PurchaseRecordMapper {
    int insertPurchaseRecord(PurchaseRecordDO pr);
}
