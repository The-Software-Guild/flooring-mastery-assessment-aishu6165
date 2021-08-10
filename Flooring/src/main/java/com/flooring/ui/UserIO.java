package com.flooring.ui;

import com.flooring.model.FlooringModel;

import java.util.List;

public interface UserIO {
    public int showOptions();
    public String askProductDate();
    public void displayOrders(List<FlooringModel> model,String date);
    public FlooringModel getOrderDetails(List<FlooringModel> list);
    public String getPermission(FlooringModel model);
    public List<String> getEditOrderDetails();
    public FlooringModel getEditableField(FlooringModel model,List<FlooringModel> productList );
    public List<String> getRemoveOrderDetails();
    public String confirmation(FlooringModel model);
}
