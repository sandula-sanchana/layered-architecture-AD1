package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            customers.add(new CustomerDTO(id, name, address));
        }
        return customers;
    }

    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        return  SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),
                customerDTO.getName(), customerDTO.getAddress() );
    }
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        return  SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),
                customerDTO.getAddress(), customerDTO.getId() );


    }
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
    }
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return  SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {

        ResultSet rst=SQLUtil.execute("SELECT * FROM Customer WHERE id=?",newValue + "");

            CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
            return customerDTO;


    }
}