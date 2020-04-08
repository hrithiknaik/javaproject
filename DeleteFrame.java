import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel lblID;
JButton btnSave, btnBack;
JTextField txtID;
int id;


DeleteFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblID = new JLabel("Id");
txtID = new JTextField(20);
btnSave = new JButton("Save");
btnBack = new JButton("Back");
c.add(lblID);
c.add(txtID);
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
id = Integer.parseInt(txtID.getText());
}
catch(NumberFormatException nfe) {
JOptionPane.showMessageDialog(c,"Please Enter Valid Employee Id");
txtID.setText("");
txtID.requestFocus();
}

Employee e = (Employee)session.get(Employee.class, id);
if(e != null)
{
session.delete(e);
t.commit();
JOptionPane.showMessageDialog(c, "record has been deleted.");
txtID.setText("");
txtID.requestFocus();
}
else
{
JOptionPane.showMessageDialog(c, "Record does not exist");
txtID.setText("");
txtID.requestFocus();
}

}
catch(Exception e)
{
t.rollback();
JOptionPane.showMessageDialog(c, "Some issue");
}
finally 
{
session.close();
}
}
});



setSize(220,400);
setTitle("Delete employee");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}