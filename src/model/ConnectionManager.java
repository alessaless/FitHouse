package model;

import java.sql.*;
import java.util.*;


public class ConnectionManager {

   static Connection con;
         
   public static Connection getConnection()
   {
     
      try
      {
        
   	  	Class.forName("com.mysql.cj.jdbc.Driver");
         
         try
         {            	
            String url = "jdbc:mysql://localhost:3306/fithouse";
            String user = "root";
            String password = "caffedesign57"; 
        	con = DriverManager.getConnection(url, user, password);
             								
         }
         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
}
}