import java.awt.*;
import javax.swing.*;

public class Help extends JFrame
{

	public ImageIcon help = new ImageIcon("help.png");
   	public Image icon = help.getImage();
	public Help()
	{
		setTitle("Help");
		setSize(750,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(icon);

		String message1 = "Pecahkan balon sebanyak - banyaknya sebelum waktu habis!";
		String message2 = "Balon merah memiliki skor 100, balon kuning memiliki skor 1000, dan jika memecahkan balon hitam maka game akan berakhir.";
		String message3 = "Jika balon merah terlewat, maka life akan berkurang 1.";
		String message4 = "Setiap level memiliki skor target, semakin tinggi levelya, semakin tinggi tingkat kesulitannya.";
		String message5 = "Selamat bermain!";
		
			
		
		JTextArea txt_help1 = new JTextArea(message1);
		JTextArea txt_help2 = new JTextArea(message2);
		JTextArea txt_help3 = new JTextArea(message3);
		JTextArea txt_help4 = new JTextArea(message4);
		JTextArea txt_help5 = new JTextArea(message5);
		
		txt_help1.setEditable(false);
		txt_help2.setEditable(false);
		txt_help3.setEditable(false);
		txt_help4.setEditable(false);
		txt_help5.setEditable(false);
		
		
		JPanel panel = new JPanel(new GridLayout(5,1));
		panel.add(txt_help1);
		panel.add(txt_help2);
		panel.add(txt_help3);
		panel.add(txt_help4);
		panel.add(txt_help5);
		
		add(panel);
		setVisible(true);
	}
}