/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.IngredientDAO;
import dao.InputDAO;
import dao.SnackDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.Ingredient;
import models.Input;
import models.Snack;

/**
 *
 * @author 42labinfo
 */
public class InputController {

  
    private JTable table;
    private List<JTextField> fields;
    private List<Ingredient> ingredients;
    private Ingredient ingredient;
    public InputController(){
        this.fields = new ArrayList<>();
        this.ingredients = new ArrayList<>();
        
    }
    public void setIngredients(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
    public void setJTable(JTable table){
        this.table = table;
    }
    public void setFields(JTextField field){
        this.fields.add(field);
    }
    public JTable getTable(){
        return this.table;
    }
    public List<Ingredient> getIngredients(){
        return this.ingredients;
    }
    public List<JTextField> getFields(){
        return this.fields;
    }   
    
    public void setFieldsValue(){
        for (int i = 0; i < this.fields.size(); i++){
            fields.get(i).setText(String.valueOf(table.getValueAt(table.getSelectedRow(), i)));
        }
    }
    
    public void setDateField(){
        Date time = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = String.valueOf(formatter.format(time));
        fields.get(fields.size()-1).setText(stringDate);
    }
     
    public void searcIngredient(int id) throws SQLException {
        IngredientDAO dao = new IngredientDAO();
        this.ingredients = dao.searchById(id);
        this.ingredient = ingredients.get(ingredients.size()-1);
        this.getFields().get(1).setText(String.valueOf(ingredient.getIngredientName()));
        this.getFields().get(3).setText(String.valueOf(ingredient.getIngredientUnitOfMeasure()));
    }
    public void readJTable() throws SQLException{
        
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();        
        this.table.setRowSorter(new TableRowSorter(model));
        model.setNumRows(0);
        
        InputDAO dao = new InputDAO();
            for (Input input: dao.readAll()){
                this.ingredients.add(input.getIngredient());
                model.addRow(new Object[]{
                input.getIngredient().getId(),
                input.getIngredient().getIngredientName(),
                input.getInputQuantity(),
                input.getIngredient().getIngredientUnitOfMeasure(),
                input.getInputCost(),
                input.getInputDate()
            });
        }       
    }
    
    public void readJTableSearch(String search) throws SQLException{
        
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();        
        this.table.setRowSorter(new TableRowSorter(model));
        model.setNumRows(0);
        
        InputDAO dao = new InputDAO();
        
        for (Input input: dao.search(search)){
            this.ingredients.add(input.getIngredient());
            model.addRow(new Object[]{
                input.getIngredient().getId(),
                input.getIngredient().getIngredientName(),
                input.getInputQuantity(),
                input.getIngredient().getIngredientUnitOfMeasure(),
                input.getInputCost(),
                input.getInputDate()
            });
        }       
    }

    public void clean (List <javax.swing.JTextField> fields){
        fields.forEach((field) -> {
            if(!"inputAddDateField".equals(field.getName()) ){
                field.setText("");
            }
        });
        try {
            this.readJTable();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de dador" + ex);
        }
    }

    public boolean add (List <javax.swing.JTextField> fields) throws SQLException, ParseException{
        boolean isEmpty = false;
        for(int i = 0; i > fields.size(); i++){
            if(fields.get(i).getText().isEmpty()){
                isEmpty = true;
                break;
            }
        }
        if(isEmpty){
            return false;
        } else{
            Input input = new Input();
            InputDAO dao = new InputDAO();
            
            Date inputDate = new SimpleDateFormat("yyyy/MM/dd").parse(fields.get(5).getText());
            
            this.ingredient.setId(Integer. parseInt(fields.get(0).getText()));
            this.ingredient.setIngredientName(String.valueOf(fields.get(1).getText()));
            this.ingredient.setIngredientUnitOfMeasure(String.valueOf(fields.get(3).getText()));
            input.setInputQuantity(Float.parseFloat(fields.get(2).getText()));
            input.setInputCost(Float.parseFloat(fields.get(4).getText()));
            input.setInputDate(inputDate);
            input.setIngredient(this.ingredient);
            try {
                dao.addInput(input);
                this.clean(this.fields);
                this.readJTable();
                return true;
            } catch (SQLException ex) {
                System.out.print(ex);
                return false;
            }
        }
    }
   public boolean update(List <javax.swing.JTextField> fields) throws SQLException{
            boolean isEmpty = false;
            for(int i = 0; i > fields.size(); i++){
                System.out.print(fields.get(i));
                if(fields.get(i).getText().isEmpty()){
                    System.out.print("the field "+i+" is empty");
                    isEmpty = true;
                    break;
                }
            }
            if(isEmpty){
                 JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos");
                return false;
            } else{
                Input input = new Input();
                InputDAO dao = new InputDAO();

                input.setId(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                input.setInputQuantity(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(),1))));
                input.setInputCost(Float.parseFloat(fields.get(2).getText()));
             // input.setInputDate(Float.parseFloat(fields.get(3).getText()));
                input.setInputStatus(fields.get(3).getText());
            

                try {
                    dao.updateInput(input);
                    this.readJTable();
                    return true;
                } catch (SQLException ex) {
                    System.out.print(ex);
                    return false;
                }
            }
        
        
    }
    
    public void delete() throws SQLException{
        if (this.table.getSelectedRow() != -1){
            int answer = JOptionPane.showConfirmDialog(null,
                    "Confirma a Exclusão do Registro?", 
                    "Exclusão de Registro",OK_CANCEL_OPTION);
            if(answer == 0){
                Input input = new Input();
                InputDAO dao = new InputDAO();                
                input.setId((int) this.table.getValueAt(
                        this.table.getSelectedRow(), 0));
                
                try {
                    dao.deleteInput(input.getId());
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
                
                this.readJTable();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Selecione um serviço na tabela abaixo!");
        }
    }           
  

}       
    

   


    



