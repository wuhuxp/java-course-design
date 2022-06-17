package Windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import BaseDAO.*;
import Constants.*;
import DAO.*;


import java.io.*;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
//
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class StudentWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelSorth, jPanelCenter,jPanelNorth;
	private JButton jButtonAdd, jButtonDelete, jButtonUpdate,
	jButtonFind1,jButtonFind2,jButtonExport;
	public static JTable jTable;
	private JScrollPane jScrollPane;
	private DefaultTableModel myTableModel;
	private JTextField jf1,jf2;
	
	
	public static String[] column = {Constants.STUDENT_SNO,Constants.STUDENT_NAME,Constants.STUDENT_SEX,Constants.STUDENT_BIRTH};
	
	public StudentWindow() {
		init();
	}
	
	private void init() {
		setTitle(Constants.STUDENT_WINDOW);
		
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 1));
				
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).quary();
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);
		
		jPanelNorth = new JPanel();
		jPanelNorth.setLayout(new GridLayout(1, 3));
		
		
		jf1 = new JTextField(Constants.PARAM_FIND_CONDITION);
		jf1.addKeyListener(new FindListener1());
		jPanelNorth.add(jf1);
		
		jButtonFind1 = new JButton(Constants.PARAM_FINDBYNAME);
		jButtonFind1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findbyname();
			}
		});
		jButtonFind1.addKeyListener(new FindListener1());
		jPanelNorth.add(jButtonFind1);
		
		jf2 = new JTextField(Constants.PARAM_FIND_CONDITION);
		jf2.addKeyListener(new FindListener2());
		jPanelNorth.add(jf2);
		
		jButtonFind2 = new JButton(Constants.PARAM_FINDBYSNO);
		jButtonFind2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findbysno();
			}
		});
		jButtonFind2.addKeyListener(new FindListener2());
		jPanelNorth.add(jButtonFind2);
		
		
		
		jPanelSorth = new JPanel();
		jPanelSorth.setLayout(new GridLayout(1, 5));
		
		jButtonAdd = new JButton(Constants.PARAM_ADD);
		jButtonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StudentAddWindow();
			}
		});
		jPanelSorth.add(jButtonAdd);
		// delete
		jButtonDelete = new JButton(Constants.PARAM_DELETE);
		jButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StudentDeleteWindow();
			}
		});
		jPanelSorth.add(jButtonDelete);
		// update
		jButtonUpdate = new JButton(Constants.PARAM_UPDATE);
		jButtonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StudentUpdateWindow();
			}
		});
		jPanelSorth.add(jButtonUpdate);
		
		
		jButtonExport = new JButton(Constants.PARAM_EXPORT);
		jButtonExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jPanelCenter, "Export Successfully!", "Success",JOptionPane.WARNING_MESSAGE);
				try {
					Export();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanelSorth.add(jButtonExport);
		
		
		
		
		jScrollPane = new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);
				
		this.add(jPanelSorth, BorderLayout.SOUTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelNorth, BorderLayout.NORTH);
				
		setBounds(400, 200, 600, 340);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
		jTable.setRowHeight(20);
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(150);
		firsetColumn.setMaxWidth(150);
		firsetColumn.setMinWidth(150);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(150);
		secondColumn.setMaxWidth(150);
		secondColumn.setMinWidth(150);
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(150);
		thirdColumn.setMaxWidth(150);
		thirdColumn.setMinWidth(150);
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
		fourthColumn.setPreferredWidth(150);
		fourthColumn.setMaxWidth(150);
		fourthColumn.setMinWidth(150);
	}
	
	private class FindListener1 extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				findbyname();
			}
		}
	}

	private void findbyname() {
		
		String param = jf1.getText()+"%";
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).searchbyname(param);
		String[][] result2 = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).quaryname(param);
		
		jf1.setText("");
		if(result2==null) {
			JOptionPane.showMessageDialog(jPanelCenter, "the student is not exists", "Error",JOptionPane.WARNING_MESSAGE);
		}
		else{
			new QuaryWindow();
			QuaryWindow.initJTable(QuaryWindow.jTable, result);
		}
	}
	
	private class FindListener2 extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				findbysno();
			}
		}
	}

	private void findbysno() {
		
		String param = jf2.getText()+"%";
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).searchbysno(param);
		String[][] result2 = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).quarysno(param);
		
		jf2.setText("");
		if(result2==null) {
			JOptionPane.showMessageDialog(jPanelCenter, "sno is not exists", "Error",JOptionPane.WARNING_MESSAGE);
		}
		else{
			new QuaryWindow();
			QuaryWindow.initJTable(QuaryWindow.jTable, result);
		}
		
	}
	
	private void Export() throws IOException {
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).search();
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("grade table");
		HSSFRow row1=sheet.createRow(0);
		
		HSSFCell cell=row1.createCell(0);
		cell.setCellValue("Grade Table");
		
		HSSFRow row2=sheet.createRow(1);  
		row2.createCell(0).setCellValue("name");  
	    row2.createCell(1).setCellValue("course");      
	    row2.createCell(2).setCellValue("grade");   
		
	    
	    
	    for(int i=0;i<result.length;i++) {
	    	HSSFRow rowt=sheet.createRow(i+2);
	    	rowt.createCell(0).setCellValue(result[i][0]);  
		    rowt.createCell(1).setCellValue(result[i][1]);      
		    rowt.createCell(2).setCellValue(result[i][2]); 	
	    }
	    
	    	
	    FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\Grade.xls");
            wb.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

	    	
	}
}

