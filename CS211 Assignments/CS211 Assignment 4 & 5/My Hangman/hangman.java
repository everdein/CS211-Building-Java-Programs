// Matthew Clark
// CS210 Building Java Programs
// Assigment 4 & 5 - Hangman

/* 
   This program is a game called, Hangman. In this game you're 
   provided with a random word that you try to guess with one
   letter from the alphabet at a time. Once you've chosen 6 times
   you loose, unless you reveal the mystery word before your 6 tries. 
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import java.util.List;
class words{
	String word; 
	int x,y,r,g,b,dx,dy; 
}
public class hangman {
	public static void main(String[]args){
		new myframe();
	}
}
class myframe extends JFrame{
	Container c; 
	JPanel panel; 
	JButton addbutton, startbutton;
	JLabel catagory,ballsize;
	JTextField guess;
	JComboBox cb;
	buttonListener handle;
	myDrawboard myboard;
	JFrame mainFrame;
	public myframe(){
		super("Hangman");
		c = getContentPane();
		guess = new JTextField(1);
		guess.setFont(new Font("SansSerif", Font.BOLD, 50));
		guess.setText("");
		guess.addActionListener(new ActionListener(){
			Random rnd = new Random();
			public void actionPerformed(ActionEvent e){
				String entered1 = guess.getText();
				Set<String> prevGuess = new HashSet<String>();
				Set<String> correctGuess = new HashSet<String>();
				prevGuess.add(entered1);
				for (int j = 0; j <myboard.panel.answer.length(); j++){
					if(entered1.equals("")){
					} else if (entered1.toUpperCase().charAt(0) == myboard.panel.answer.charAt(j)){ 
						myboard.panel.temp = Character.toString(entered1.toUpperCase().charAt(0));
						correctGuess.add(entered1);
					}
				}
				if (correctGuess.contains(entered1) == false){
					myboard.panel.count++;
				}
				guess.setText("");
			}});
		catagory = new JLabel("catagory");
		startbutton = new JButton("Play");
		startbutton.setFont(new Font("SansSerif", Font.BOLD,30));
		startbutton.setPreferredSize(new Dimension(120,60));
		cb = new JComboBox();
		cb.addItem("Easy");
		cb.addItem("Normal");
		cb.addItem("Difficult");
		handle = new buttonListener();
		startbutton.addActionListener(handle);
		panel= new JPanel();
		panel.add(guess);
		panel.add(catagory);
		panel.add(cb);
		panel.add(startbutton);
		myboard = new myDrawboard();
		c.add(myboard.panel, BorderLayout.CENTER);
		c.add(panel, BorderLayout.SOUTH);
		setSize(1000,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Timer timer = new Timer();
		timer.schedule(new myTimer(), 0, 16);
			myboard.addWords();
	}
class myTimer extends TimerTask{
	@Override
	public void run(){
		repaint();
	}
}
class buttonListener implements ActionListener{
	public void actionPerformed(ActionEvent action){
		if(action.getSource() == startbutton){
			if (startbutton.getText().equals("Play")){
				startbutton.setText("End");
				myboard.addChar(cb.getSelectedItem().toString());
			}else startbutton.setText("Play");
		}
	}
}
}
class myDrawboard{
	Graphics2D g2; 
	MyPanel panel = new MyPanel();
	public void addChar(String word){
		panel.addChar(word);
	}
	public void addWords(){
		panel.addWords();
	}
}
class MyPanel extends JPanel{
	ArrayList<String> words1 = new ArrayList<String>();
	ArrayList<String> words2 = new ArrayList<String>();
	ArrayList<String> words3 = new ArrayList<String>();
	ArrayList<String> myQuestion = new ArrayList<String>();
	String temp = "";
	List<String> entered = new ArrayList<String>();
	public MyPanel(){
		setBackground(Color.BLACK);
	}
	public void addWords(){
	words1.add("CAR");
	words1.add("BUS");
    words1.add("GOAT");
    words1.add("MEAT");
    words1.add("CAT");
    words1.add("BLUE");
    words1.add("KIWI");
    words1.add("LOUD");
    words1.add("ROAD");
    words1.add("RADIO");
    words1.add("SOUND");
    words2.add("SCIENCE");
    words2.add("ELEMENTARY");
    words2.add("COMPUTER");
    words2.add("FICTION");
    words2.add("MASTERY");
    words2.add("PUBLICIST");
    words2.add("AMAZON");
    words2.add("FORSAKEN");
    words2.add("HANGMAN");
    words2.add("CONSIDER");
    words2.add("EVIDENCE");
    words3.add("ABNEGATION");
    words3.add("CIRCUMSCRIBE");
    words3.add("DIAPHANOUS");
    words3.add("INCONTROVERTIBLE");
    words3.add("EXPURGATE");
    words3.add("LICENTIOUS");
    words3.add("OSTENSIBLE");
    words3.add("NEGLIGENT");
    words3.add("MORASS");
    words3.add("QUIXOTIC");
    words3.add("ZEPHYR");
	}
	String answer="";
	public void addChar(String catagory){
		myQuestion.clear();
		Random rnd = new Random();
		int index;
		switch(catagory){
		case "Easy":
			index = rnd.nextInt(words1.size());
			answer = words1.get(index);
			break;
		case "Normal":
			index = rnd.nextInt(words2.size());
			answer = words2.get(index);
			break;
		case "Difficult": 
			index = rnd.nextInt(words3.size());
			answer = words3.get(index);
			break;
		}
		List <String> letter = Arrays.asList(answer);
		for (int i = 0; i <letter.size(); i++){
			myQuestion.add(letter.get(i));
		}
	}
	int count = 0;
	public void paintComponent(Graphics g){
	Graphics2D g2 = (Graphics2D) g; 
	g2.setColor(Color.WHITE);
	g2.fillRect(0,0,getWidth(),getHeight());
	int x = 25; 
	int y = 100; 
	for (int i = 0; i <myQuestion.size(); i++){
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SansSarif", Font.BOLD, 50));
		g2.drawLine(750, 500, 950, 500);
		g2.drawLine(850, 50, 850, 500);
		g2.drawLine(750, 50, 850, 50);
		g2.drawLine(750, 50, 750, 100);
		g2.drawString("Incorrect Attempts:",15, y + 200);
		g2.drawString("Previous Guesses: ", 15, 400);
		for (int j = 0; j <answer.length(); j++){
			g2.drawLine(x + j*40, y, x + j*40 + 30, y);
			g2.drawString(temp, x, y);
		}
		   g2.drawString(count + "/6",500 , y+203);
			if (count == 1){
				g2.drawOval(710, 100, 75, 75);//Head
			}
			if (count == 2){
				g2.drawLine(750, 175, 750, 350);//Body
			}
			if (count == 3){
				g2.drawLine(750, 200, 700, 250);//Left Arm
			}
			if (count == 4){
				g2.drawLine(750, 200, 800, 250);//Right Arm
			}
			if (count == 5){
				g2.drawLine(750, 350, 700, 425);//Left Leg
			}
			if (count == 6){
				g2.drawLine(750, 350, 800, 425);//Right Leg
			}
		}
	}
}