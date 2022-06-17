package Windows;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;


import Constants.Constants;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton stuB,cosB,gdeB;
	private JPanel jPanelCenter;
	
	public MainWindow() {
		init();
	}
	
	public void init() {
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new FlowLayout());
		
		stuB = new JButton(Constants.MAINWINDOW_stuBUTTON);
		stuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentWindow();
			}
		});
		jPanelCenter.add(stuB);
		
		cosB = new JButton(Constants.MAINWINDOW_cosBUTTON);
		cosB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CourseWindow();
			}
		});
		jPanelCenter.add(cosB);
		
		gdeB = new JButton(Constants.MAINWINDOW_gdeBUTTON);
		gdeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GradeWindow();
			}
		});
		jPanelCenter.add(gdeB);
		
		Dimension preferredSize=new Dimension(160, 50);
		stuB.setPreferredSize(preferredSize); 
		cosB.setPreferredSize(preferredSize);
		gdeB.setPreferredSize(preferredSize);
		
		this.add(jPanelCenter);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 600, 200);
		setResizable(false);
		setVisible(true);
	}
}
