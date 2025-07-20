package com.example.layeredarchitecture.BO;

import com.example.layeredarchitecture.BO.impl.CustomerBOImpl;
import com.example.layeredarchitecture.BO.impl.ItemBOImpl;
import com.example.layeredarchitecture.BO.impl.PlaceOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory==null)?new BOFactory():boFactory;
    }

    public enum BOType{
        CUSTOMER,ITEM,PLACE_ORDER;
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case ITEM :
                return new ItemBOImpl();

            case CUSTOMER:
                return new CustomerBOImpl();

            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }



}
