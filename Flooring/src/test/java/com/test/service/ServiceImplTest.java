package com.test.service;

import com.flooring.dao.FlooringDao;
import com.flooring.dao.FlooringDaoImpl;
import com.flooring.model.FlooringModel;
import com.flooring.service.FlooringService;
import com.flooring.service.FlooringServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ServiceImplTest {
    FlooringModel model = new FlooringModel();
    FlooringService service;
    FlooringDao dao;

    @Before
    public void setup() {
        dao = new FlooringDaoImpl();
        service = new FlooringServiceImpl(dao);
        model.setOrderNumber(1);
        model.setCustomerName("Test");
        model.setProductType("Tile");
        model.setState("TX");
        model.setArea(new BigDecimal("110"));
        model.setDate("12082021");
    }

    @Test
    public void addProductTest() {
        FlooringModel model1 = service.addProduct(model);
        Assert.assertEquals("Test", model1.getCustomerName());
    }

    @Test
    public void displayOrdersTest() {
        List<FlooringModel> list = service.displayOrders("11082021");
        Assert.assertNotNull(list);
    }

    @Test
    public void loadProductTypeTest() {
        List<FlooringModel> list = service.loadProductType();
        Assert.assertNotNull(list);

    }

    @Test
    public void validateOrderTest() {
        service.loadProductType();
        FlooringModel model = service.validateOrder(this.model);
        Assert.assertEquals("TX",model.getState());
        Assert.assertNotNull(model.getTotal());
        Assert.assertNotNull(model.getTaxRate());
    }


    @Test
    public void saveOrderTest(){
        service.loadProductType();
        FlooringModel savemodel = service.validateOrder(this.model);
        String message = service.saveOrder(savemodel);
        Assert.assertEquals("Your Order has saved successfully........",message);
    }

    @Test
    public void getEditOrderDetailsTest(){
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("12082021");
        FlooringModel flooringModel = service.getEditOrderDetails(stringList);
        Assert.assertEquals("Test",flooringModel.getCustomerName());
    }
}
