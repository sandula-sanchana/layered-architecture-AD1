package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public boolean saveOrderDetails(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {


        for (OrderDetailDTO detail : orderDetails) {
           Boolean done= SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",orderId,detail.getItemCode(), detail.getUnitPrice(),detail.getQty());

            if (!done) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getDbConnection().getConnection();
        ItemDAOImpl itemDAO=new ItemDAOImpl();
        for (OrderDetailDTO detail : orderDetails) {
            ItemDTO item = itemDAO.findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
            Boolean done=SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getCode());

            if (!done) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        }
        return true;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDetailDTO search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
