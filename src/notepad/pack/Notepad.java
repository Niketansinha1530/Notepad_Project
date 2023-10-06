package notepad.pack;

//abstract window toolkit
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Notepad extends JFrame implements ActionListener{

	JTextArea area;
	String text;
	public Notepad() {
	
		setTitle("Notepad");
		
		ImageIcon notepadicon= new ImageIcon(ClassLoader.getSystemResource("notepadIcon/notepad.png"));
		Image icon=notepadicon.getImage();
		setIconImage(icon);
		//menubar
		JMenuBar menuBar=new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		JMenu file=new JMenu("File");
		file.setFont(new Font("AERIAL",Font.PLAIN,14));
		
		//All item in menu bar File
		JMenuItem newdoc=new JMenuItem("New");
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newdoc.addActionListener(this);
		
		JMenuItem open=new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		
		JMenuItem save=new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		
		JMenuItem print=new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		print.addActionListener(this);
		
		JMenuItem exit=new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,ActionEvent.CTRL_MASK));
		exit.addActionListener(this);
		
		file.add(newdoc);
		file.add(open);
		file.add(save);
		file.add(print);
		file.add(exit);
		
		
		
		// 2 JMenu
		JMenu edit=new JMenu("Edit");
		edit.setFont(new Font("AERIAL",Font.PLAIN,14));
		
		//All item in menu bar File
		JMenuItem copy=new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		JMenuItem paste=new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		paste.addActionListener(this);

		JMenuItem cut=new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		JMenuItem selectAll=new JMenuItem("Select All");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		selectAll.addActionListener(this);
		
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(selectAll);
		
		JMenu about=new JMenu("About");
		about.setFont(new Font("AERIAL",Font.PLAIN,14));
		
		//All item in menu bar File
		JMenuItem help=new JMenuItem("About NotePad");
		help.addActionListener(this);
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		about.add(help);
		
		
		
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(about);
		setJMenuBar(menuBar);
		
		area=new JTextArea();
		area.setFont(new Font("Consolas",Font.PLAIN,18));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		//add(area); //humaray text area pay scroll nahii ho raha tha tho jscrool ka use karengay
		
		JScrollPane pane=new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder());
		add(pane);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//it need to be write in last line
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New"))
		{
		area.setText("");
		}else if(e.getActionCommand().equals("Open"))
		{
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);//sarri file ko na pad sackay only txt file.
			FileNameExtensionFilter restric = new FileNameExtensionFilter("only .txt files", "txt");
			fileChooser.addChoosableFileFilter(restric);
			int action= fileChooser.showOpenDialog(this);
			
			//agar kuch select nahi kiya or cancel kardiya tho kya karna hai wo likha hai.
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File file=fileChooser.getSelectedFile();
			
			try
			{
			
				BufferedReader reader=new BufferedReader(new FileReader(file));
				area.read(reader, null);
				
			}catch(Exception ae)
			{
				ae.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Save"))
		{
			JFileChooser saveas=new JFileChooser();
			saveas.setApproveButtonText("Save");
			int action=saveas.showOpenDialog(this);
			
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File filename=new File(saveas.getSelectedFile()+".txt");
			BufferedWriter outfile=null;
			
			try
			{
				outfile=new BufferedWriter(new FileWriter(filename));
				area.write(outfile);
				
			}catch(Exception ee)
			{
				ee.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Print"))
		{
			try
			{
				area.print();
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}else if(e.getActionCommand().equals("Copy"))
		{
			text=area.getSelectedText();
		}else if(e.getActionCommand().equals("Paste"))
		{
			area.insert(text, area.getCaretPosition());
		}else if(e.getActionCommand().equals("Cut"))
		{
			text=area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
		}else if(e.getActionCommand().equals("Select All"))
		{
			area.selectAll();
		}else if(e.getActionCommand().equals("About NotePad"))
		{
			new About().setVisible(true);
		}
					
	}
	public static void main(String[] args) {
		
		new Notepad();

	}
	

}
