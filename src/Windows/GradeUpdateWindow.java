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


public class GradeUpdateWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField sno,cid,grade;

	public GradeUpdateWindow() {
		init();
	}

	private void init() {
		setTitle(Constants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(Constants.GRADE_SNO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel(Constants.GRADE_CID));
		cid = new JTextField();
		jPanelCenter.add(cid);
		jPanelCenter.add(new JLabel(Constants.GRADE_GRADE));
		grade = new JTextField();
		jPanelCenter.add(grade);
		
		
		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(Constants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Grade gde=new Grade();
					buildGrade(gde);
					boolean isSuccess = false;
					try {
						isSuccess = ((GradeDAO) BaseDAO.getAbilityDAO(DAO.GradeDAO)).update(gde);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (isSuccess) {
						setEmpty();
						String[][] result = ((GradeDAO) BaseDAO.getAbilityDAO(DAO.GradeDAO))
								.quary();
						GradeWindow.initJTable(GradeWindow.jTable, result);
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
		setBounds(470, 200, 400, 200);
		setResizable(false);
		setVisible(true);
	}

	private void buildGrade(Grade gde) {
		gde.setSno(sno.getText());
		gde.setCid(cid.getText());
		gde.setGrade(grade.getText());
	}

	private void setEmpty() {
		sno.setText("");
		cid.setText("");
		grade.setText("");
	}
}
