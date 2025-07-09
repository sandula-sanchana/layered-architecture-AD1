package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public class PlaceOrderDAOImpl implements  PlaceOrderDAO{
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection =connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderDTO.getOrderId());
        stm.setDate(2, Date.valueOf(orderDTO.getOrderDate()));
        stm.setString(3, orderDTO.getCustomerId());
        return stm.executeUpdate()>0;
    }

    public boolean existCustomer(String code) throws SQLException, ClassNotFoundException {
        CustomerDAOImpl customerDAO=new CustomerDAOImpl();
        return customerDAO.existCustomer(code);
    }

    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ItemDAOImpl itemDAO=new ItemDAOImpl();
        return itemDAO.existItem(code);
    }

}