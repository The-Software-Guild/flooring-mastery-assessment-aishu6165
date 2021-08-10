package com.test.service;

import com.flooring.dao.FlooringDao;
import com.flooring.dao.FlooringDaoImpl;
import com.flooring.model.FlooringModel;
import com.flooring.service.FlooringServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class DaoImplTest {
    FlooringModel model;
    FlooringDao dao;
    @Before
    public void setup() {
        dao = new FlooringDaoImpl();
        model = new FlooringModel();
        model.setOrderNumber(1);
        model.setCustomerName("Test");
        model.setProductType("Tile");
        model.setState("TX");
        model.setArea(new BigDecimal("110"));
        model.setDate("12082021");
    }

    @Test
    public void addProductTest(){
        FlooringModel flooringModel = dao.addProduct(model);
        Assert.assertEquals("TX",flooringModel.getState());
    }
    @Test
    public void loadTaxFileTest(){
       List<FlooringModel> flooringModel = dao.loadTaxFile();
        Assert.assertEquals("TX",flooringModel.get(0).getState());
    }

    @Test
    public void getLastOrderNoTest(){
        Integer number = dao.getLastOrderNo("12082021");
        Assert.assertEquals(java.util.Optional.of(1),java.util.Optional.of(number));
    }
}
