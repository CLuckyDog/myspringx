package com.thread.service.impl;

import com.thread.service.PurchaseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceImplTest {
    @Autowired
    PurchaseService purchaseService = null;
    @Test
    public void purchase() {
//        Assert.assertEquals(Integer.valueOf(1),purchaseService.purchase(1L,1L,1));
        Assert.assertNotNull(purchaseService.purchase(1L,1L,1));
    }
}