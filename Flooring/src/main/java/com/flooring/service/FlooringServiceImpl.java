package com.flooring.service;

import com.flooring.dao.FlooringDao;
import com.flooring.model.FlooringModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringServiceImpl implements FlooringService{

    private FlooringDao dao;

    private static List<FlooringModel> productTypeList;

    public FlooringServiceImpl(FlooringDao dao) {
        this.dao = dao;
    }

    @Override
    public FlooringModel addProduct(FlooringModel model) {
        dao.addProduct(model);
        return model;
    }

    @Override
    public List<FlooringModel> displayOrders(String date) {
        String fileName ="src/main/resources/Orders/Orders_".concat(date).concat(".txt");
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!
        List<FlooringModel> list = new ArrayList<>();
        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {

                if(i>0){
                    FlooringModel model = new FlooringModel();
                    String[] arr = sCurrentLine.split(",");
                    //for the first line it'll print
                    if(arr.length>13) {
                        model.setOrderNumber(Integer.parseInt(arr[0]));
                        model.setCustomerName(arr[1]+","+arr[2]);
                        model.setDate(arr[3]);
                        model.setState(arr[4]);
                        model.setTaxRate(new BigDecimal(arr[5]));
                        model.setProductType(arr[6]);
                        model.setArea(new BigDecimal(arr[7]));
                        model.setCostPerSquareFoot(new BigDecimal(arr[8]));
                        model.setLaborCostPerSquareFoot(new BigDecimal(arr[9]));
                        model.setMaterialCost(new BigDecimal(arr[10]));
                        model.setLaborCost(new BigDecimal(arr[11]));
                        model.setTax(new BigDecimal(arr[12]));
                        model.setTotal(new BigDecimal(arr[13]));
                    }
                    else{
                        model.setOrderNumber(Integer.parseInt(arr[0]));
                        model.setCustomerName(arr[1]);
                        model.setDate(arr[2]);
                        model.setState(arr[3]);
                        model.setTaxRate(new BigDecimal(arr[4]));
                        model.setProductType(arr[5]);
                        model.setArea(new BigDecimal(arr[6]));
                        model.setCostPerSquareFoot(new BigDecimal(arr[7]));
                        model.setLaborCostPerSquareFoot(new BigDecimal(arr[8]));
                        model.setMaterialCost(new BigDecimal(arr[9]));
                        model.setLaborCost(new BigDecimal(arr[10]));
                        model.setTax(new BigDecimal(arr[11]));
                        model.setTotal(new BigDecimal(arr[12]));
                    }
                   list.add(model);
                }

                i++;
            }

        } catch (IOException e) {
            return null;

        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<FlooringModel> loadProductType() {
        String fileName ="src/main/resources/Data/Products".concat(".txt");
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!
        List<FlooringModel> list = new ArrayList<>();
        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {

                if(i>0){
                    FlooringModel model = new FlooringModel();
                    String[] arr = sCurrentLine.split(",");
                    //for the first line it'll print

                    model.setProductType(arr[0]);
                    model.setCostPerSquareFoot(new BigDecimal(arr[1]));
                    model.setLaborCostPerSquareFoot(new BigDecimal(arr[2]));
                    list.add(model);
                }

                i++;
            }

        } catch (IOException e) {
            return null;

        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        productTypeList =list;
        return list;
    }

    @Override
    public FlooringModel validateOrder(FlooringModel model) {
        List<FlooringModel> taxtList = dao.loadTaxFile();
        boolean stateAvl = false;
        BigDecimal taxRate = null;
        for(FlooringModel data : taxtList){
            if(data.getState().equalsIgnoreCase(model.getState())) {
                stateAvl = true;
                taxRate = data.getTaxRate();
            }
        }
        if(stateAvl) {
            model = calculateOrderRates(taxRate, model);
            return model;
        }
        else{
            return null;
        }

    }

    @Override
    public String saveOrder(FlooringModel updatedModel) {
        List<FlooringModel> modelList = displayOrders(updatedModel.getDate());
        boolean flag = false;
        if(modelList!=null) {
            for (FlooringModel model : modelList) {
                if (model.getOrderNumber().compareTo(updatedModel.getOrderNumber()) == 0) {
                    int index = modelList.indexOf(model);
                    modelList.set(index, updatedModel);
                    flag = true;
                } else {
                   // flag = false;
                }
            }
        }
        else{
            modelList= new ArrayList<>();
        }
        if(!flag){
            modelList.add(updatedModel);
        }
        String msg = dao.saveFile(modelList,updatedModel.getDate());

        return msg;
    }

    @Override
    public FlooringModel getEditOrderDetails(List<String> list) {
       FlooringModel model = dao.getOrderDetails(list);
       if(model!=null) {
           if(model.getOrderNumber()!=null) {
               return model;
           }else
            return null;
       }
       else
        return null;
    }

    @Override
    public String removeOrder(FlooringModel model) {
        List<FlooringModel> list = displayOrders(model.getDate());
        int index = -1;
        if(list!=null) {
            for (FlooringModel model1 : list) {
                if (model1.getOrderNumber().compareTo(model.getOrderNumber()) == 0) {
                    index = list.indexOf(model1);

                }
            }
        }
        if(index>-1)
            list.remove(index);
        else
            return "Order Not Available";
        dao.saveFile(list,model.getDate());
        return "Your order has been removed successfully";
    }

    private FlooringModel calculateOrderRates(BigDecimal taxRate, FlooringModel model) {
        BigDecimal costpersqFoot = null;
        BigDecimal labourPerSqFoot = null;
        for(FlooringModel data : productTypeList){
            if(data.getProductType().equalsIgnoreCase(model.getProductType())){
                costpersqFoot = data.getCostPerSquareFoot();
                labourPerSqFoot = data.getLaborCostPerSquareFoot();
            }
        }
        model.setTaxRate(taxRate);
        model.setCostPerSquareFoot(costpersqFoot);
        model.setLaborCostPerSquareFoot(labourPerSqFoot);
        BigDecimal materialCost = model.getArea().multiply(costpersqFoot);
        BigDecimal labourCost = model.getArea().multiply(labourPerSqFoot);
        BigDecimal tax = (materialCost.add(labourCost)).multiply(taxRate.divide(new BigDecimal(100)));
        BigDecimal total = materialCost.add(tax).add(labourCost);
        if(model.getOrderNumber()==null) {
            Integer orderNo = dao.getLastOrderNo(model.getDate());
            model.setOrderNumber(orderNo+1);
        }
        model.setMaterialCost(materialCost);
        model.setLaborCost(labourCost);
        model.setTax(tax);
        model.setTotal(total);
        return model;
    }
}
