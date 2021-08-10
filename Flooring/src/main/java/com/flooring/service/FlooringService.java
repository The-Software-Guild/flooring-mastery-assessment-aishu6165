package com.flooring.service;

import com.flooring.model.FlooringModel;

import java.util.List;

public interface FlooringService {
    public FlooringModel addProduct(FlooringModel model);
    public List<FlooringModel> displayOrders(String date);
    public List<FlooringModel> loadProductType();
    public FlooringModel validateOrder(FlooringModel model);
    public String saveOrder(FlooringModel updatedModel);
    public FlooringModel getEditOrderDetails(List<String> list);
    public String removeOrder(FlooringModel model);
}
