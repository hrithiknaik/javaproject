import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel lblid, lblname, lblsal;
JTextField txtid, txtname, txtsal;
JButton btnSave, btnBack;
int id;
String name;
int salary;

UpdateFrame()
{
c= getContentPane();
c.setLayout(new FlowLayout());
lblid= new JLabel("Id");
lblname = new JLabel("Name");
lblsal = new JLabel("Salary");
txtid = new JTextField(15);
txtname = new JTextField(15);
txtsal = new JTextField(15);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

c.add(lblid);
c.add(txtid);
c.add(lblname);
c.add(txtname);
c.add(lblsal);
c.add(txtsal);
c.add(btnSave);
c.add(btnBack);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a = new MainFrame();
dispose();
}
});

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;
try
{
t = session.beginTransaction();

try {
id = Integer.parseInt(txtid.getText());
}
catch(NumberFormatException nfe) {
JOptionPane.showMessageDialog(c,"Please Enter Valid Employee Id");
txtid.setText("");
txtid.requestFocus();
}


Employee e = (Employee)session.get(Employee.class, id);
int i=0;
int j=0;
if(e != null)

try{
name = txtname.getText();

if(name.length() < 3)
	throw new IllegalAccessException();
}
catch(NullPointerException npe){
i=1;
j--;
JOptionPane.showMessageDialog(c,"Name Should only include Alphabets");
txtname.setText("");
txtname.requestFocus();
}
catch(IllegalAccessException iae) {
i=2;
j--;
JOptionPane.showMessageDialog(c,"Name Should be more than two letters");
txtname.setText("");
txtname.requestFocus();
}
if(i==0){
e.setName(name);
}

try {
salary = Integer.parseInt(txtsal.getText());
if (salary < 8000)
	throw new NullPointerException();
}
catch(NullPointerException npe) {
j--;
JOptionPane.showMessageDialog(c,"Minimum Salary Should Be 8000");
txtsal.setText("");
txtsal.requestFocus();
}
catch(NumberFormatException nfe) {
j--;
JOptionPane.showMessageDialog(c,"Please Enter Valid Salary");
txtsal.setText("");
txtsal.requestFocus();
}

e.setSalary(salary);
session.save(e);		
t.commit();
if(j==0)
{
JOptionPane.showMessageDialog(c, "Record updated");

MainFrame a = new MainFrame();
dispose();
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(c, "entered id doesn't exist");
txtid.setText("");
txtid.requestFocus();
}


finally{
session.close();
}
}
});
setSize(275,300);
setTitle("Update Employee details");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}
}

