package crud;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Hp
 */
public class Read extends JFrame implements ActionListener{
    String[][] data = new String[500][3];
    String[] kolom = {"Nim","Nama","Alamat"};
    float panja;
    JTextField tfCari;
    JButton btnDelete, btnUpdate;
    JTable tabel;
    JScrollPane scrollPane;
    String DBurl = "jdbc:mysql://localhost/praktikum";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;

    public Read(){
        setTitle("Data Mahasiswa!");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            statement = koneksi.createStatement();
            String sql = "select * from mahasiswa";
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while (resultSet.next()){
                data[p][0] = resultSet.getString("nim");
                data[p][1] = resultSet.getString("nama");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();
            koneksi.close();
            new Menu();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
        tabel = new JTable(data, kolom);
        tfCari = new JTextField(9);
        btnDelete = new JButton("Hapus");
        btnUpdate = new JButton("Ubah");
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        tfCari.addActionListener(this);
        scrollPane = new JScrollPane(tabel);

        setLayout(new FlowLayout());
        add(scrollPane);
        add(tfCari);
        add(btnDelete);
        add(btnUpdate);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnDelete) {
            Delete(); 
        }
        if (e.getSource()==btnUpdate) {
            panja = Float.valueOf(tfCari.getText());
            System.out.println(panja);
            new Update(); 
        }
    }
    
    private void Delete(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl,DBusername, DBpassword);
            statement = koneksi.createStatement();
            statement.executeUpdate("delete from mahasiswa where nim = '"+ tfCari.getText() + "'" );
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!", "Hasil",JOptionPane.INFORMATION_MESSAGE);            
            statement.close();
            koneksi.close();
            new MenuM();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data GagalDihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
