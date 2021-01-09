package xyz.ganbug.flashsale.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.ganbug.flashsale.entity.ProductDO;

public interface ProductMapper {
    ProductDO getProduct(Long id);

    int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity);
}
