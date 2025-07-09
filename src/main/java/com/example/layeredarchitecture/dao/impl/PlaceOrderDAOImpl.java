package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.PlaceOrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        if (rst.next()) {
            String lastId = rst.getString("oid");
            int newId = Integer.parseInt(lastId.replace("OID-", "")) + 1;
            return String.format("OID-%03d", newId);
        } else {
            return "OID-001";
        }
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderDTO.getOrderId(),
                Date.valueOf(orderDTO.getOrderDate()),
                orderDTO.getCustomerId()
        );
    }

    @Override
    public boolean existCustomer(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer WHERE id=?", code);
        return rst.next();
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return rst.next();
    }
}
