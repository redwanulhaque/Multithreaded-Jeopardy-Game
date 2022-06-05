import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client_Server extends JFrame {  // Main class for client server

    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    private String name, message;
	private JPanel contentPane;
	private int position;
	private JTextField getName, getMessage;	
	private JTextArea textArea, scoreBoard;
	private static JPanel board; //Name of board(panel)
	private JButton [] myJeopardy; //Array for buttons
	
/*******************************************************************START JEOPARDY GAME BOARD*********************************************************************/           
	public void game() {
		
		board = new JPanel(new GridLayout(6,5,5,5)); // Grid layout for categories
	  	board.setBounds(10, 45, 669, 503);  // size
	  	contentPane.add(board); // adding it to the display
		
	  	// Universe column
	  	JButton music = new JButton("SPACE");
	  	music.setForeground(new Color(138, 43, 226));
	  	music.setFont(new Font("Jokerman", Font.PLAIN, 20));
	  	music.setBackground(new Color(255, 228, 225));
	  	board.add(music);
      
	  	// tv show column
	  	JButton TVSHOW = new JButton("TV SHOW");
	  	TVSHOW.setForeground(new Color(138, 43, 226));
	  	TVSHOW.setFont(new Font("Jokerman", Font.PLAIN, 20));
	  	TVSHOW.setBackground(new Color(255, 228, 225));
	  	board.add(TVSHOW);
	  	
	  	// movie column
	  	JButton MOVIE = new JButton("MOVIE");
	  	MOVIE.setForeground(new Color(138, 43, 226));
	  	MOVIE.setFont(new Font("Jokerman", Font.PLAIN, 20));
	  	MOVIE.setBackground(new Color(255, 228, 225));
	  	board.add(MOVIE);
	  	
	  	// csci column
	  	JButton CSCI = new JButton("CSCI");
	  	CSCI.setForeground(new Color(138, 43, 226));
	  	CSCI.setFont(new Font("Jokerman", Font.PLAIN, 20));
	  	CSCI.setBackground(new Color(255, 228, 225));
	  	board.add(CSCI);
      
	  	// math column
	  	JButton MATH = new JButton("MATH");
	  	MATH.setForeground(new Color(138, 43, 226));
	  	MATH.setFont(new Font("Jokerman", Font.PLAIN, 20));
	  	MATH.setBackground(new Color(255, 228, 225));
	  	board.add(MATH);
      
	  	myJeopardy = new JButton[26];// 25 buttons
	  	 	
	  	for (int i = 1; i < myJeopardy.length; i++){ 
	  			  			
	  		myJeopardy[i] = new JButton("" + i);// making new button	
	  	  			
	  		myJeopardy[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String actionCommand = e.getActionCommand();
	
						if (!getName.getText().isEmpty()) {  // // when the client has put his/her name and messages
		                	
		                    name = getName.getText().trim();  // get the name of the host
		                    
		                    try {  // sending the box chosen by the client to the host
                                position = Integer.parseInt(actionCommand);// convert string into int
                                output.writeInt(position);
                                questions(position);
                                textArea.append(name + " choose box: " + position + "\n");
		                    }
		                    
		                    catch (IOException ioe) {
		                        System.err.println(ioe);
		                    }  // catch
		                    
		                }  // if loop
		                
		                else if (getName.getText().isEmpty())  // if user input message with out a name show this error
		                    JOptionPane.showMessageDialog(null, "Please enter a name!", "No name detected", JOptionPane.ERROR_MESSAGE);

				}}); // To make it click able
	  		
			myJeopardy[i].setForeground(Color.BLACK);
			myJeopardy[i].setBackground(new Color(240, 255, 240));
			board.add(myJeopardy[i]); // adding to to the JPanel
		} // for loop 		
	} // game method
	
	public void questionHandler(int positionition, String question) { // with this method we will handle the questioning format
		
		myJeopardy[positionition].setText(question);  // making the questing and adding it to the method
		myJeopardy[positionition].setEnabled(false);
		myJeopardy[positionition].setFont(new Font("Arial", Font.BOLD,11));  // font style
		myJeopardy[positionition].setBackground(Color.BLACK);
	}
	
	public void questions(int position) {
	
		switch (position){
		
		// space questions
		case 1:
				questionHandler(position, "<html>What is the name of the force which keeps the planets in orbit around the sun?</html>");						
				break;

		case 6:
				questionHandler(position, "<html>Which is the largest planet in the solar system?</html>");					
				break;
			
		case 11: 
				questionHandler(position, "<html>Which is the largest moon in the solar system?</html>");				
				break;

		case 16:
				questionHandler(position, "<html>Which planet is the densest?</html>");								
				break;

		case 21:
				questionHandler(position, "<html>What shape is the Milky Way?</html>");					
				break;

		// tv show questions		
		case 2:
				questionHandler(position, "<html>In the show The Boys, who is Homelander copy cat of?</html>");						
				break;

		case 7:
				questionHandler(position, "<html>South Park takes place in which state?</html>");					
				break;
		
		case 12: 
				questionHandler(position, "<html>What is the name of Negan’s bat on The Walking Dead?</html>");			
				break;

		case 17:
				questionHandler(position, "<html>In The Twilight Zone, who was the howling man?</html>");								
				break;

		case 22:
				questionHandler(position, "<html>Which character’s catchphrase was “pop pop!” on Community?</html>");					
				break;
		
		// movies
		case 3:
				questionHandler(position, "<html>In The Matrix, does Neo take the blue pill or the red pill?</html>");					
				break;

		case 8:
				questionHandler(position, "<html>In what 1976 thriller does Robert De Niro famously say “You talkin’ to me?”</html>");				
				break;
	
		case 13: 
				questionHandler(position, "<html>What Hollywood movie star plays himself in Zombieland?</html>");			
				break;

		case 18:
				questionHandler(position, "<html>What is the highest-grossing R-rated movie of all time?</html>");								
				break;

		case 23:
				questionHandler(position, "<html>Which 1927 war drama was the first movie to ever win Best Picture?</html>");		
				break;
			
		// CSCI
		case 4:
				questionHandler(position, "<html>When was the first 1 GB disk drive released in the world?</html>");		
				break;

		case 9:
				questionHandler(position, "<html>What was the name of the first computer programmer?”</html>");		
				break;

		case 14: 
				questionHandler(position, "<html>Which popular company designed the first CPU?</html>");
				break;

		case 19:
				questionHandler(position, "<html>What is the name of the first operating system designed by Microsoft?</html>");		
				break;

		case 24:
				questionHandler(position, "<html>Which is the single most popular computer system ever sold?</html>");		
				break;
		
		// MATH
		case 5:
				questionHandler(position, "<html>What is the only number that does not have its Roman numeral?</html>");		
				break;

		case 10:
				questionHandler(position, "<html>What is the name of the symbol used in the division?”</html>");		
				break;

		case 15: 
				questionHandler(position, "<html>What flat image can also be displayed in 3D?</html>");
				break;

		case 20:
				questionHandler(position, "<html>What Was The Number 0 Originally Called?</html>");		
				break;

		case 25:
				questionHandler(position, "<html>What is the median Erdős number of Fields Medalists?</html>");				
				break;
		}//End switch
	}
	
