package com.thread.dao;

import com.thread.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**** imports ****/
@Mapper
public interface ProductDao {
    // 获取产品
     ProductPo getProduct(Long id);

     //减库存，而@Param标明MyBatis参数传递给后台
     int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity);

     int decreaseProduct(@Param("id") Long id,@Param("quantity") int quantity, @Param("version") int version);
}