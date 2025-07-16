package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO{
     public List<CustomDTO> getAllCustomersByOrderCount() throws SQLException ;
    List<CustomDTO> getAllCustomersItem() throws SQLException, ClassNotFoundException;
}
