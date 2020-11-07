import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.*;

class Main extends JFrame{
Container c;
JButton btnAdd, btnView, btnDelete, btnUpdate;

Main(){



c = getContentPane();
c.setLayout(null);
btnAdd = new JButton("ADD");
btnAdd.setBackground(Color.WHITE);
btnAdd.setContentAreaFilled(false);
btnAdd.setOpaque(true);
btnView = new JButton("VIEW");
 btnView.setBackground(Color.WHITE);
            
            btnView.setContentAreaFilled(false);
            btnView.setOpaque(true);
btnUpdate = new JButton("UPDATE");
 btnUpdate.setBackground(Color.WHITE);
            
            btnUpdate.setContentAreaFilled(false);
            btnUpdate.setOpaque(true);
btnDelete = new JButton("DELETE");
 btnDelete.setBackground(Color.WHITE);
            
            btnDelete.setContentAreaFilled(false);
            btnDelete.setOpaque(true);
btnAdd.setBounds(100,90,150,40);
btnView.setBounds(100,150,150,40);
btnUpdate.setBounds(100,210,150,40);
btnDelete.setBounds(100,270,150,40);





 
c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);








btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
View n = new View();

}
});

btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
Add a = new Add();

}
});


btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
Update m = new Update();

}
});


btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
Delete d = new Delete();

}
});





setResizable(false);
setTitle("E.M.S");
setSize(350,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}


 	
class MainFrame{
public static void main(String args[])
{
Main M = new Main();
}
}