package com.example.layeredarchitecture.BO;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;
    public boolean saveCus(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException;
    public boolean updateCUs(CustomerDTO customerDTO) throws  SQLException,ClassNotFoundException;
    public boolean deleteCUs(String id) throws  SQLException,ClassNotFoundException;
    public boolean existCus(String id) throws  SQLException,ClassNotFoundException;
    public String generateNewCusId() throws SQLException, ClassNotFoundException ;
}
