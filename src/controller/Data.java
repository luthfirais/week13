/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import model.Biodata;

/**
 *
 * @author Aii Rynn
 * Luthfi Rais - 6706210132
 * 
 */
public class Data {
    private Connection con;
    private final Koneksi koneksiData = new Koneksi();
    
    // Cek apakah username sudah ada atau belum
    public boolean cekUsername(String nama){
        PreparedStatement ps;
        ResultSet rs;
        boolean checkNama = false;
        
        // Quey SQL untuk mengecek nama
        String query = 
                "SELECT * FROM 'data' WHERE 'id' = ?";
        
        // melakukan eksepsi utk mengetahui jika ada error (try & catch)
        try{
          // Menyiapkan database / memanipulasi data utk dikirim ke database utk dieksekusi
          ps = Koneksi.getConnection().prepareStatement(query);
          ps.setString(1, nama);
          
          // pengeksekusian Query
          rs = ps.executeQuery();
          
          // pengecekan apakah username nya ada atau tidak
          if(rs.next()){
              checkNama = true;
          }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        // mengembalikan nilai utk pengecekan nama
        return checkNama;
    }
    
    // Fungsi TAMPIL DATA
    public ArrayList<Biodata> tampilData() throws SQLException {
        ArrayList<Biodata> tampil = new ArrayList<>();
        
        // membuka koneksi
        con = koneksiData.getConnection();
        
        // membuat query utk tampil data biodata
        String kueri = "SELECT * FROM data";
        PreparedStatement ps = con.prepareStatement(kueri);
        
        // mengeksekusi query
        ResultSet rs = ps.executeQuery();
        
        // melakukan perulangan utk menampilkan seluruh data yg ada di tabel data
        while (rs.next()) {
            String id = rs.getString("id");
            String nama = rs.getString("nama");
            String umur = rs.getString("umur");
            String alamat = rs.getString("alamat");
            
            Biodata listdata = new Biodata(id, nama, umur, alamat);
            tampil.add(listdata);
        }
        
        
        // menutup result set, PreparedStatement dan koneksi
        rs.close();
        ps.close();
        con.close();
        
        // mengembalikan nilai
        return tampil;
    } 
    
    // Fungsi TAMBAH DATA
    public boolean tambahData(Biodata data) throws SQLException {
        
        // membuka koneksi ke database
        con = koneksiData.getConnection();
        
        // membuat query utk tambah data akun
        String kueri = 
                "INSERT INTO data (id, nama, umur, alamat) VALUES (?,?,?,?)";
        
        
        // Meniapkan dataabase / memanipulasi data utk dikirim ke database utk dieksekusi (Prepared Statement)
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, data.getId());
        ps.setString(2, data.getNama());
        ps.setString(3, data.getUmur());
        ps.setString(4, data.getAlamat());
            
        // Mengeksekusi query
        int rowAffected = ps.executeUpdate();
            
        // Menutup koneksi
        ps.close();
        con.close();
        
        // Mengembalikan nilai data utk dirubah ke database MySQL
        return rowAffected == 1;
    }
    
    // Fungsi UBAH DATA
    public boolean ubahData (Biodata data) throws SQLException{
        
        // Membuka koneksi ke database
        con = koneksiData.getConnection();
        
        // menyiapkan database / memanipuasi data utk dikirim ke database utk di eksekusi (Prepared Statement)
        String kueri = "UPDATE data SET nama = ?, umur = ?, alamat = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(4, data.getId());
        ps.setString(1, data.getNama());
        ps.setString(2, data.getUmur());
        ps.setString(3, data.getAlamat());
        
        // Mengeksekusi query
        int rowAffected = ps.executeUpdate();
        
        // MenutupPreparedStatement & koneksi
        ps.close();
        con.close();
        
        // Mengembalikan nilai datautk dirubah ke database query
        return rowAffected == 1;
    }
    
    // Fungsi HAPUS DATA
    public boolean hapusData(String id) throws SQLException {
        
        // membuka koneksi ke database
        con = koneksiData.getConnection();
        
        // Menyiapkan database / memanipulasi data utk dikirim ke database utk di eksekusi
        String kueri = "DELETE FROM data WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, id);
        
        // mengeksekusi query
        int rowAffected = ps.executeUpdate();
        
        // Menutup PreparedStatement & koneksi
        ps.close();
        con.close();
        
        // Mengembalikan nila data utk dirubah ke database MySQL
        return rowAffected == 1;
    }
}
