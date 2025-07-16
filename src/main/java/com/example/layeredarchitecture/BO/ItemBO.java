package com.example.layeredarchitecture.BO;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;
    public boolean saveItem(ItemDTO itemDTO) throws SQLException,ClassNotFoundException;
    public boolean updateItem(ItemDTO itemDTO) throws  SQLException,ClassNotFoundException;
    public boolean deleteItem(String id) throws  SQLException,ClassNotFoundException;
    public boolean existItem(String id) throws  SQLException,ClassNotFoundException;
    public String generateNewItemId() throws SQLException, ClassNotFoundException ;
    public ItemDTO searchItem(String newValue) throws SQLException, ClassNotFoundException ;
}
