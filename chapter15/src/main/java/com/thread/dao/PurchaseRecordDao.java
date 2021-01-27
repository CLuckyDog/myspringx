package com.thread.dao;

import com.thread.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**** imports ****/
@Mapper
public interface PurchaseRecordDao {
     int insertPurchaseRecord(PurchaseRecordPo pr);
}