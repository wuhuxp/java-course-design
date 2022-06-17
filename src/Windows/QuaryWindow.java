package Windows;
import java.awt.BorderLayout;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BaseDAO.BaseDAO;
import Constants.Constants;
import Constants.DAO;
import DAO.StudentDAO;

public class QuaryWindow  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JPanel jPanelCenter;
	public static JTable jTable;
	private JScrollPane jScrollPane;
	private DefaultTableModel myTableModel;
	
	public static String[] column = {Constants.STUDENT_NAME,Constants.STUDENT_SNO,Constants.COURSE_CNAME,Constants.GRADE_GRADE};
	
	public QuaryWindow() {
		init();
	}
	
	private void init() {
		setTitle(Constants.SEARCHWINDOW_TITLE);
		
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 1));
				
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).searchbysno("");
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);
		
		
		jScrollPane = new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);
				
		this.add(jPanelCenter, BorderLayout.CENTER);
				
		setBounds(400, 200, 800, 340);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
		jTable.setRowHeight(20);
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(200);
		firsetColumn.setMaxWidth(200);
		firsetColumn.setMinWidth(200);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(200);
		secondColumn.setMaxWidth(200);
		secondColumn.setMinWidth(200);
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(200);
		thirdColumn.setMaxWidth(200);
		thirdColumn.setMinWidth(200);
		TableColumn forthColumn = jTable.getColumnModel().getColumn(3);
		forthColumn.setPreferredWidth(200);
		forthColumn.setMaxWidth(200);
		forthColumn.setMinWidth(200);
	}
}
