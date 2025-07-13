package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            ));
        }
        return items;
    }

    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        Boolean done=SQLUtil.execute(
                "INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?, ?, ?, ?)",
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        );

        return false;
    }
    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        boolean DONE= SQLUtil.execute(
                "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?",
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand(),
                itemDTO.getCode()
        );
        return DONE;
    }
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE code = ?", code);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            String code = rst.getString("code");
            int newItemCode = Integer.parseInt(code.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemCode);
        } else {
            return "I00-001";
        }
    }


    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code = ?", code);
        return rst.next();
    }



    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code = ?", code);
        if (rst.next()) {
            return new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
        }
        return null;
    }

    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
        return search(code);
    }

}