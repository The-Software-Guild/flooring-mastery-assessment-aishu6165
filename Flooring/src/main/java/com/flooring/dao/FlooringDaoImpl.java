package com.flooring.dao;

import com.flooring.model.FlooringModel;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoImpl implements FlooringDao{
    @Override
    public FlooringModel addProduct(FlooringModel model) {
        return model;
    }

    @Override
    public List<FlooringModel> loadTaxFile() {
        String fileName ="src/main/resources/Data/Taxes".concat(".txt");
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

                    model.setState(arr[0]);
                    model.setTaxRate(new BigDecimal(arr[2]));
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
    public Integer getLastOrderNo(String date) {
        String fileName ="src/main/resources/Orders/Orders_".concat(date).concat(".txt");
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!
        Integer orderNo = null;
        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {

                if(i>0){
                    String[] arr = sCurrentLine.split(",");
                  orderNo = Integer.parseInt(arr[0]);
                }

                i++;
            }

        } catch (IOException e) {

            return 0;

        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return orderNo;
    }

    @Override
    public String saveFile(List<FlooringModel> modelList,String date) {
        String fileName = "src/main/resources/Orders/Orders_".concat(date).concat(".txt");
        File file =new File(fileName);

            FileWriter objectName = null;
            try {
                objectName = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter pw = null;

            pw = new PrintWriter(objectName);
            pw.println("OrderNumber" + "," + "CustomerName" + ","+ "Date" +","+ "State" + "," + "TaxRate" + "," + "ProductType" + "," + "Area" + "," + "CostPerSquareFoot" + "," + "LaborCostPerSquareFoot" + "," + "MaterialCost" + "," + "LaborCost" + "," + "Tax" + "Total" );
            for(FlooringModel data : modelList)
                pw.println(data.getOrderNumber() + "," + data.getCustomerName() +","+data.getDate()+ "," + data.getState() + "," + data.getTaxRate() + "," + data.getProductType() + "," + data.getArea() + "," + data.getCostPerSquareFoot() + "," + data.getLaborCostPerSquareFoot() + "," + data.getMaterialCost() + "," + data.getLaborCost() + "," + data.getTax() + "," + data.getTotal());
            pw.close();

       // pw.println(data); //




        return "Your Order has saved successfully........";
    }

    @Override
    public FlooringModel getOrderDetails(List<String> list) {
        String fileName ="src/main/resources/Orders/Orders_".concat(list.get(1)).concat(".txt");
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!
        FlooringModel model = new FlooringModel();
        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {

                if(i>0){

                    String[] arr = sCurrentLine.split(",");
                    //for the first line it'll print
                    if(arr[0].equals(list.get(0))) {
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
                    }

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
        return model;
    }

}
