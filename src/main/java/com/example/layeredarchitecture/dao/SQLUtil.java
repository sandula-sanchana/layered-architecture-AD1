package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
   public static <T> T execute(String sql,Object... obj) throws SQLException, ClassNotFoundException {
       Connection connection= DBConnection.getDbConnection().getConnection();
       PreparedStatement statement=connection.prepareStatement(sql);
       for(int i=0;i<obj.length;i++){
           statement.setObject(i+1,obj[i]);
       }

       if(sql.startsWith("SELECT")){
           return  (T)statement.executeQuery();

       }else {
           return  (T)(Boolean) (statement.executeUpdate()>0);
       }
   }
}
