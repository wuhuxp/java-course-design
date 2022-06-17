package Windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import BaseDAO.*;
import Constants.*;
import DAO.*;


public class CourseWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelNorth, jPanelCenter;
	private JButton jButtonAdd, jButtonDelete, jButtonUpdate;
	public static JTable jTable;
	private JScrollPane jScrollPane;
	private DefaultTableModel myTableModel;
	
	public static String[] column = {Constants.COURSE_CID,Constants.COURSE_CNAME};
	
	public CourseWindow() {
		init();
	}
	
	private void init() {
		setTitle(Constants.COURSE_WINDOW);
		
		jPanelNorth = new JPanel();
		jPanelNorth.setLayout(new GridLayout(1, 5));
		
		
		jButtonAdd = new JButton(Constants.PARAM_ADD);
		jButtonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CourseAddWindow();
			}
		});
		jPanelNorth.add(jButtonAdd);
		// delete
		jButtonDelete = new JButton(Constants.PARAM_DELETE);
		jButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CourseDeleteWindow();
			}
		});
		jPanelNorth.add(jButtonDelete);
		// update
		jButtonUpdate = new JButton(Constants.PARAM_UPDATE);
		jButtonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CourseUpdateWindow();
			}
		});
		jPanelNorth.add(jButtonUpdate);

		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 1));
		
		String[][] result = ((CourseDAO) BaseDAO.getAbilityDAO(DAO.CourseDAO)).quary();
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);

		jScrollPane = new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);
		
		this.add(jPanelNorth, BorderLayout.SOUTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		
		setBounds(400, 200, 600, 340);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
		jTable.setRowHeight(20);
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(300);
		firsetColumn.setMaxWidth(300);
		firsetColumn.setMinWidth(300);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(300);
		secondColumn.setMaxWidth(300);
		secondColumn.setMinWidth(300);
	}
}
