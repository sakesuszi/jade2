// Assign the budget - certain amount of money - to the buyer agent.
// When the buyer makes a purchase (receives INFORM message confirming the successful purchase),
// budget decreases - money is withdrawn from the budget. Agent cannot spend more money than it owns.
package jadelab2;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BookSellerGui extends JFrame {	
	private BookSellerAgent myAgent;
	
	private JTextField titleField, priceField, shippingField; // dodajemy pole shippingField
	
	BookSellerGui(BookSellerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2)); // zwiększamy ilość wierszy
		p.add(new JLabel("Title:"));
		titleField = new JTextField(15);
		p.add(titleField);
		p.add(new JLabel("Price:"));
		priceField = new JTextField(15);
		p.add(priceField);
		p.add(new JLabel("Shipping:")); // dodajemy labelke Shipping
		shippingField = new JTextField(15); // dodajemy pole do wprowadzania cen dostaw
		p.add(shippingField); // dodajemy aby wyśwetliło się w GUI
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					String price = priceField.getText().trim();
					String shippingPrice = shippingField.getText().trim(); // pobieramy do zmiennej wartosć wprowadzoną wyżej
					// myAgent.updateCatalogue(title, Integer.parseInt(price));
					myAgent.updateCatalogue(title, Integer.parseInt(price)+Integer.parseInt(shippingPrice)); //dodajemy cene produktu do ceny dostawy
					titleField.setText("");
					priceField.setText("");
					shippingField.setText(""); // zerujemy
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void display() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
	}	
}
