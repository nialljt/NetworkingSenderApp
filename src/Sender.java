import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.*;

public class Sender extends JPanel {

	public static byte N = 10; // the N from "Go-Back-N"
	public static byte MAX_SEQ_NUMBER = 20; // The maximum sequence number
	public static int PORT = 7609; // the receiver's port
	public static int DELAY = 1000; // 1 second delay for timeout

	// the socket
	DatagramSocket socket;
	// the ip address of the receiver
	InetAddress ipAddress;

	// variables from the algorithm
	byte base = 1;
	byte nextSeqNum = 1; // Pay attention to this

	// needed for receiver thread
	volatile boolean done = false;

	// number of packets sent, but unacknowledged
	int unAckedPackets = 0;

	JPanel pan1;
	JPanel pan2;
	JTextArea message;
	JButton sendButton;
	JScrollPane scrollPane;

	static Toolkit tk = Toolkit.getDefaultToolkit();
	static Dimension d = tk.getScreenSize();

	int pixelWidth = 0;
	int pixelHeight = 0;
	static int rez = 0;
	
	public Sender() {
		super(new BorderLayout());
		
		setSizeofApp();
		
		
		message = new JTextArea();
		pan1 = new JPanel();
		pan2 = new JPanel();
		sendButton = new JButton("SEND");
		sendButton.setPreferredSize(new Dimension(70 , 20));
		scrollPane = new JScrollPane(message);
		pan1.setLayout(new FlowLayout());
		pan2.setLayout(new FlowLayout());
		scrollPane.setPreferredSize(new Dimension((((rez ==1)? 500:350)), (((rez ==1)? 300:225))));
		pan1.add(scrollPane);
		pan2.add(sendButton);
		add(pan1, BorderLayout.NORTH);
		add(pan2, BorderLayout.SOUTH);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// sendMessage(event.getActionCommand());

			}
		});
	}

	private void setSizeofApp() {
		pixelWidth = d.width;
		pixelHeight = d.height;
	
		if(pixelWidth > 1400){
			rez = 1;
		}else{
			rez = 2;
		}
	}

	public static void main(String[] args) throws IOException {

		JFrame f = new JFrame("Packet Sender");
		f.add(new Sender(), BorderLayout.CENTER);

		f.setBounds((d.width - (((rez ==1)? 550:450))) / 2, (d.height - (((rez ==1)? 400:300))) / 2, (((rez ==1)? 550:450)), (((rez ==1)? 400:300)));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);

	}

}