/*******************************************************************END JEOPARDY GAME BOARD******************************************************************/ 
	

	
/*******************************************************START CONNECTION TO THE SERVER*****************************************************************************/                 
    public Client_Server(String hostServer, int connection) {  // establish a connection with the host server
	
        // Main display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 100, 950, 600);  // making up the locations
        setResizable(false);
        setTitle("Client_Server");  // GUI Name
    	contentPane = new JPanel();  
		setContentPane(contentPane);
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setLayout(null);  // setting it to null, we can freely move our design around
		
		game(); // calling on the main jeopardy game method and connecting it to the main display of the board

		// Title of the Game
		JTextArea title = new JTextArea();  // Title of the board "Welcome To Jeopardy 2022!"
		title.setForeground(new Color(255, 0, 0));  // color of title
		title.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));  // font
		title.setEditable(false);  // disable edit text
		title.setBackground(new Color(255, 204, 153));
		title.setBounds(160, 5, 369, 29);
		title.setText("            Welcome To Jeopardy 2022! ");  // naming the title
		add(title);  // adding it to the display
		
        // scroll bar for score board
		JScrollPane scrollPane_1 = new JScrollPane();  // scroll bar to the text area
		scrollPane_1.setBounds(688, 35, 240, 100);
		contentPane.add(scrollPane_1);  // adding it to the display
		
		// score board 
		JTextArea scoreTitle = new JTextArea();  // Title of the board "Welcome To Jeopardy 2022!"
		scoreTitle.setForeground(new Color(255, 0, 0));  // color of title
        Font font1 = new Font("Segoe Script", Font.BOLD, 16);
        scoreTitle.setFont(font1);
        scoreTitle.setEditable(false);  // disable edit text
        scoreTitle.setBackground(new Color(255, 204, 153));
		scoreTitle.setBounds(718, 5, 184, 25);
		scoreTitle.setText("       Score Board");  // naming the title
		contentPane.add(scoreTitle);  // adding it to the display	
		scoreBoard = new JTextArea();  // Title of the board "Welcome To Jeopardy 2022!"
		scrollPane_1.setViewportView(scoreBoard);
		scoreBoard.setBounds(688, 35, 240, 100);
		scoreBoard.setEditable(false);  // disable edit text
		scoreBoard.setFont(new Font("Times New Roman", Font.PLAIN, 16));  // text style
		scoreBoard.setBackground(new Color(216, 191, 216));
		
		// Name area
		JTextArea nameTitle = new JTextArea("Name:");
		nameTitle.setBounds(688, 525, 55, 25); 
        Font font = new Font("Segoe Script", Font.BOLD, 16);
        nameTitle.setFont(font);
        nameTitle.setForeground(Color.RED);
        nameTitle.setBackground(new Color(255, 204, 153));
        nameTitle.setEditable(false);
        add(nameTitle);  // adding it to the display
        getName = new JTextField();  // This is where the user will input their name
        getName.setBounds(746, 525, 184, 25); 
        getName.setBackground(new Color(240, 255, 255));
        add(getName);  // adding it to the display
        
        // Message area
		JTextArea messageTitle = new JTextArea("Message:");
		messageTitle.setBounds(688, 480, 61, 25); 
        Font font2 = new Font("Segoe Script", Font.BOLD, 13);
        messageTitle.setFont(font2);
        messageTitle.setForeground(Color.RED);
        messageTitle.setEditable(false);
        messageTitle.setBackground(new Color(255, 204, 153));
        add(messageTitle);  // adding it to the display
        getMessage = new JTextField();  // This is where the client will type their message
        getMessage.setBounds(746, 470, 184, 39);
        getMessage.setBackground(new Color(240, 248, 255));
        add(getMessage);  // adding it to the display
        
        // scroll bar for text area
		JScrollPane scrollPane_2 = new JScrollPane();  // scroll bar to the text area
		scrollPane_2.setBounds(688, 150, 240, 309);
		contentPane.add(scrollPane_2);  // adding it to the display
        		
		// Text area
        textArea = new JTextArea("Please Start a Conversation ...\n\n");  // this is where the input of the text between the clients/host will be displayed
		scrollPane_2.setViewportView(textArea);
        textArea.setBounds(688, 150, 240, 309);
        textArea.setEditable(false);
		textArea.setBackground(new Color(230, 230, 250));
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));  // text style
		textArea.setForeground(Color.BLACK);
        
        setVisible(true);  // Making the GUI visiable
        
        getMessage.addActionListener(new ActionListener(){
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                if (!getName.getText().isEmpty() && !getMessage.getText().isEmpty()) {  // // when the client has put his/her name and messages
                	
                    name = getName.getText().trim();  // get the name of the host
                    message = getMessage.getText().trim();  // message sent by the host
                    
                    try {
                        output.writeUTF(name);  // write name to the host
                        output.writeUTF(message); // write message to the host
                        getMessage.setText(""); // after message is sent clear the message area                      
                    }
                    
                    catch (IOException ioe) {
                        System.err.println(ioe);
                    }  // catch
                }  // if loop
                
                else if (getName.getText().isEmpty())  // if user input message with out a name show this error
                    JOptionPane.showMessageDialog(null, "Please enter a name!", "No name detected", JOptionPane.ERROR_MESSAGE);
                
                else  // if the user enter a message without a name then show this error
                    JOptionPane.showMessageDialog(null, "Please enter a chat message!", "No message detected", JOptionPane.ERROR_MESSAGE);
                
            	}  // actionPerformed
        	});  // actionListener       
        
        try {  // connect to the host server
        	
            socket = new Socket(hostServer, connection);  // connecting to the host server
            textArea.append("Connected to " + socket + '\n');  // after connected, display this message

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
                        
            while(true) {  // being able to type name and input
                
                String rName = input.readUTF();  // read the name of the host and display to the client server 
                String rMessage = input.readUTF();  // read the message of the host and display it in the client
                textArea.append(rName + ": " + rMessage + '\n');  // display to the text area  
                
//                String score = input.readUTF();
//                scoreBoard.append(score + ": " + '\n');  // display to the text area  
            }  // while
        }  // try
        
        catch(IOException ioe) {  // Exception error
            System.err.println(ioe);
        } 
    }
/****************************************************************END CONNECTION TO THE SERVER*******************************************************************/             

    public static void main(String[] args) {
    	
        String hostServer =  "192.168.1.109";  // Address number
        int connection = 2001;  // name of the connection
        
        new Client_Server(hostServer, connection);       
    }
}