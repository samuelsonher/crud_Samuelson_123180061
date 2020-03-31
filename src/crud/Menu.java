/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Hp
 */
class MenuM extends JFrame implements ActionListener{
    private JLabel lJudul;
    private JButton btnInput;
    private JButton btnRead;
    
    public MenuM(){
        lJudul = new JLabel("Menu");
        btnInput = new JButton("Input Data");
        btnRead = new JButton("Lihat Data");
        setLayout(null);
        add(lJudul);
        add(btnInput);
        add(btnRead);
        setSize(400,200);
        btnInput.addActionListener(this);
        btnRead.addActionListener(this);
        lJudul.setBounds(190,10,120,20);
        btnInput.setBounds(130,50,150,20);
        btnRead.setBounds(130,90,150,20);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== btnInput) {
            new Input();
        }
       if (e.getSource()== btnRead) {
            new Read();
        }
    }

    
}
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MenuM();
    }
    
}


