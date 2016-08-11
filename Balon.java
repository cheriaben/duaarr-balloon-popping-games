import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;



public class Balon extends JFrame {

    int gametimer = 60;
    int score = 0;
    int life = 3;
    int highscore = 0;
    int level = 1;
    final DrawPane drawpanel = new DrawPane();
    final MenuPane menupanel = new MenuPane();
    ArrayList<Integer> baloncoordx = new ArrayList();
    ArrayList<Integer> warnabalon = new ArrayList();
    ArrayList<Integer> baloncoordy = new ArrayList();
    Random random;
    Thread balonthread, timerthread;
    boolean pause = true;
    String filewav;
    AudioFormat audioFormat;
  	AudioInputStream audioInputStream;
  	SourceDataLine sourceDataLine;
   
	 public void playAudio(){
    	try{
      	File soundFile = new File(filewav);
      	audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      	audioFormat = audioInputStream.getFormat();
      	System.out.println(audioFormat);

      	DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

      	sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
      	
      	new PlayThread().start();
      	
    	}
    	catch (Exception e){
      e.printStackTrace();
    }//end catch
  }//end playAudio


//Inner class to play back the data from the
// audio file.
public class PlayThread extends Thread{
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;
      //Keep looping until the input read method
      // returns -1 for empty stream
      while((cnt = audioInputStream.read(
           tempBuffer,0,tempBuffer.length))!=-1){
        if(cnt > 0){
       
          //Write data to the internal buffer of
          // the data line where it will be
          // delivered to the speaker.
          sourceDataLine.write(tempBuffer, 0, cnt);
        }//end if
      }//end while
      //Block and wait for internal buffer of the
      // data line to empty.
      sourceDataLine.drain();
      sourceDataLine.close();
    

