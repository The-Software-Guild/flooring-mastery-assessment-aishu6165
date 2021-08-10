package com.flooring.ui;

import com.flooring.model.FlooringModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserIoimpl implements UserIO {
    private static Scanner sc = new Scanner(System.in);
    @Override
    public int showOptions() {
        System.out.println(" <<Flooring Program>>");
        System.out.println(" 1. Display Orders ");
        System.out.println(" 2. Add an Order ");
        System.out.println(" 3. Edit an Order ");
        System.out.println(" 4. Remove an Order ");
        System.out.println(" 5. Export All Data ");
        System.out.println(" 6. Quit ");
        System.out.println("Please enter Option in number : ");
        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    @Override
    public String askProductDate() {
        System.out.println("Please Enter Order Date(MMDDYYYY) :");
        String date = sc.nextLine();
        return date;
    }

    @Override
    public void displayOrders(List<FlooringModel> model, String date) {
        model.forEach(data->{
            System.out.println(data);
        });

    }

    @Override
    public FlooringModel getOrderDetails(List<FlooringModel> list) {
        System.out.println("Please Enter orderDate (DDMMYYYY):");
        String date = sc.nextLine();
        System.out.println("Please enter your Name :");
        String custName = sc.nextLine();
        if(custName.isBlank()){
            System.out.println("Please enter valid Name :");
            custName = sc.nextLine();
        }
        System.out.println("Please Enter state like(TX,CAN) :");
        String state = sc.nextLine();
        list.forEach(data->{
            System.out.println(data.getProductType()+" , "+data.getCostPerSquareFoot()+" , "+data.getLaborCostPerSquareFoot());
        });
        System.out.println("Please pass product type from above list :");
        String productType = sc.nextLine();
        System.out.println("Please Enter Area min(100) :");
        String areStr = sc.nextLine();
        BigDecimal area = new BigDecimal(areStr);
        if(area.compareTo(new BigDecimal(100))==-1){
            System.out.println("Please Enter valid Area min(100) :");
            areStr = sc.nextLine();
            area = new BigDecimal(areStr);
        }
        FlooringModel model = new FlooringModel();
        model.setDate(date);
        model.setCustomerName(custName);
        model.setState(state);
        model.setProductType(productType);
        model.setArea(area);
        return model;
    }

    @Override
    public String getPermission(FlooringModel model) {
        System.out.println(model);
        System.out.println("Do you want to Place this Order Y/N :");
        String str = sc.nextLine();
        return str;
    }

    @Override
    public List<String> getEditOrderDetails() {
        List<String> stringList = new ArrayList<>();
        System.out.println("Enter Order Number :");
        String orderNo = sc.nextLine();
        System.out.println("Enter Order Date :");
        String date = sc.nextLine();
        stringList.add(orderNo);
        stringList.add(date);
        return stringList;
    }

    @Override
    public FlooringModel getEditableField(FlooringModel model,List<FlooringModel> productList ) {
        System.out.println(model);
        System.out.println("Enter Customer Name :");
        String custName = sc.nextLine();
        System.out.println("Enter State :");
        String state = sc.nextLine();
        productList.forEach(data->{
            System.out.println(data.getProductType()+" "+data.getCostPerSquareFoot()+" "+data.getLaborCostPerSquareFoot());
        });

        System.out.println("Select Product Type from above :");
        String prodType = sc.nextLine();
        System.out.println("Enter Area min(100) :");
        String  area = sc.nextLine();
        if(!area.isEmpty() && new BigDecimal(area).compareTo(new BigDecimal(100))==-1){
            System.out.println("Please Enter valid Area min(100) :");
            area=sc.nextLine();
        }

        FlooringModel editedModel = new FlooringModel();
        editedModel.setOrderNumber(model.getOrderNumber());
        editedModel.setCustomerName(custName.isEmpty()?model.getCustomerName():custName);
        editedModel.setState(state.isEmpty()?model.getState():state);
        editedModel.setProductType(prodType.isEmpty()?model.getProductType():prodType);
        editedModel.setArea(area.isEmpty()?model.getArea():new BigDecimal(area));
        editedModel.setDate(model.getDate());
        editedModel.setLaborCost(model.getLaborCost());
        editedModel.setTax(model.getTax());
        editedModel.setMaterialCost(model.getMaterialCost());
        editedModel.setTaxRate(model.getTaxRate());
        editedModel.setLaborCostPerSquareFoot(model.getLaborCostPerSquareFoot());
        editedModel.setCostPerSquareFoot(model.getCostPerSquareFoot());
        editedModel.setTotal(model.getTotal());
        return editedModel;
    }

    @Override
    public List<String> getRemoveOrderDetails() {
        List<String> strList = new ArrayList<>();
        System.out.println("Please Enter Order Number :");
        String orderNo = sc.nextLine();
        System.out.println("Please Enter Order Date :");
        String date = sc.nextLine();
        strList.add(orderNo);
        strList.add(date);
        return strList;
    }

    @Override
    public String confirmation(FlooringModel model) {
        System.out.println(model);
        System.out.println("Are you sure Y/N:");
        String str = sc.nextLine();

        return str;
    }
}
