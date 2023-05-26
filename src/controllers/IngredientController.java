/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.IngredientDao;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.Ingredient;

/**
 *
 * @author Lucas Dantas
 */
public class IngredientController {
    public static void readJTable(javax.swing.JTable tblEstoque) throws java.sql.SQLException{
        DefaultTableModel modelo = (DefaultTableModel) tblEstoque.getModel();
        tblEstoque.setRowSorter(new TableRowSorter(modelo));
        modelo.setNumRows(0);
        IngredientDao ingredientesDao = new IngredientDao();
        for (Ingredient ingredientes: ingredientesDao.read()){
            modelo.addRow(new Object[]{
                ingredientes.getId(),
                ingredientes.getName(),
                ingredientes.getPrice(),
                ingredientes.getWeight(),
                ingredientes.getQuantity(),
                ingredientes.getType(),
            });
        }
    }
    
    public static void readJTablePesquisar(String pesquisar, javax.swing.JTable tblEstoque) throws java.sql.SQLException{
        DefaultTableModel modelo = (DefaultTableModel) tblEstoque.getModel();
        tblEstoque.setRowSorter(new TableRowSorter(modelo));
        modelo.setNumRows(0);
        IngredientDao ingredientesDao = new IngredientDao();
        for (Ingredient ingredientes: ingredientesDao.readBusca(pesquisar)){
            modelo.addRow(new Object[]{
                ingredientes.getId(),
                ingredientes.getName(),
                ingredientes.getPrice(),
                ingredientes.getToStringCreation(),
                ingredientes.getToStringUpdate()
            });
        }
    }  
}
