package com.employee.manage;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;

public class HomePage {

	private Frame mainFrame;
	private Panel controlPanel;

	public HomePage() {
		prepareGUI();
	}

	public static void main(String args[]) {
		HomePage awtMenuDemo = new HomePage();
		awtMenuDemo.showMenuDemo();
		awtMenuDemo.showimagedemo();
	}

	private void prepareGUI() {

		mainFrame = new Frame("HOME PAGE");
		int width = 800, height = 600;
		mainFrame.setSize(width,height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width-width)/2;
		int y = (dim.height-height)/2;		 
		mainFrame.setLocation(x, y);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	public void showimagedemo() {
		controlPanel.add(new ImageComponent("C:\\Users\\background.png"));
		mainFrame.setVisible(true);
	}

	@SuppressWarnings("serial")
	class ImageComponent extends Component {

		BufferedImage img;

		public void paint(Graphics g) {
			g.drawImage(img, 0, 0,mainFrame.getX()/2,mainFrame.getY()/2,null);
		}

		public ImageComponent(String path) {
			try {
				img = ImageIO.read(new File(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Dimension getPreferredSize() {
			if (img == null) {
				return new Dimension(100, 100);
			} else {
				return new Dimension(img.getWidth(), img.getHeight());
			}
		}
	}

	public void showMenuDemo() {

		final MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("Home");
		Menu editMenu = new Menu("Update");

		MenuItem newMenuItem = new MenuItem("New Employee");
		newMenuItem.setActionCommand("New Employee");

		MenuItem openMenuItem = new MenuItem("Salary Details");
		openMenuItem.setActionCommand("Salary Details");

		MenuItem cutMenuItem = new MenuItem("Update Salary");
		cutMenuItem.setActionCommand("Update Salary");

		MenuItem copyMenuItem = new MenuItem("Update Employee");
		copyMenuItem.setActionCommand("Update Employee");

		MenuItem pasteMenuItem = new MenuItem("Take Attendance");
		pasteMenuItem.setActionCommand("Take Attendance");

		MenuItemListener menuItemListener = new MenuItemListener();

		newMenuItem.addActionListener(menuItemListener);
		openMenuItem.addActionListener(menuItemListener);
		cutMenuItem.addActionListener(menuItemListener);
		copyMenuItem.addActionListener(menuItemListener);
		pasteMenuItem.addActionListener(menuItemListener);

		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);

		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		mainFrame.setMenuBar(menuBar);
		mainFrame.setVisible(true);
	}

	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd == "New Employee") {
				new AddEmployee();
			}else if(cmd == "Salary Details") {
				new AddSalary();
			}else if(cmd == "Update Salary") {
				new UpdateSalary();
			}else if(cmd == "Update Employee") {
				new UpdateEmployee();
			}else if(cmd == "Take Attendance") {
				new Attendance();
			}
		}
	}

}
