/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;

/**
 *
 * @author Lucas Dantas
 */
public class DataSheet extends EntityMultipleId{
    private int dsSnackId;
    private int dsIngredientId;
    private int dsQuantity;
    private float dsTotalCost;
    private String dsStatus;

    public DataSheet() {}

    public DataSheet(int dsSnackId, int dsIngredientId, int dsQuantity, float dsTotalCost, LocalDateTime creation, LocalDateTime update, String status) {
        super(creation, update, status);
        this.dsSnackId = dsSnackId;
        this.dsIngredientId = dsIngredientId;
        this.dsQuantity = dsQuantity;
        this.dsTotalCost = dsTotalCost;
    }

    public int getDsSnackId() {
        return dsSnackId;
    }

    public void setDsSnackId(int dsSnackId) {
        this.dsSnackId = dsSnackId;
    }

    public int getDsIngredientId() {
        return dsIngredientId;
    }

    public void setDsIngredientId(int dsIngredientId) {
        this.dsIngredientId = dsIngredientId;
    }

    public int getDsQuantity() {
        return dsQuantity;
    }

    public void setDsQuantity(int dsQuantity) {
        this.dsQuantity = dsQuantity;
    }

    public float getDsTotalCost() {
        return dsTotalCost;
    }

    public void setDsTotalCost(float dsTotalCost) {
        this.dsTotalCost = dsTotalCost;
    }

    public String getDsStatus() {
        return dsStatus;
    }

    public void setDsStatus(String dsStatus) {
        this.dsStatus = dsStatus;
    }
    
}
