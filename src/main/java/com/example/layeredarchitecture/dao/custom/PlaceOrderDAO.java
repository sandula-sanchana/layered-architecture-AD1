package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public interface PlaceOrderDAO {
    public String generateNewOrderId() throws SQLException, ClassNotFoundException ;

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String code) throws SQLException, ClassNotFoundException ;

    public boolean existItem(String code) throws SQLException, ClassNotFoundException ;
}
