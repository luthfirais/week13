/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minggu13;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;

/**
 *
 * @author Aii Rynn
 * Luthfi Rais - 6706210132
 * 
 */
public class LatihanDB {
    public static void main(String[] raraa) {
        // Mendeklarasikan & Instansiasi DataSource
        String DB_NAME = "jdbc:mysql://localhost:3306/nauradb";
        String DB_USER = "root";
        String DB_PASS = "";
        
        // setUrl untuk datasource
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUrl(DB_NAME);
        datasource.setUser(DB_USER);
        datasource.setPassword(DB_PASS);
        
        try{
            Connection koneksi = datasource.getConnection();
            // System.out.println("Koneksi Berhasil : " + koneksi);
            String queryInsert = "INSERT INTO data (id,nama,umur,alamat) values(?,?,?,?)";
            
            PreparedStatement ps = koneksi.prepareStatement(queryInsert);
            ps.setString(1, "NOA");
            ps.setString(2, "Naura");
            ps.setInt(3, 16);
            ps.setString(4, "Bandung");
            
            ps.execute();
            
            ps.close();
            koneksi.close();
            
        } catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
    }
    }
}
