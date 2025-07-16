package com.example.layeredarchitecture.BO.impl;

import com.example.layeredarchitecture.BO.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.dao.custom.PlaceOrderDAO;
import com.example.layeredarchitecture.dao.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.dao.impl.PlaceOrderDAOImpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    PlaceOrderDAO placeOrderDAO=(PlaceOrderDAO) DAOFactory.getObject().getDAO(DAOFactory.DAOTypes.ORDER);
    CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getObject().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO=(ItemDAO) DAOFactory.getObject().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO=(OrderDetailDAO) DAOFactory.getObject().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return placeOrderDAO.generateNewOrderId();
    }

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection=null;

        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false); // Begin transaction


            if (!placeOrderDAO.saveOrder(new OrderDTO(orderId, orderDate, customerId))) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }



            if (!orderDetailDAO.saveOrderDetails(orderId, orderDetails)) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }


            if (orderDetailDAO.updateQty(orderDetails)){


                connection.commit(); // All successful
                connection.setAutoCommit(true);
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    public boolean existCus(String id) throws SQLException, ClassNotFoundException {
        return  customerDAO.exist(id);
    }

    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }



    @Override
    public CustomerDTO serchCustomer(String code) throws SQLException, ClassNotFoundException {
        return customerDAO.search(code);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

}
