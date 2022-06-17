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


public class GradeDeleteWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField sno,cid; 

	public GradeDeleteWindow() {
		init();
	}

	private void init() {
		setTitle(Constants.DELETEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(2, 2));

		jPanelCenter.add(new JLabel(Constants.GRADE_SNO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel(Constants.GRADE_CID));
		cid = new JTextField();
		jPanelCenter.add(cid);

		
		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton(Constants.DELETEVIEW_DELETEBUTTON);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Grade gde=new Grade();
					buildGrade(gde);
					boolean isSuccess = ((GradeDAO) BaseDAO.getAbilityDAO(DAO.GradeDAO)).delete(gde);
					if (isSuccess) {
						setEmpty();
						String[][] result = ((GradeDAO) BaseDAO.getAbilityDAO(DAO.GradeDAO)).quary();
						GradeWindow.initJTable(GradeWindow.jTable, result);
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
		setBounds(470, 200, 400, 150);
		setResizable(false);
		setVisible(true);
	}


	private void buildGrade(Grade gde) {
		gde.setCid(cid.getText());
		gde.setSno(sno.getText());
	}

	private void setEmpty() {
		cid.setText("");
		sno.setText("");
	}
}
