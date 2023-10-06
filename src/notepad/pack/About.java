package notepad.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener {

	JButton button;
	public About() {

		setLayout(null);
		ImageIcon il = new ImageIcon(ClassLoader.getSystemResource("notepadIcon/windows.png"));
		Image i2 = il.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel label = new JLabel(i3);
		label.setBounds(70, 40, 400, 80);
		add(label);

		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("notepadIcon/notepad.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 70, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel label1 = new JLabel(i6);
		label1.setBounds(50, 180, 60, 70);
		add(label1);

		JLabel text = new JLabel(
				"<html> Microsoft Windows <br> Version 0.213.452.365 <br> MicroSoft Corporation, all right reserved <br> <br> The Windows 11 Home single language operating system its user <br> interface are protected by trademark and other pending and  <br>existing intellectual property in the united States.</html>");
		text.setBounds(150, 100, 500, 200);
		add(text);

		button=new JButton("OK");
		button.addActionListener(this);
		button.setBounds(150, 300, 120	, 25);
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Consolas",Font.PLAIN,18));
		add(button);
		
		setTitle("About");
		setBounds(400, 100, 600, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		setVisible(false);
	}
	public static void main(String[] args) {

		new About();
	}

	

}
