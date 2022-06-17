package Windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BaseDAO.*;
import Constants.*;
import DAO.*;

import Model.*;


public class StudentDeleteWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField sno; 

	public StudentDeleteWindow() {
		init();
	}

	private void init() {
		setTitle(Constants.DELETEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 2));

		jPanelCenter.add(new JLabel(Constants.STUDENT_SNO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		
		
		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton(Constants.DELETEVIEW_DELETEBUTTON);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).delete(stu);
					if (isSuccess) {
						setEmpty();
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).quary();
						StudentWindow.initJTable(StudentWindow.jTable, result);
					}
			}
		});
		jPanelSouth.add(deleteButton);
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
		setBounds(470, 250, 400, 100);
		setResizable(false);
		setVisible(true);
	}


	private void buildStudent(Student stu) {
		stu.setSno(sno.getText());
	}

	private void setEmpty() {
		sno.setText("");
	}
}
