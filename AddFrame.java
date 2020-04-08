import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblid,lblname,lblsalary;
JTextField txtid,txtname,txtsalary;
JButton btnSave,btnBack;

AddFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblid = new JLabel("Id");
lblname = new JLabel("name");
lblsalary = new JLabel("salary");
txtid = new JTextField(15);
txtname = new JTextField(15);
txtsalary = new JTextField(15);
btnSave = new JButton("Save");
btnBack = new JButton("Back");
c.add(lblid);
c.add(txtid);
c.add(lblname);
c.add(txtname);
c.add(lblsalary);
c.add(txtsalary);
c.add(btnSave);
c.add(btnBack);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
int i = 0;
String a1 = txtid.getText();
String a2 = txtname.getText();
String a3 = txtsalary.getText();
int j=0;
if(a1.isEmpty()||a2.isEmpty()||a3.isEmpty())
{
JOptionPane.showMessageDialog(c,"enter all values");
txtid.setText("");
txtid.requestFocus();
}
else
{
j++;
}
if(j==1)
{
try
{
Integer.parseInt(a1);
i++;
}
catch(NumberFormatException ne)
{
JOptionPane.showMessageDialog(c,"please enter integer values");
txtid.setText("");
txtid.requestFocus();
}

if(Integer.parseInt(a1) < 1)
{
JOptionPane.showMessageDialog(c,"please enter positive integer");
i--;
txtid.setText("");
txtid.requestFocus();
}
if(Integer.parseInt(a3) < 8000)
{
JOptionPane.showMessageDialog(c,"salary must be greater than 8000");
i--;
txtsalary.setText("");
txtsalary.requestFocus();
}
try
{
Integer.parseInt(a3);
i++;
}
catch(NumberFormatException ne)
{
JOptionPane.showMessageDialog(c,"Invalid salary");
txtsalary.setText("");
txtsalary.requestFocus();
}
try
{
if(txtname.getText().matches("[A-z][a-z]*"))
{
}
else
{
JOptionPane.showMessageDialog(c,"invalid name format");
i--;
txtname.setText("");
txtname.requestFocus();
}
if(txtname.getText().length() < 2)
{
JOptionPane.showMessageDialog(c,"invalid name");

txtname.setText("");
txtname.requestFocus();
}
else
{
i++;
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(c,"invalid name");

txtname.setText("");
txtname.requestFocus();
}
}

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();
Transaction t = null;
try
{
t = session.beginTransaction();
Employee e = new Employee();
int id = Integer.parseInt(txtid.getText());
String name = txtname.getText();
int salary = Integer.parseInt(txtsalary.getText());

e.setId(id);
e.setName(name);
e.setSalary(salary);
session.save(e);

t.commit();
}
catch(Exception e)
{
t.rollback();
System.out.println(e);
}
finally{
session.close();
}
if(i==3)
{
JOptionPane.showMessageDialog(c,"record added");

MainFrame a = new MainFrame();
dispose();
}
}
});

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a = new MainFrame();
dispose();
}
});
setTitle("Add");
setSize(220,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}

