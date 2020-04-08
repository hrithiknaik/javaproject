import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.util.List;
class ViewFrame extends JFrame
{
int id;
int salary;
String name;
Container c;
TextArea ta;
JButton btnBack;

ViewFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());
ta = new TextArea(5,30);
btnBack = new JButton("Back");
c.add(ta);
c.add(btnBack);


Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();
try
{
List<Employee> emp = new ArrayList<Employee>();
emp = session.createQuery("from Employee").list();

for(Employee e: emp)
{
ta.append(e.getId() + " " + e.getName() + " " + e.getSalary() + "\n");
}

}
catch(Exception e)
{
System.out.println(e);
}
finally{
session.close();
}


btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a = new MainFrame();
dispose();
}
});
setTitle("View");
setSize(300,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}