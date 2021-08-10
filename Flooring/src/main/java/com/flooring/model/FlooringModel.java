package com.flooring.model;

import java.math.BigDecimal;

public class FlooringModel {

    private Integer  orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate ;
    private String productType;
    private String date;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot ;
    private BigDecimal laborCostPerSquareFoot ;
    private BigDecimal materialCost ;
    private BigDecimal laborCost ;
    private BigDecimal tax;
    private BigDecimal total ;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return
                "orderNumber=" + orderNumber +
                ",customerName='" + customerName + '\'' +
                ",state='" + state + '\'' +
                ",taxRate=" + taxRate +
                ",productType='" + productType + '\'' +
                ",date='" + date + '\'' +
                ",area=" + area +
                ",costPerSquareFoot=" + costPerSquareFoot +
                ",laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                ",materialCost=" + materialCost +
                ",laborCost=" + laborCost +
                ",tax=" + tax +
                ",total=" + total ;
    }
}
