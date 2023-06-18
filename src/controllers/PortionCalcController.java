/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import views.spinner.Spinner;

/**
 *
 * @author 42labinfo
 */
public class PortionCalcController {
    private List<JTextField> calcItemFieldValue = new ArrayList<>();
    private List<JTextField> calcQuantityFieldValue = new ArrayList<>();
    private List<JTextField> calcUnityFieldValue = new ArrayList<>();
    private List<JTextField> calcCostFieldValue = new ArrayList<>();
    private JTextField calcPortionField;
    private int calcPortionQuantityFieldValue;
    
    private List<JTextField> resultItemField = new ArrayList<>();
    private List<JTextField> resultQuantityField = new ArrayList<>();
    private List<JTextField> resultUnityField = new ArrayList<>();
    private List<JTextField> resultCostField = new ArrayList<>();
    private Spinner resultPortionField;
    
    public PortionCalcController(){};
    
    public void clearValues(){
        this.calcItemFieldValue = new ArrayList<>();
        this.calcQuantityFieldValue = new ArrayList<>();
        this.calcUnityFieldValue = new ArrayList<>();
        this.calcCostFieldValue = new ArrayList<>();
        this.calcPortionQuantityFieldValue = 0;
    }
    
    public float ruleOfThree(String stringQuantity, String stringCost){
        float cost = Float.parseFloat(stringCost);
        float quantity = Float.parseFloat(stringQuantity);
        
        System.out.println(stringQuantity);
        System.out.println(stringCost);
        System.out.println(this.resultPortionField.getValue());
        float value = ( cost * (int)this.resultPortionField.getValue()) / quantity;
        System.out.println(value);
        return value;
    }
    
    public Spinner getResultPortionField() {
         return resultPortionField;
    }
   
    public void clear(){
        this.resultItemField = new ArrayList<>();
        this.resultQuantityField = new ArrayList<>();
        this.resultUnityField = new ArrayList<>();
        this.resultCostField = new ArrayList<>();
        this.resultPortionField.setValue(1); 
    }
   
//calc
    public void setCalcItemField(javax.swing.JTextField itemField) {
        if(!itemField.getText().isEmpty()){
            this.calcItemFieldValue.add(itemField);
        }else{
            itemField.setText(" ");
            this.calcItemFieldValue.add(itemField);
        }
    }
    public void setCalcQntField(JTextField qntField) {
        if(!qntField.getText().isEmpty()){
            this.calcQuantityFieldValue.add(qntField);
        }else{
            qntField.setText(" ");
            this.calcQuantityFieldValue.add(qntField);
        }
    }
    public void setCalcUnityField(JTextField unityField) {
        if(!unityField.getText().isEmpty()){
            this.calcUnityFieldValue.add(unityField);
        }else{
            unityField.setText(" ");
            this.calcUnityFieldValue.add(unityField);
        }
    }
    public void setCalcCostField(JTextField costField) {
        if(!costField.getText().isEmpty()){
            this.calcCostFieldValue.add(costField);
        }else{
            costField.setText(" ");
            this.calcCostFieldValue.add(costField);
        }
    }
    public void setCalcQuantityFieldValue(){
        if(!this.calcPortionField.getText().isEmpty() || calcPortionField.getText() != "0"){
            this.calcPortionQuantityFieldValue = Integer.parseInt(calcPortionField.getText());
        }else{
            this.calcPortionQuantityFieldValue = 1;
        }
    }
    public void setCalcPortionField(JTextField quantityField){
        this.calcPortionField = quantityField;
    }
    
    public List<JTextField> getCalcItemFieldValue() {
        return calcItemFieldValue;
    }
    public List<JTextField> getCalcQntFieldValue() {
        return calcQuantityFieldValue;
    }
    public List<JTextField> getCalcUnityFieldValue() {
        return calcUnityFieldValue;
    }
    public List<JTextField> getCalcCostFieldValue() {
        return calcCostFieldValue;
    }

   public int getPortionQuantityFieldValue(){
        return this.calcPortionQuantityFieldValue;
    }
    
    public float sumAllCalcCost(){
        float value = 0;
        for(int i = 0; i < this.calcCostFieldValue.size(); i++){
            try{
                value += Float.parseFloat(this.calcCostFieldValue.get(i).getText());
            }catch(NumberFormatException  E){
                
            }
        }
        
        return value;
    }
 
//result
    public void addResultItemField(javax.swing.JTextField field) {
        resultItemField.add(field);
    }
    public void addResultQntField(javax.swing.JTextField field) {
        resultQuantityField.add(field);
    }    
    public void addResultUnityField(javax.swing.JTextField field) {
        resultUnityField.add(field);
    }
    public void addResultCostField(javax.swing.JTextField field) {
        resultCostField.add(field);
    }
    
    public List<javax.swing.JTextField> getResultItemField() {
        return resultItemField;
    }
    public List<javax.swing.JTextField> getResultQuantityField() {
        return resultQuantityField;
    }
    public List<javax.swing.JTextField> getResultUnityField() {
        return resultUnityField;
    }
    public List<javax.swing.JTextField> getResultCostField() {
        return resultCostField;
    }
    
    public void setResultPortionField(Spinner resultPortionQuantity) {
        if((int) resultPortionQuantity.getValue() == 0){
            resultPortionQuantity.setValue(1);
        }
        this.resultPortionField = resultPortionQuantity;
    }
    public void setResultItemFieldValue(){
        List<JTextField> itemField = this.getCalcItemFieldValue();
        for(int i = 0; i < itemField.size(); i++){
            this
            .getResultItemField()
            .get(i)
            .setText(itemField.get(i).getText());
        }
    }
    public void setResultQntFieldValue(){
        for(int i = 0; i< calcQuantityFieldValue.size(); i++){
            this.resultQuantityField.get(i).setText(calcQuantityFieldValue.get(i).getText());
        }
    }
    public void setResultUnFieldValue(){
        float result = 0; 
        List<JTextField> unityField = this.getCalcUnityFieldValue();
        for(int i = 0; i< unityField.size(); i++){
            this
            .getResultUnityField()
            .get(i)
            .setText(unityField.get(i).getText());
        }
    }
    public void setResultCostFieldValue(){
        List<JTextField> costField = this.getCalcCostFieldValue();
        for(int i = 0; i< costField.size(); i++){
            if(costField.get(i).getText().isEmpty() || costField.get(i).getText() == " "){
                this
                    .getResultCostField()
                    .get(i)
                    .setText(costField.get(i).getText());
            }else{
                this
                    .getResultCostField()
                    .get(i)
                    .setText(  
                    Float.toString(this.ruleOfThree(calcQuantityFieldValue.get(i).getText(), calcCostFieldValue.get(i).getText())));
            }
            
        }
    }
}
