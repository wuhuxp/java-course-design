package Windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BaseDAO.*;
import Constants.*;
import DAO.*;

import Model.*;


import Constants.Constants;

public class StudentUpdateWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField sno,name,sex,birth;

	public StudentUpdateWindow() {
		init();
	}

	private void init() {
		setTitle(Constants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(4, 2));
		jPanelCenter.add(new JLabel(Constants.STUDENT_SNO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel(Constants.STUDENT_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(Constants.STUDENT_SEX));
		sex = new JTextField();
		jPanelCenter.add(sex);
		jPanelCenter.add(new JLabel(Constants.STUDENT_BIRTH));
		birth = new JTextField();
		jPanelCenter.add(birth);
		
		
		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(Constants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = false;
					try {
						isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).update(stu);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (isSuccess) {
						setEmpty();
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.quary();
						StudentWindow.initJTable(StudentWindow.jTable, result);
					}
			}
		});
		jPanelSouth.add(updateButton);
		exitButton = new JButton(Constants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	private void buildStudent(Student stu) {
		stu.setSno(sno.getText());
		stu.setName(name.getText());
		stu.setSex(sex.getText());
		stu.setBirth(birth.getText());
	}

	private void setEmpty() {
		sno.setText("");
		name.setText("");
		sex.setText("");
		birth.setText("");
	}
}
