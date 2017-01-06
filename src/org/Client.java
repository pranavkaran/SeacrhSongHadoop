package org;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Utilities.Constant;
import Utilities.myServer;

/* Clent class 
 * creates gui and call remote server
 * */
public class Client {
	
	BufferedReader clientBufferReader;
    PrintWriter clientPrintWriter;
    Socket clientSocket;
	
    String strScreenName;
    JFrame frame = new JFrame(strScreenName);
	JLabel lable = new JLabel("Enter artist name: ", 10);
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(30, 40);
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    
    JRadioButton option1 = new JRadioButton("Location");
    JRadioButton option2 = new JRadioButton("Tracks");
    JRadioButton option3 = new JRadioButton("Both");
    ButtonGroup group = new ButtonGroup();
    
    String givenArtistName = "";
	String artistLocation = "";
	String artistTracks = "";
	boolean bLocation;
	boolean bTracks;
	boolean bBoth;
    
	/*Initializes GUI*/
    public Client() {
    	// Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        
        group.add(option1);
        group.add(option2);
        group.add(option3);

        panel1.setLayout(new FlowLayout(20, 10, 0));
        panel1.add(option1);
        panel1.add(option2);
        panel1.add(option3);
        panel2.add(lable, "North");
        panel2.add(textField, "South");
        panel3.add(messageArea, "South");
        frame.getContentPane().add(panel1, "Center");
        frame.getContentPane().add(panel2, "North");
        frame.getContentPane().add(panel3, "South");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				givenArtistName = textField.getText();
                textField.setText("");
			}
        });
        
        RadioButtonActionListener actionListener = new RadioButtonActionListener();
        option1.addActionListener(actionListener);
        option2.addActionListener(actionListener);
        option3.addActionListener(actionListener);
        
        
        this.frame.setVisible(true);
    }
    
    /*Format data*/
    public void arrayListFromat(ArrayList<String> arr) {
    	artistLocation = "Location: \n";
    	artistTracks = "\nTracks: \n";
    	for (String object : arr) {
    		String[] o = object.split("<SEP>");
    		artistLocation =  artistLocation + o[0] + "\n";
    		System.out.println(artistLocation);
    		if(o[1].contains("<I>")) {
    			String[] s = o[1].split("<I>");
    			for (String string : s) {
    				artistTracks = artistTracks + string + "\n";
				}
    		} else {
    			artistTracks = o[1];
    		}
    		System.out.println(artistTracks);
		}
    }
    
    /*call remote RMIs on the basis of choice acordingly*/
    public void processInput(Remote server, String input) throws RemoteException {
    	textField.setEditable(true);
    	if (!input.isEmpty()) {
        	System.out.println(input);
    		//ArrayList<String> retArrayList = new ArrayList<String>();
    		//retArrayList = server.dataPull(input);
    		artistLocation = ((myServer) server).findLocation(input);
    		artistTracks = ((myServer) server).getSongs(input);
    		
    		//arrayListFromat(retArrayList);
    		//System.out.println(retArrayList);
    		
    		messageArea.setText("");
    		if (bLocation) {
    			messageArea.append(artistLocation);
    		} else if (bTracks){
    			messageArea.append(artistTracks);
    		} else if (bBoth) {
    			messageArea.append(artistLocation);
            	messageArea.append(artistTracks);
    		}
    		givenArtistName = "";
        	artistLocation = "";
        	artistTracks = "";
    	}
    }
    
    /*Run method*/
    @SuppressWarnings("unused")
	public void run(Remote server) throws IOException, NotBoundException{
    	
    	while (true) {
    		processInput(server, givenArtistName);
		}
    }
	
    /*Main method*/
	public static void main(String[] args) throws IOException, NotBoundException {
		Client objClient = new Client();
//		if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        //String name = "Server";
        Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
        Remote server = (Remote) registry.lookup(Constant.RMI_ID);
        objClient.run(server);
	}
	
	/*Radio button listener*/
	class RadioButtonActionListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent event) {
	        JRadioButton button = (JRadioButton) event.getSource();
	 
	        if (button == option1) {
	        	bLocation = true;
	        	bTracks = false;
	        	bBoth = false;
	        } else if (button == option2) {
	        	bLocation = false;
	        	bTracks = true;
	        	bBoth = false;
	        } else if (button == option3) {
	        	bLocation = false;
	        	bTracks = false;
	        	bBoth = true;
	        }
	    }
	}
}
