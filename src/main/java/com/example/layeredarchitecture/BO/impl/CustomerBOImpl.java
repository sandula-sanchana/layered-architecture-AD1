package com.example.layeredarchitecture.BO.impl;

import com.example.layeredarchitecture.BO.CustomerBO;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO=new CustomerDAOImpl();

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean saveCus(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
       return customerDAO.save(customerDTO);
    }

    @Override
    public boolean updateCUs(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customerDTO);
    }

    @Override
    public boolean deleteCUs(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean existCus(String id) throws SQLException, ClassNotFoundException {
        return  customerDAO.exist(id);
    }

    @Override
    public String generateNewCusId() throws SQLException, ClassNotFoundException {
        return "";
    }


}
