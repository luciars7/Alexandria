package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;

public class JFramaNewAuthor extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JButton btnCancel;
	private JLabel lblRelatedElements;
	private JLabel lblName;
	private JTextField textFieldDescription;
	private JLabel lblDiseases;
	private JList listDiseases;
	private JLabel lblDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramaNewAuthor frame = new JFramaNewAuthor();
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
	public JFramaNewAuthor() {
		setTitle("New Symptom");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 309, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);
		
		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		contentPane.add(lblDescription, gbc_lblDescription);
		
		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 1;
		contentPane.add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);
		
		lblRelatedElements = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElements = new GridBagConstraints();
		gbc_lblRelatedElements.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElements.gridx = 0;
		gbc_lblRelatedElements.gridy = 2;
		contentPane.add(lblRelatedElements, gbc_lblRelatedElements);
		
		lblDiseases = new JLabel("Diseases");
		GridBagConstraints gbc_lblDiseases = new GridBagConstraints();
		gbc_lblDiseases.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiseases.gridx = 0;
		gbc_lblDiseases.gridy = 3;
		contentPane.add(lblDiseases, gbc_lblDiseases);
		
		listDiseases = new JList();
		GridBagConstraints gbc_listDiseases = new GridBagConstraints();
		gbc_listDiseases.insets = new Insets(0, 0, 5, 0);
		gbc_listDiseases.fill = GridBagConstraints.BOTH;
		gbc_listDiseases.gridx = 1;
		gbc_listDiseases.gridy = 3;
		contentPane.add(listDiseases, gbc_listDiseases);
		
		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 8;
		contentPane.add(addButton, gbc_addButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 9;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

}
