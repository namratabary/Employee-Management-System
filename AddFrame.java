import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*; 

class Add extends JFrame {
Container c;
JLabel lblId, lblName, lblSalary;
JTextField txtId, txtName , txtSalary ;
JButton btnSave,btnBack;

Add()
{



c = getContentPane();
c.setLayout(null);







lblId = new JLabel("Enter Id");
lblName = new JLabel("Enter Name");
lblSalary = new JLabel("Enter Salary");

txtId = new JTextField(10);
txtId.setOpaque(true);

txtName = new JTextField(10);


txtSalary = new JTextField(10);
txtSalary.setOpaque(true);

btnSave = new JButton("Save");
btnSave.setBackground(Color.WHITE);
btnSave.setContentAreaFilled(false);
btnSave.setOpaque(true);

btnBack = new JButton("Back");
btnBack.setBackground(Color.WHITE);
btnBack.setContentAreaFilled(false);
btnBack.setOpaque(true);
btnSave.setBounds(85,300,100,25);
btnBack.setBounds(215,300,100,25);
txtId.setBounds(220,60,100,20);
txtName.setBounds(220,110,100,20);
txtSalary.setBounds(220,160,100,20);
lblId.setBounds(80,60,100,20);
lblName.setBounds(80,110,100,20);
lblSalary.setBounds(80,160,100,20);


c.add(lblId);
c.add(txtId);
c.add(lblName);
c.add(txtName);
c.add(lblSalary);
c.add(txtSalary);

c.add(btnSave);
c.add(btnBack);



btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();
Transaction t = null;
try{
t = session.beginTransaction();

Employee s = new Employee();

String iden = txtId.getText();
String name = txtName.getText();
String sal = txtSalary.getText(); 

if(iden.length() == 0)
{
JOptionPane.showMessageDialog(c,"Please enter id");
txtId.setText("");
txtId.requestFocus();
return;
}

int lc =0 , dc =0, oc =0;
for(int i  = 0; i< iden.length() ; i++)
{
char ch = iden.charAt(i);
if ((ch>= 'A' && ch <= 'Z') || (ch >='a' && ch <='z'))
	lc++;
else if (ch>= '0' && ch <= '9')
	dc++;
else 
	oc++;
}

if(oc > 0 ) 
{
JOptionPane.showMessageDialog(c,"Invalid Id");
txtId.setText("");
txtId.requestFocus();
txtName.setText("");
txtSalary.setText("");
return;

}



if(lc != 0) 
{
JOptionPane.showMessageDialog(c,"Enter id properly");
txtId.setText("");
txtId.requestFocus();
txtName.setText("");
txtSalary.setText("");
return;
}
int rno = Integer.parseInt(iden);
if(dc != 0) 
{
if(rno <= 0)
 {
JOptionPane.showMessageDialog(c,"Wrong value for Id");
txtId.setText("");
txtId.requestFocus();
txtName.setText("");
txtSalary.setText("");
return;
}
}


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

else{
JOptionPane.showMessageDialog(c, "Invalid Name ");

txtName.setText("");
txtName.requestFocus();
return;

}
}}
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
s.setId(rno); 
s.setName(name);
s.setSalary(salary);

session.save(s);

if (j==1){
t.commit();
JOptionPane.showMessageDialog(c,"Record Added");
txtId.setText("");
txtId.requestFocus();
txtName.setText("");
txtSalary.setText("");
}
}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Improper values");
t.rollback();

txtId.setText("");
txtName.setText("");
txtSalary.setText("");
txtId.requestFocus();

}

catch(org.hibernate.exception.ConstraintViolationException e){
JOptionPane.showMessageDialog(c,"ID ALREADY EXISTS. TRY AGAIN! ");
txtId.setText("");
txtId.requestFocus();
txtName.setText("");
txtSalary.setText("");

t.rollback();
}


catch(Exception e){
txtId.setText("");
txtId.requestFocus();
txtName.setText("");
txtSalary.setText("");

t.rollback();
}
finally{
session.close();
}



}
});




btnBack.addActionListener(new ActionListener () {
public void actionPerformed(ActionEvent ae)
{


dispose();
}
});
setResizable(false);
setTitle("Add Employee");
setSize(400,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setVisible(true);

}
}

