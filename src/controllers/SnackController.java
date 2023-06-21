/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.DataSheetDAO;
import dao.SnackDAO;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;
import models.Snack;

/**
 *
 * @author 42labinfo
 */
public class SnackController {
    
    private JTextField textField;
   
    private List<Snack> snacks;
    private JTable table;
    private final List<JTextField> fields;
    private views.text.area.AreaText textArea;
    private String imageUrl;
    public SnackController() {
        this.fields = new ArrayList<>();
        this.snacks = new ArrayList<>();
        this.textArea = new views.text.area.AreaText();
    }
    
    public void setJTable(JTable table){
        this.table = table;
    }
    public void setFields(javax.swing.JTextField field){
        this.fields.add(field);
    }
    public void setTextArea(views.text.area.AreaText textArea){
        this.textArea = textArea;
    }
    public void setImageUrl(String url){
        this.imageUrl = url;
    }
    
    public views.text.area.AreaText getTextArea(){
        return this.textArea;
    }
    public JTable getTable(){
        return this.table;
    }
    public List<JTextField> getFields(){
        return this.fields;
    }
    public List<Snack> getSnacks(){
        return this.snacks;
    }
    public ImageIcon getImageByIndex(int i){
        String imagePath = snacks.get(i).getSnackImageUrl();
        ImageIcon image = new ImageIcon("productImages/default.jpg");
        System.out.println(imagePath);
        if(new File(imagePath).isFile()){
             image = new ImageIcon(snacks.get(i).getSnackImageUrl());
        }
        return image;
    }
     public void setFieldsValue(){
        for (int i = 0; i < this.fields.size(); i++){
            fields.get(i).setText(String.valueOf(table.getValueAt(table.getSelectedRow(), i+1)));
        }
        this.textArea.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
        
    }
     
    @SuppressWarnings("unchecked")
    public void readJTable() throws SQLException{
        
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();        
        this.table.setRowSorter(new TableRowSorter(model));
        model.setNumRows(0);
        
        SnackDAO dao = new SnackDAO();
        this.snacks = new ArrayList<>();
        for (Snack snack: dao.readAll()){
            this.snacks.add(snack);
            model.addRow(new Object[]{
                snack.getId(),
                snack.getSnackTitle(),
                snack.getSnackSellingPrice(),
                snack.getSnackDescription()
                    
                    
            });
        }       
    }
    
    @SuppressWarnings("unchecked")
    public void readJTableSearch(String search) throws SQLException{
        
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();        
        this.table.setRowSorter(new TableRowSorter(model));
        model.setNumRows(0);
        
        SnackDAO dao = new SnackDAO();
        
        for (Snack snack: dao.search(search)){
            model.addRow(new Object[]{
                snack.getId(),
                snack.getSnackTitle(),
                snack.getSnackSellingPrice(),
                snack.getSnackDescription()
            });
        }       
    }

    public void clean (List <javax.swing.JTextField> fields, views.text.area.AreaText textArea){
        fields.forEach((field) -> {
                field.setText("");
                
        });
        textArea.setText("");
        try {
            this.readJTable();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de dador" + ex);
        }
    }

    public boolean add (List <javax.swing.JTextField> fields, views.text.area.AreaText textArea ) throws SQLException{
        boolean isEmpty = false;
        for(int i = 0; i > fields.size(); i++){
            if(fields.get(i).getText().isEmpty()){
                System.out.print("the field "+i+" is empty");
                isEmpty = true;
                break;
            }
        }
        if(isEmpty){
            return false;
        } else{
            Snack snack = new Snack();
            SnackDAO dao = new SnackDAO();
            
            snack.setSnackTitle(fields.get(0).getText());
            snack.setSnackSellingPrice(Float.parseFloat(fields.get(1).getText().replaceAll("," , ".")));
            snack.setSnackDescription(textArea.getText());
            snack.setSnackImageUrl("productImages/default.jpg");
            if(!this.imageUrl.isEmpty()){
                snack.setSnackImageUrl(this.imageUrl);
            }
            
            try {
                dao.addSnack(snack);
                this.snacks = new ArrayList<>();
                this.clean(this.fields, this.textArea);
                this.readJTable();
                return true;
            } catch (SQLException ex) {
                System.out.print(ex);
                return false;
            }
        }
    }
    
    public boolean update(List <javax.swing.JTextField> fields, views.text.area.AreaText textArea) throws SQLException{
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
                Snack snack = new Snack();
                SnackDAO dao = new SnackDAO();

                snack.setId(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                snack.setSnackTitle(fields.get(0).getText());
                snack.setSnackSellingPrice(Float.parseFloat(fields.get(1).getText().replaceAll("," , ".")));
                snack.setSnackDescription(textArea.getText());
                snack.setSnackImageUrl("productImages/default.jpg");
                if(!this.imageUrl.isEmpty()){
                    snack.setSnackImageUrl(this.imageUrl);
                }
                try {
                    dao.updateSnack(snack);
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
                Snack snack = new Snack();
                SnackDAO dao = new SnackDAO();         
                DataSheetDAO dsDAO = new DataSheetDAO();
                snack.setId((int) this.table.getValueAt(
                        this.table.getSelectedRow(), 0));
                
                try {
                    dsDAO.deleteBySnackId(snack.getId());
                    dao.deleteSnack(snack.getId());
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

