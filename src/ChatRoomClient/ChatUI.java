package ChatRoomClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class ChatUI {

	private JFrame frmTheChatRoom;

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
		frmTheChatRoom.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 38, 331, 318);
		frmTheChatRoom.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setForeground(SystemColor.controlText);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(287, 376, 77, 21);
		frmTheChatRoom.getContentPane().add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(393, 38, 214, 318);
		frmTheChatRoom.getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Chat Area");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setForeground(new Color(250, 250, 210));
		lblNewLabel.setBounds(33, 11, 108, 31);
		frmTheChatRoom.getContentPane().add(lblNewLabel);
		
		JLabel lblActiveUsers = new JLabel("Active Users");
		lblActiveUsers.setForeground(new Color(250, 250, 210));
		lblActiveUsers.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblActiveUsers.setBackground(SystemColor.activeCaption);
		lblActiveUsers.setBounds(457, 16, 108, 21);
		frmTheChatRoom.getContentPane().add(lblActiveUsers);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(33, 376, 244, 21);
		frmTheChatRoom.getContentPane().add(textPane);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setForeground(Color.BLACK);
		btnLeave.setFont(new Font("Georgia", Font.PLAIN, 17));
		btnLeave.setBackground(SystemColor.activeCaption);
		btnLeave.setBounds(530, 376, 77, 21);
		frmTheChatRoom.getContentPane().add(btnLeave);
		frmTheChatRoom.setBounds(100, 100, 633, 457);
		frmTheChatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