      //Prepare to playback another file
          }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }//end catch
  }//end run
}//end inner class PlayThread	
    
   public void mainmenu(){
   	this.setContentPane(menupanel);
   } 
    
   public void newgame() {
        baloncoordx.clear();
        baloncoordy.clear();
        warnabalon.clear();
        gametimer = 60;
        score = 0;
        life = 3;
        level = 1;
        pause = false;
        this.setContentPane(drawpanel);
        drawpanel.revalidate();
    }

   public void exitgame() {
        pause = true;
        mainmenu();
        filewav = "theme.wav";
        playAudio();
        
    }

    public void losegame() {
    	pause = true;
        if (score > highscore) {
            JOptionPane.showMessageDialog(null, "GAME OVER, NEW HIGH SCORE!", "Game Over", 1);
            highscore = score;
        }
        else if(score <= highscore) {
            JOptionPane.showMessageDialog(null, "GAME OVER!", "Game Over", 1);
        }
        
        mainmenu();
        filewav = "theme.wav";
        playAudio();
    }

    public void continuegame() {
        pause = false;
        this.setContentPane(drawpanel);
        drawpanel.revalidate();
    }
    
   public void levelup(){
   		pause = true;
    	JOptionPane.showMessageDialog(null, "LEVEL UP!", "Level Up", 1);
    }
    
    
    public class MenuPane extends JPanel implements ActionListener{
    	public ImageIcon background = new ImageIcon("bg.jpg");
   		public Image bg = background.getImage();
   		public ImageIcon duaarr = new ImageIcon("duaarr.png");
   		public Image icon = duaarr.getImage();
   		JLabel lbl = new JLabel();
   		JLabel lbl2 = new JLabel();
   		JLabel lbl3 = new JLabel();
   		JLabel lbl4 = new JLabel();
    	JButton newbtn = new JButton("New Game");
        JButton continuebtn = new JButton("Continue");
        JButton highscorebtn = new JButton("High Score");
        JButton helpbtn = new JButton("Help");
        JButton aboutbtn = new JButton("About");
        JButton exitbtn = new JButton("Exit");

        public MenuPane() {
		    
		    setIconImage(icon);
		    newbtn.addActionListener(this);
			continuebtn.addActionListener(this);
            highscorebtn.addActionListener(this);
            helpbtn.addActionListener(this);
            aboutbtn.addActionListener(this);
            exitbtn.addActionListener(this);
            
            continuebtn.setEnabled(false);
            lbl.setPreferredSize(new Dimension(100,25));
            
			JPanel panel = new JPanel(new GridLayout(10,1,0,30));
			panel.setOpaque(false);
			panel.add(lbl);			
			panel.add(lbl2);			
			panel.add(lbl3);			
			panel.add(lbl4);			
			panel.add(newbtn);
			panel.add(continuebtn);
			panel.add(highscorebtn);
			panel.add(helpbtn);
			panel.add(aboutbtn);
			panel.add(exitbtn);
	
			add(panel);
			filewav = "theme.wav";
			playAudio();
			
		
            
           
         }

         
            
            public void actionPerformed(ActionEvent e){
            	
            	Object obj = e.getSource();
            	if (obj == newbtn){
            		newgame();
            		continuebtn.setEnabled(true);
            	}
            	else if (obj == continuebtn){
            		continuegame();
            	continuebtn.setEnabled(true);
            	}
            	else if (obj == highscorebtn){
            		JOptionPane.showMessageDialog(null, "High Score: " + highscore, "High Score", 1);
            	}
            	else if (obj == helpbtn){
            		Help help = new Help();
            	}
            	else if (obj == aboutbtn){
            		About about = new About();
            	}
            	else {
            		System.exit(0);
            	}
            }
            
             public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg,0,0,null);
             }
            
    }

   public class DrawPane extends JPanel implements MouseListener, ActionListener{
   	public ImageIcon morning = new ImageIcon("morning.jpg");
   	public Image bgmorning = morning.getImage();
   	public ImageIcon night = new ImageIcon("night.jpg");
   	public Image bgnight = night.getImage();
   		
        JButton exitgame = new JButton("Pause");
		
        public DrawPane(){
            addMouseListener(this);
            add(exitgame);
           	exitgame.addActionListener(this);
        }
        
        public void actionPerformed(ActionEvent e){
        	exitgame();
        }
        
       
    

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
			if (level == 1 || level == 3 || level == 5){
            g.drawImage(bgmorning,0,0,null);
   			g.setColor(Color.black);
            g.drawString("Score: " + score, 500, 10);
            g.setColor(Color.black);
            g.drawString("HighScore: " + highscore, 600, 10);
            g.setColor(Color.black);
 
            g.drawString("Life: " + life, 10, 10);
            g.setColor(Color.black);
            g.drawString("LV: " + level, 100, 10);
            g.setColor(Color.black);
            g.drawString("00:" + gametimer, 800, 10);
            g.setColor(Color.black);
            g.drawString("00:" + gametimer, 700, 10);}
            if (level == 2 || level == 4)
            {
            g.drawImage(bgnight,0,0,null);
   			g.setColor(Color.white);
            g.drawString("Score: " + score, 500, 10);
            g.setColor(Color.white);
            g.drawString("HighScore: " + highscore, 600, 10);
            g.setColor(Color.white);
 
            g.drawString("Life: " + life, 10, 10);
            g.setColor(Color.white);
            g.drawString("LV: " + level, 100, 10);
            g.setColor(Color.white);
            g.drawString("00:" + gametimer, 700, 10);}
            

           	


            // menggambar semua balon
            for (int i = 0; i < baloncoordx.size(); i++) {
                g.setColor(Color.WHITE);
                g.drawLine(baloncoordx.get(i)+25, baloncoordy.get(i)+50, baloncoordx.get(i)+25, baloncoordy.get(i)+150);  
                	if (warnabalon.get(i) == 0){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 1){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 2){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 3){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 4){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 5){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 6){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 7){
                    g.setColor(Color.red);}
                else if (warnabalon.get(i) == 8){
                    g.setColor(Color.yellow);}
                else if (warnabalon.get(i) == 9) {
                    g.setColor(Color.black);}
                    
	
                g.fillOval(baloncoordx.get(i), baloncoordy.get(i), 50, 60);

            }


           


        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < baloncoordx.size(); i++) {
                int baloncoordxint = baloncoordx.get(i);
                int baloncoordyint = baloncoordy.get(i);

                // mengecek klik mouse kena balon atau tidak
                if (e.getX() <= baloncoordxint + 50
                        && e.getX() >= baloncoordxint
                        && e.getY() <= baloncoordyint + 60
                        && e.getY() >= baloncoordyint) {
                    if (warnabalon.get(i) == 0){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 1){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 2){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 3){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 4){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 5){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 6){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 7){
                    	filewav = "pop.wav";
                    	playAudio();
                        score += 100;}
                    else if (warnabalon.get(i) == 8){
                    	filewav = "tada.wav";
                    	playAudio();
                        score += 1000;}
                    else if (warnabalon.get(i) == 9){
                        life -= 3;
                        
                    }
                    baloncoordx.remove(i);
                    baloncoordy.remove(i);
                    warnabalon.remove(i);
                }
            }
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }


        public void mouseExited(MouseEvent e) {
        }
    }

    public class BalonRunnable implements Runnable {
    	

        public void run() {
            while (true) {
                if (!pause) {

                    // menambah balon baru jika balon sblumnya ada dan posisi y nya diatas 500
                    if (baloncoordy.size() != 0 && level == 1) {

                        if (baloncoordy.get(baloncoordy.size() - 1) <= 200) {
                            warnabalon.add(random.nextInt(10));
                            baloncoordx.add(random.nextInt(700));
                            baloncoordy.add(620);
                        }
                    }
                    
                    else if (baloncoordy.size() != 0 && level == 2) {

                        if (baloncoordy.get(baloncoordy.size() - 1) <= 250) {
                            warnabalon.add(random.nextInt(10));
                            baloncoordx.add(random.nextInt(700));
                            baloncoordy.add(620);
                        }
                    }
                    
                    else if (baloncoordy.size() != 0 && level == 3) {

                        if (baloncoordy.get(baloncoordy.size() - 1) <= 300) {
                            warnabalon.add(random.nextInt(10));
                            baloncoordx.add(random.nextInt(700));
                            baloncoordy.add(620);
                        }
                    }
                    
                    else if (baloncoordy.size() != 0 && level == 4) {

                        if (baloncoordy.get(baloncoordy.size() - 1) <= 400) {
                            warnabalon.add(random.nextInt(10));
                            baloncoordx.add(random.nextInt(700));
                            baloncoordy.add(620);
                        }
                    }
                    
                    else if (baloncoordy.size() != 0 && level == 5) {

                        if (baloncoordy.get(baloncoordy.size() - 1) <= 450) {
                            warnabalon.add(random.nextInt(10));
                            baloncoordx.add(random.nextInt(700));
                            baloncoordy.add(620);
                        }
                    }
                    	
                    	 else {
                         // jika tidk ada balon sama sekali tambah balon baru
                        warnabalon.add(random.nextInt(10));
                        baloncoordx.add(random.nextInt(700));
                        baloncoordy.add(620);
                    }

                    // menggerakan balon 
                    for (int i = 0; i < baloncoordx.size(); i++) {
                        baloncoordy.set(i, baloncoordy.get(i) - 2 * (level+1));
                        if(warnabalon.get(i) == 8){
                        baloncoordy.set(i, baloncoordy.get(i) - 12);
                        }
                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // mengecek apakah balon merah sudah naik ke atas dan gagal diklik 
                    for (int i = 0; i < baloncoordx.size(); i++) {
                        int baloncoordxint = baloncoordx.get(i);
                        int baloncoordyint = baloncoordy.get(i);

                        if (baloncoordyint < 0 && warnabalon.get(i) == 0) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                       else if (baloncoordyint < 0 && warnabalon.get(i) == 1) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                        else if (baloncoordyint < 0 && warnabalon.get(i) == 2) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                        else if (baloncoordyint < 0 && warnabalon.get(i) == 3) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                        else if (baloncoordyint < 0 && warnabalon.get(i) == 4) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                        else if (baloncoordyint < 0 && warnabalon.get(i) == 5) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                        else if (baloncoordyint < 0 && warnabalon.get(i) == 6) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                        else if (baloncoordyint < 0 && warnabalon.get(i) == 7) {
                            life--;
                            baloncoordx.remove(i);
                            baloncoordy.remove(i);
                            warnabalon.remove(i);
                        }
                    }
                    if (life <= 0) {
                        losegame();
                        mainmenu();
                    }

                    drawpanel.repaint();
                }
            }
        }
    }

    public class gametimerRunnable implements Runnable {
//timer game

        public void run() {
            while (true) {
                if (!pause) {
                    gametimer--;
                    if (score >= 3000 && level == 1) {
                        gametimer = 60;
                        level++;
                        levelup();
                        pause = false;
                    }
                    else if (score >= 6000 && level == 2) {
                        gametimer = 60;
                        level++;
                        levelup();
                        pause = false;
                        
                    }
                    else if (score >= 9000 && level == 3) {
                        gametimer = 60;
                        level++;
                        levelup();
                        pause = false;
                    }
                    else if (score >= 12000 && level == 4) {
                        gametimer = 60;
                        level++;
                        levelup();
                        pause = false;
                     
                    }
                    if (gametimer <= 0) {
                        if (score >= 3000 && level == 1) {
                            gametimer = 60;
                            level++;
                            levelup();
                        	pause = false;
                        }
                            
                        else if (score >= 6000 && level == 2) {
                            gametimer = 60;
                            level++;
                            levelup();
                       	 	pause = false;
                        } 
                        else if (score >= 9000 && level == 3) {
                            gametimer = 60;
                            level++;
                            levelup();
                        	pause = false;
                        }
                        else if (score >= 12000 && level == 4) {
                            gametimer = 60;
                           	level++;
                           	levelup();
                        	pause = false;
                        }
                        else {
                        	losegame();
                        	mainmenu();
                        }
                    }
                    drawpanel.repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public Balon() {
        BalonRunnable balonrunnable = new BalonRunnable();
        gametimerRunnable timerrunnable = new gametimerRunnable();
        balonthread = new Thread(balonrunnable);
        timerthread = new Thread(timerrunnable);
        random = new Random();
        balonthread.start();
        timerthread.start();
        setContentPane(menupanel);

    }

    public static void main(String[] args) {
        Balon frame = new Balon();
	frame.setTitle("Duaarr!!!");
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
}
}
