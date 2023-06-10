package dao;

import ConnectionFactory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.DataSheet;
import models.Ingredient;
import models.Snack;

public class DataSheetDAO {
    private final Connection connection;

    public DataSheetDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public List<DataSheet> readAll() throws SQLException {
        List<DataSheet> dataSheets = new ArrayList<>();
        String query = "SELECT datasheet.*, snack.*, ingredient.* FROM datasheet" + 
                "INNER JOIN snack ON datasheet.dsSnackId = snack.snackId " +
                "INNER JOIN ingredient ON ds.dsIngredientId = ingredient.ingredientId";
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                DataSheet dataSheet = new DataSheet();
                dataSheet.setDsSnackId(resultSet.getInt("dsSnackId"));
                dataSheet.setDsIngredientId(resultSet.getInt("dsIngredientId"));
                dataSheet.setDsQuantity(resultSet.getInt("dsQuantity"));
                dataSheet.setDsTotalCost(resultSet.getFloat("dsTotalCost"));
                dataSheet.setDsStatus(resultSet.getString("dsStatus"));
                
                Snack snack = new Snack();
                snack.setId(resultSet.getInt("snackId"));
                snack.setSnackTitle(resultSet.getString("snackTitle"));
                snack.setSnackSellingPrice(resultSet.getFloat("snackSellingPrice"));
                snack.setSnackDescription(resultSet.getString("snackDescription"));
                snack.setSnackImageUrl(resultSet.getString("snackImageUrl"));
                snack.setSnackStatus(resultSet.getString("snackStatus"));
                
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("ingredientId"));
                ingredient.setIngredientName(resultSet.getString("ingredientName"));
                ingredient.setIngredientMinQuantity(resultSet.getFloat("ingredientMinQuantity"));
                ingredient.setIngredientUnitOfMeasure(resultSet.getString("ingredientUnitOfMeasure"));
                ingredient.setIngredientUnitCost(resultSet.getFloat("ingredientUnitCost"));
                ingredient.setIngredientStatus(resultSet.getString("ingredientStatus"));
                ingredient.setIngredientStock(resultSet.getFloat("ingredientStock"));
                ingredient.setIngredientStockStatus(resultSet.getString("ingredientStockStatus"));
                ingredient.setIngredientUnitQuantity(resultSet.getFloat("ingredientUnitQuantity"));
                
                dataSheet.setSnack(snack);
                dataSheet.setIngredient(ingredient);
                dataSheets.add(dataSheet);
            }
        }
        return dataSheets;
    }

    public List<DataSheet> search(String searchTerm) throws SQLException {
        List<DataSheet> dataSheets = new ArrayList<>();
        String query = "SELECT datasheet.*, snack.*, ingredient.* " +
                "FROM datasheet ds " +
                "INNER JOIN snack s ON datasheet.dsSnackId = s.snackId " +
                "INNER JOIN ingredient i ON ds.dsIngredientId = i.ingredientId " +
                "WHERE s.snackTitle LIKE ? OR i.ingredientName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchTerm + "%");
            statement.setString(2, "%" + searchTerm + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DataSheet dataSheet = new DataSheet();
                    dataSheet.setDsSnackId(resultSet.getInt("dsSnackId"));
                    dataSheet.setDsIngredientId(resultSet.getInt("dsIngredientId"));
                    dataSheet.setDsQuantity(resultSet.getInt("dsQuantity"));
                    dataSheet.setDsTotalCost(resultSet.getFloat("dsTotalCost"));
                    dataSheet.setDsStatus(resultSet.getString("dsStatus"));

                    Snack snack = new Snack();
                    snack.setId(resultSet.getInt("snackId"));
                    snack.setSnackTitle(resultSet.getString("snackTitle"));
                    snack.setSnackSellingPrice(resultSet.getFloat("snackSellingPrice"));
                    snack.setSnackDescription(resultSet.getString("snackDescription"));
                    snack.setSnackImageUrl(resultSet.getString("snackImageUrl"));
                    snack.setSnackStatus(resultSet.getString("snackStatus"));

                    Ingredient ingredient = new Ingredient();
                    ingredient.setId(resultSet.getInt("ingredientId"));
                    ingredient.setIngredientName(resultSet.getString("ingredientName"));
                    ingredient.setIngredientMinQuantity(resultSet.getFloat("ingredientMinQuantity"));
                    ingredient.setIngredientUnitOfMeasure(resultSet.getString("ingredientUnitOfMeasure"));
                    ingredient.setIngredientUnitCost(resultSet.getFloat("ingredientUnitCost"));
                    ingredient.setIngredientStatus(resultSet.getString("ingredientStatus"));
                    ingredient.setIngredientStock(resultSet.getFloat("ingredientStock"));
                    ingredient.setIngredientStockStatus(resultSet.getString("ingredientStockStatus"));

                    dataSheet.setSnack(snack);
                    dataSheet.setIngredient(ingredient);
                    dataSheets.add(dataSheet);
                }
            }
        }
        return dataSheets;
    }

    public void addDataSheet(DataSheet dataSheet) throws SQLException {
        String query = "INSERT INTO datasheet (dsSnackId, dsIngredientId, dsQuantity, dsTotalCost, dsStatus) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataSheet.getDsSnackId());
            statement.setInt(2, dataSheet.getDsIngredientId());
            statement.setInt(3, dataSheet.getDsQuantity());
            statement.setFloat(4, dataSheet.getDsTotalCost());
            statement.setString(5, dataSheet.getDsStatus());
            statement.executeUpdate();
        }
    }

    public void updateDataSheet(DataSheet dataSheet) throws SQLException {
        String query = "UPDATE datasheet SET dsSnackId = ?, dsIngredientId = ?, dsQuantity = ?, " +
                "dsTotalCost = ?, dsStatus = ? WHERE dsSnackId = ? AND dsIngredientId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataSheet.getDsSnackId());
            statement.setInt(2, dataSheet.getDsIngredientId());
            statement.setInt(3, dataSheet.getDsQuantity());
            statement.setFloat(4, dataSheet.getDsTotalCost());
            statement.setString(5, dataSheet.getDsStatus());
            statement.setInt(6, dataSheet.getDsSnackId());
            statement.setInt(7, dataSheet.getDsIngredientId());
            statement.executeUpdate();
        }
    }

    public void deleteDataSheet(int snackId, int ingredientId) throws SQLException {
        String query = "DELETE FROM datasheet WHERE dsSnackId = ? AND dsIngredientId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, snackId);
            statement.setInt(2, ingredientId);
            statement.executeUpdate();
        }
    }
}