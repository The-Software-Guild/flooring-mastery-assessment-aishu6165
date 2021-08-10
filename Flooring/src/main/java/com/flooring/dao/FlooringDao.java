package com.flooring.dao;

import com.flooring.model.FlooringModel;

import java.util.List;

public interface FlooringDao {
    public FlooringModel addProduct(FlooringModel model);
    public List<FlooringModel> loadTaxFile();
    public Integer getLastOrderNo(String date);
    public String saveFile(List<FlooringModel> modelList,String date);
    public FlooringModel getOrderDetails(List<String> list);

}
