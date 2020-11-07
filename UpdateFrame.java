import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import org.hibernate.*;
import org.hibernate.cfg.*;


class Update extends JFrame{
Container c;

JButton btnUpdate,btnBack;
JTextField txtId;
JLabel lblUpdate;

Update(){
c = getContentPane();
c.setLayout(null);


lblUpdate = new JLabel("Enter Id to Update");
btnUpdate = new JButton("FIND");
btnBack = new JButton("BACK");
txtId = new JTextField(10);

btnUpdate.setBounds(70,180,100,30);
btnBack.setBounds(240,180,100,30);
txtId.setBounds(240,50,110,25);
lblUpdate.setBounds(90,50,150,25);
c.add(lblUpdate);
c.add(txtId);
c.add(btnUpdate);
c.add(btnBack);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){

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
String iden = txtId.getText();

/* else if(id <= 0) 
{
JOptionPane.showMessageDialog(c,"Improper Id");
txtId.setText("");
txtId.requestFocus();
return;


}
else
{
JOptionPane.showMessageDialog(c, "ID NOT FOUND");
txtId.setText("");
txtId.requestFocus();

}
}
*/

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
return;

}



if(lc != 0) 
{
JOptionPane.showMessageDialog(c,"Enter id properly");
txtId.setText("");
txtId.requestFocus();
return;
}
int id = Integer.parseInt(iden);
if(dc != 0) 
{
if(id <= 0)
 {
JOptionPane.showMessageDialog(c,"Wrong value for Id");
txtId.setText("");
txtId.requestFocus();
return;
}
}
Employee e = (Employee)session.get(Employee.class, id);
if (e!=null)
{
Update2 m = new Update2(id);
dispose();
}
else
{
JOptionPane.showMessageDialog(c, "ID NOT FOUND");
txtId.setText("");
txtId.requestFocus();

}

}


catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(c,"Invalid ID");
txtId.setText("");
txtId.requestFocus();
return;

}
catch(Exception e){
JOptionPane.showMessageDialog(c,"Error\n"+e);
txtId.setText("");
txtId.requestFocus();

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
