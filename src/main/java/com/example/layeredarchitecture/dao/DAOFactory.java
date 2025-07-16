package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.impl.*;

public class DAOFactory {

    private static DAOFactory df;

    private DAOFactory(){

    }

    public static DAOFactory getObject(){
       if(df==null){
           df=new DAOFactory();
           return df;
       }
       return df;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch(daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new PlaceOrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
