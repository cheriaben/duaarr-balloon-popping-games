import java.awt.*;
import javax.swing.*;

public class About extends JFrame
{
	public ImageIcon about = new ImageIcon("about.png");
   	public Image icon = about.getImage();
	public About()
	{
		setIconImage(icon);
		setTitle("About");
		setSize(250,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		String message1 = "      Copyright © 2014 DUAARR!!! GAME               ";
		String message2 = "                  JAVA 2 MINI PROJECT                    ";
		String message3 = "        Lecturer: Prya Artha Widjaja, M.Sc. ";
		String message4 = "                                    By:";
		String message5 = "           Cheria Benedicta (535120012)";
		String message6 = "                 Dessy Yanty (535120039)";
		String message7 = "                  Tania Oen (535120023)";
		String message8 = "         Kezia Septaviana S. (535120066) ";
		String message9 = "           Handy Chandra G. (535120011)";
		String message10 = "                       Eris (535120026)";
		String message11 = "         Thank you for playing DUAARR!!!";
		
		
		JTextArea txt_about1 = new JTextArea(message1);
		JTextArea txt_about2 = new JTextArea(message2);
		JTextArea txt_about3 = new JTextArea(message3);
		JTextArea txt_about4 = new JTextArea(message4);
		JTextArea txt_about5 = new JTextArea(message5);
		JTextArea txt_about6 = new JTextArea(message6);
		JTextArea txt_about7 = new JTextArea(message7);
		JTextArea txt_about8 = new JTextArea(message8);
		JTextArea txt_about9 = new JTextArea(message9);
		JTextArea txt_about10 = new JTextArea(message10);
		JTextArea txt_about11 = new JTextArea(message11);
		
		
		txt_about1.setEditable(false);
		txt_about2.setEditable(false);
		txt_about3.setEditable(false);
		txt_about4.setEditable(false);
		txt_about5.setEditable(false);
		txt_about6.setEditable(false);
		txt_about7.setEditable(false);
		txt_about8.setEditable(false);
		txt_about9.setEditable(false);
		txt_about10.setEditable(false);
		txt_about11.setEditable(false);
		
		
		JPanel panel = new JPanel(new GridLayout(11,1));
		panel.add(txt_about1);
		panel.add(txt_about2);
		panel.add(txt_about3);
		panel.add(txt_about4);
		panel.add(txt_about5);
		panel.add(txt_about6);
		panel.add(txt_about7);
		panel.add(txt_about8);
		panel.add(txt_about9);
		panel.add(txt_about10);
		panel.add(txt_about11);
	
		
		add(panel);
		setVisible(true);
	}
}