package com.example.layeredarchitecture.BO;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;
    public boolean saveCus(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException;
}
