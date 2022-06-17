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


public class CourseAddWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
	private JTextField cid,cname;

	public CourseAddWindow() {
		init();
	}

	private void init() {
		setTitle(Constants.ADDVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(2, 2));
		jPanelCenter.add(new JLabel(Constants.COURSE_CID));
		cid = new JTextField();
		jPanelCenter.add(cid);
		jPanelCenter.add(new JLabel(Constants.COURSE_CNAME));
		cname = new JTextField();
		jPanelCenter.add(cname);
		
		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton(Constants.ADDVIEW_ADDBUTTON);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course cos = new Course();
				buildCourse(cos);
				boolean isSuccess = false;
				isSuccess = ((CourseDAO) BaseDAO.getAbilityDAO(DAO.CourseDAO)).add(cos);
				if (isSuccess) {
					setEmpty();
					String[][] result = ((CourseDAO) BaseDAO.getAbilityDAO(DAO.CourseDAO)).quary();
					CourseWindow.initJTable(CourseWindow.jTable, result);
				}
		}
	});
		jPanelSouth.add(addButton);
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
		setBounds(470, 200, 400, 150);
		setResizable(false);
		setVisible(true);
	}


	private void buildCourse(Course cos) {
		cos.setCid(cid.getText());
		cos.setCname(cname.getText());
	}

	private void setEmpty() {
		cid.setText("");
		cname.setText("");
	}
}
