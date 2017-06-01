package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
	private JTextField textFieldNewAssociation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 308, 168);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewAssociation = new JLabel("New association");
		GridBagConstraints gbc_lblNewAssociation = new GridBagConstraints();
		gbc_lblNewAssociation.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewAssociation.anchor = GridBagConstraints.EAST;
		gbc_lblNewAssociation.gridx = 0;
		gbc_lblNewAssociation.gridy = 0;
		getContentPane().add(lblNewAssociation, gbc_lblNewAssociation);
		
		textFieldNewAssociation = new JTextField();
		GridBagConstraints gbc_textFieldNewAssociation = new GridBagConstraints();
		gbc_textFieldNewAssociation.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNewAssociation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNewAssociation.gridx = 1;
		gbc_textFieldNewAssociation.gridy = 0;
		getContentPane().add(textFieldNewAssociation, gbc_textFieldNewAssociation);
		textFieldNewAssociation.setColumns(10);
		
		JButton btnAddChanges = new JButton("Add changes");
		btnAddChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnAddChanges = new GridBagConstraints();
		gbc_btnAddChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddChanges.gridx = 0;
		gbc_btnAddChanges.gridy = 6;
		getContentPane().add(btnAddChanges, gbc_btnAddChanges);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		getContentPane().add(btnCancel, gbc_btnCancel);
	}

}
