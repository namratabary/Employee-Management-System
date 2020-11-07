import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Delete extends JFrame {
Container c;
JLabel lblDelete;
JTextField txtId;

JButton btnDelete, btnBack;
Delete()
{
c = getContentPane();
c.setLayout(null);

lblDelete = new JLabel("Enter Id to Delete");
txtId = new JTextField(20);
btnDelete = new JButton("Delete");
btnBack = new JButton("Back");
btnDelete.setBounds(70,180,100,30);
btnBack.setBounds(240,180,100,30);
lblDelete.setBounds(90,50,150,25);
txtId.setBounds(240,50,110,25);


c.add(lblDelete);
c.add(txtId);
c.add(btnDelete);
c.add(btnBack);



btnDelete.addActionListener(new ActionListener () {
public void actionPerformed(ActionEvent ae)
{
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;
try{
t = session.beginTransaction();
String iden = txtId.getText();
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
session.delete(e);
t.commit();
JOptionPane.showMessageDialog(c, "Record is Deleted");
txtId.setText("");
txtId.requestFocus();
}
else
{
JOptionPane.showMessageDialog(c, "ID NOT FOUND");
txtId.setText("");
txtId.requestFocus();

}
}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c, "Invalid ID");
txtId.setText("");
txtId.requestFocus();


}
catch(Exception e){
JOptionPane.showMessageDialog(c,"Error"+e);
txtId.setText("");
txtId.requestFocus();


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
setTitle("Delete Employee");
setSize(400,280);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setVisible(true);

}
}



