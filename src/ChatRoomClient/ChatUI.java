package ChatRoomClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class ChatUI implements ActionListener {

	JFrame frmTheChatRoom;
	protected JPanel joinPanel,ChatPanel,ActiveUserPanel;
	private JTextField userField;
	private JButton joinButton;
	private String user;
	private JList<String> _userList;
	private List<String> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatUI window = new ChatUI();
					window.frmTheChatRoom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTheChatRoom = new JFrame();
		frmTheChatRoom.setTitle("The Chat Room");
		frmTheChatRoom.setBackground(Color.WHITE);
		frmTheChatRoom.getContentPane().setBackground(Color.DARK_GRAY);
		frmTheChatRoom.setResizable(false);
		// Join Room Windows
		joinPanel = new JPanel();
		joinPanel.setBounds(33, 38, 488, 318);
		joinPanel.setBackground(Color.WHITE);
		joinPanel.setBackground(Color.DARK_GRAY);
		frmTheChatRoom.getContentPane().add(joinPanel, BorderLayout.CENTER);
		joinPanel.setLayout(null);
		
		//visibility
		
		
		
		JLabel userFieldLabel = new JLabel("UserName:");
		userFieldLabel.setBounds(143, 126, 97, 32);
		userFieldLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
		userFieldLabel.setBackground(SystemColor.activeCaption);
		userFieldLabel.setForeground(new Color(250, 250, 210));
		joinPanel.add(userFieldLabel);
		
		userField = new JTextField();
		userField.setBounds(250, 135, 123, 20);
		joinPanel.add(userField);
		userField.setColumns(10);
		
		joinButton = new JButton("Join Room");
		joinButton.setBounds(276, 223, 97, 23);
		joinButton.addActionListener(this);
		joinButton.setForeground(SystemColor.controlText);
		joinButton.setBackground(SystemColor.activeCaption);
		joinButton.setFont(new Font("Georgia", Font.PLAIN, 12));
		joinPanel.add(joinButton);
		
		JLabel ChatLabel = new JLabel("Chat Room:");
		ChatLabel.setBounds(143, 175, 97, 14);
		ChatLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
		ChatLabel.setBackground(SystemColor.activeCaption);
		ChatLabel.setForeground(new Color(250, 250, 210));
		joinPanel.add(ChatLabel);
		
		JComboBox RoomcomboBox = new JComboBox();
		RoomcomboBox.setBounds(250, 174, 123, 22);
		joinPanel.add(RoomcomboBox);
		frmTheChatRoom.setBounds(100, 100, 633, 457);
		frmTheChatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//*********************************** Join Room Panel ended 
		
		
		
		//*********************************** Chat Room 
		ChatPanel = new JPanel();
		ChatPanel.setBounds(33, 38, 331, 318);
		ChatPanel.setBackground(Color.WHITE);
		ChatPanel.setBackground(Color.DARK_GRAY);
		frmTheChatRoom.getContentPane().add(ChatPanel, BorderLayout.CENTER);
		ChatPanel.setLayout(null);
		ChatPanel.setVisible(false);
		
		JPanel ChatAreaPanel = new JPanel();
		ChatAreaPanel.setBounds(33, 38, 331, 318);
		ChatPanel.add(ChatAreaPanel);
		
		JButton sendButton = new JButton("Send");
		sendButton.setForeground(SystemColor.controlText);
		sendButton.setBackground(SystemColor.activeCaption);
		sendButton.setFont(new Font("Georgia", Font.PLAIN, 17));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sendButton.setBounds(287, 376, 77, 21);
		ChatPanel.add(sendButton);
		
		ActiveUserPanel = new JPanel();
		ActiveUserPanel.setBackground(Color.LIGHT_GRAY);
		ActiveUserPanel.setBounds(393, 38, 214, 318);
		ChatPanel.add(ActiveUserPanel);
		
	
		
		JLabel ChatAreaLabel = new JLabel("Chat Area");
		ChatAreaLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
		ChatAreaLabel.setBackground(SystemColor.activeCaption);
		ChatAreaLabel.setForeground(new Color(250, 250, 210));
		ChatAreaLabel.setBounds(33, 11, 108, 31);
		ChatPanel.add(ChatAreaLabel);
		
		JLabel ActiveUsersLabel = new JLabel("Active Users");
		ActiveUsersLabel.setForeground(new Color(250, 250, 210));
		ActiveUsersLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
		ActiveUsersLabel.setBackground(SystemColor.activeCaption);
		ActiveUsersLabel.setBounds(457, 16, 108, 21);
		ChatPanel.add(ActiveUsersLabel);
		
		JTextPane chatTextFiled = new JTextPane();
		chatTextFiled.setBounds(33, 376, 244, 21);
		ChatPanel.add(chatTextFiled);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setForeground(SystemColor.controlText);
		btnLeave.setFont(new Font("Georgia", Font.PLAIN, 17));
		btnLeave.setBackground(SystemColor.activeCaption);
		btnLeave.setBounds(517, 376, 77, 21);
		ChatPanel.add(btnLeave);
		
		_userList = new JList<String>();
		_userList.setBackground(Color.LIGHT_GRAY);
		_userList.setFont(new Font("Georgia", Font.PLAIN, 12));
        _userList.setVisibleRowCount(6);
	}
	
	public void updateList(String[] userList)
	{
		users = new ArrayList<String>();
		
		System.out.println("pol");
        
		for(String s : userList){
        	users.add(s);
        	System.out.println(s+ "pol");
        }
        
		JScrollPane listScrollPane = new JScrollPane(_userList);
		ActiveUserPanel.add(listScrollPane,BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == joinButton)
			{
				user = userField.getText();
				joinPanel.setVisible(false);
				ChatPanel.setVisible(true);
				Client nC = new Client(this,user);
				nC.clientStart();
				System.out.println("iooo");
				
			}
			
		} catch(Exception err) {
			System.err.println(err.toString());
			err.printStackTrace();
		}
	}
	
	
	

}
