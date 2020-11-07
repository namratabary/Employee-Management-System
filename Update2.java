import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class Update2 extends JFrame{
Container c;

JButton btnUpdate, btnBack;
JTextField txtName, txtSalary;
JLabel lblName,lblSalary;
int id;

Update2(int id){
this.id = id; 
c = getContentPane();
c.setLayout(null);
lblName = new JLabel("Enter Name to Update");
lblSalary = new JLabel("Enter Salary to Update");

btnUpdate = new JButton("UPDATE");
btnBack = new JButton("BACK");
txtName = new JTextField(10);
txtSalary = new JTextField(10);

btnUpdate.setBounds(70,190,100,30);
btnBack.setBounds(240,190,100,30);
txtName.setBounds(235,50,100,25);
txtSalary.setBounds(235,90,100,25);
lblName.setBounds(70,50,140,25);
lblSalary.setBounds(70,90,140,25);

c.add(lblName);
c.add(lblSalary);

c.add(txtName);
c.add(txtSalary);
c.add(btnUpdate);
c.add(btnBack);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
Update m = new Update();
dispose();

}
});

btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;
try{
t = session.beginTransaction();
Employee e = (Employee)session.get(Employee.class, id);
String name = txtName.getText();
String sal = txtSalary.getText(); 

int i = 0;
int j = 0;
if(name.length()>=2)
{
while(i < name.length())
{
if ((name.charAt(i) <='z' && name.charAt(i) >='a') || (name.charAt(i) <='Z' && name.charAt(i) >='A') || name.charAt(i) == ' ')
{
i++;


}

else {
JOptionPane.showMessageDialog(c, "Invalid Name ");

txtName.setText("");
txtName.requestFocus();
return;

}
}
}
else
{
JOptionPane.showMessageDialog(c, " Name Too Short ");
txtName.setText("");
txtName.requestFocus();
return;
}



Double salary = 0.0;

if(i==name.length())
{
if(sal.length() == 0) {
JOptionPane.showMessageDialog(c, "Enter proper Salary");

txtSalary.setText("");
txtSalary.requestFocus();
return;


}
int g = 0;
if ((sal.charAt(g) <='z' && sal.charAt(g) >='a') || (sal.charAt(g) <='Z' && sal.charAt(g) >='A') || sal.charAt(g) == ' ')
{
JOptionPane.showMessageDialog(c,"Enter salary properly");
txtSalary.setText("");
txtSalary.requestFocus();
return;

}


salary = Double.parseDouble(sal);
if (salary<8000.0){
JOptionPane.showMessageDialog(c, "Wrong Salary. As per Govt, MINIMUM WAGE IS 8000");

txtSalary.setText("");
txtSalary.requestFocus();
return;

}

else {j++;
}
}

e.setName(name);
e.setSalary(salary);

session.save(e);

if (j==1){
t.commit();
JOptionPane.showMessageDialog(c, "Record Updated");
Update m = new Update();
dispose();
}
}
catch(NumberFormatException e )
{
JOptionPane.showMessageDialog(c, "Invalid Salary");
txtSalary.setText("");
txtSalary.requestFocus();


}
catch(Exception e)
{
JOptionPane.showMessageDialog(c, "Error "+ e);
txtName.setText("");
txtName.requestFocus();
txtSalary.setText("");


}
finally{
session.close();
}
 





}
});
setResizable(false);
setTitle("Update Frame");
setSize(400,280);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setVisible(true);
}
}

