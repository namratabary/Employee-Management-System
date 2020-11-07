import java.net.*;
import javax.swing.JFrame;
import javax.swing.*; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class View extends JFrame {
private JTextField jtf;
private JLabel searchLbl;
private TableModel dtm;
private JTable table;
private TableRowSorter sorter;
private JScrollPane jsp;
JButton btnReload;

View(){


setLayout(new BorderLayout());
setTitle("EMPLOYEE DATA");
jtf = new JTextField(15);
searchLbl = new JLabel("Search");
btnReload = new JButton("Reload");





Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

try{




java.util.List<Employee> emp = new ArrayList<>();
emp = session.createQuery("from Employee").list();

String[] cols= {"EMPLOYEE ID", "EMPLOYEE NAME", "EMPLOYEE SALARY"};
DefaultTableModel dtm = new DefaultTableModel(cols,0);



for(Employee e : emp ) {
String[] data= {Integer.toString(e.getId()),e.getName(),Double.toString(e.getSalary())};
dtm.addRow(data);
}
sorter = new TableRowSorter<>(dtm);
table = new JTable(dtm);
table.setRowSorter(sorter);
table.setPreferredSize(new Dimension(130, 4000));
setLayout(new FlowLayout(FlowLayout.CENTER));
jsp = new JScrollPane(table);
jsp.setPreferredSize( new Dimension( 400, 260 ) );
jsp.setBounds(10, 150, 1320, 2450);
jsp.setVisible(true);

add(searchLbl);
add(jtf);
add(btnReload);
jtf.getDocument().addDocumentListener(new DocumentListener() {
public void insertUpdate(DocumentEvent e) {
search(jtf.getText());
}
public void removeUpdate(DocumentEvent e) {
search(jtf.getText());
}
public void changedUpdate(DocumentEvent e) {
search(jtf.getText());
}
public void search(String str) {
     if (str.length() == 0) {
        sorter.setRowFilter(null);
     } 
     else {
     sorter.setRowFilter(RowFilter.regexFilter(str));
     }
}
});
add(jsp, table);
setResizable(false);
setSize(475, 350);
setDefaultCloseOperation(DISPOSE_ON_CLOSE);
setLocationRelativeTo(null);
setResizable(false);
setVisible(true);
   }


catch (Exception e)
{
System.out.println("Error " + e);
}

finally{
session.close();
}
btnReload.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
View v = new View();
dispose();

}
});
}   
}

