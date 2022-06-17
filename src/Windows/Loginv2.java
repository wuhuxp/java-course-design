package Windows;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BaseDAO.*;
import Constants.*;
import DAO.*; 
public class Loginv2 extends JFrame{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�������  
    JPanel jp1,jp2,jp3;//���  
    JLabel jlb1,jlb2;//��ǩ  
    JButton jb1,jb2;//��ť  
    JTextField jtf;//�ı�  
    JPasswordField jpf;//����  
    //���캯��  
    public Loginv2(){  
        //�������  
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        //������ǩ  
        jlb1=new JLabel(Constants.LOGIN_USERNAME);  
        jlb2=new JLabel(Constants.LOGIN_PASSWORD);  
        //������ť  
        jb1=new JButton(Constants.LOGIN); 
        jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
        jb2=new JButton(Constants.RESET);  
        jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtf.setText("");
				jpf.setText("");
			}
		});
        //�����ı���  
        jtf=new JTextField(10);  
        //���������  
        jpf=new JPasswordField(10);  
          
        //���ò��ֹ���  
        this.setLayout(new GridLayout(3, 1));//����ʽ����  
          
        //����������  
        jp1.add(jlb1);  
        jp1.add(jtf);  
          
        jp2.add(jlb2);  
        jp2.add(jpf);  
          
        jp3.add(jb1);  
        jp3.add(jb2);  
          
        //���뵽JFrame  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
          
        //���ô���  
        this.setTitle(Constants.LOGIN);//�����ǩ  
        this.setSize(300, 150);//�����С  
        this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳��ر�JFrame  
        this.setVisible(true);//��ʾ����  
          
        //��������  
        this.setResizable(false);  
    }  
    

	private void check() {
		AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if (adminDAO.queryForLogin(jtf.getText(), String.valueOf(jpf.getPassword()))) {
			dispose();
			new MainWindow();
		} else {
			jtf.setText("");
			jpf.setText("");  
			JOptionPane.showMessageDialog(null, "username or password wrong", "Error",JOptionPane.WARNING_MESSAGE);
		}
	}
    
}  
