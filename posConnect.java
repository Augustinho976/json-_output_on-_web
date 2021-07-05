package pos_json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class posConnect {
    Connection conn=null;

    public  void DbConnect(){
        try {
            String url = "jdbc:postgresql://localhost:5432/pos";
            conn = DriverManager.getConnection(url, "postgres","");


        }catch (SQLException e){
            System.out.println("Sql connection error:"+e);

        }
    }

    public  String getProducts(){
        JSONArray jproducts= new JSONArray();
        String query = "select id, product_name, price from products";
        try {
            Statement statement= conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {

                JSONObject jproduct = new JSONObject();
                jproduct.put("id",rs.getString("id"));
                jproduct.put("product_name", rs.getString("product_name"));
                jproduct.put("price", rs.getString("price"));

                jproducts.add(jproduct);


            }


        }catch (SQLException ex){
            System.out.println("Sql connection error"+ ex);

        }
        return jproducts.toString();
    }
    public void close() {
        try {
            if(conn!=null)conn.close();
        }catch (SQLException ex){
            System.out.println("SQL connection error");
        }
    }
}
