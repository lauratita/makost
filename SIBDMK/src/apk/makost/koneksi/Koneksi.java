/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apk.makost.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Neendy's
 */
public class Koneksi {
    public static Connection conn;
    public static Statement stm;
//    method main
//    public static void main(String[] args){
//        try{
//            String url = "jdbc:mysql://localhost:3306/dv_kos"; //link database
//            String user = "root"; //nama database
//            String pass = ""; //password database
//            Class.forName("com.mysql.cj.jdbc.Driver"); //memanggil driver mysql dari jdbc (connectivity)
//            
//            conn = DriverManager.getConnection(url, user, pass); //menghubungkan url, user, pass
//            stm = conn.createStatement(); //menjalankan perintal sql
//            System.out.println("Koneksi Berhasil");
//        } catch (Exception e){
//            System.out.println("Koneksi Gagal" + e.getMessage());
//        }
//    }
    
//    method private
    
    
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/db_kos"; //url database
            String user = "root"; //nama user db
            String pass = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (Exception e){
//            perintah koneksi gagal ke db
            System.err.println("Koneksi gagal " + e.getMessage());
        }
        return mysqlconfig;
    }
}
