package javaCalculatrice;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class FirstCalculatrice {
	public static void main(String[] args) {
		// Crée un panneau
		JPanel window = new JPanel();
		
		// Affecte un gestionnaire de disposition à ce panneau
	    FlowLayout disposition = new FlowLayout(); 
	    window.setLayout(disposition);
	    
	    // Crée les contrôles en mémoire 
	    JLabel label1 = new JLabel("Nombre 1 :");
	    JTextField input1 = new JTextField(10);
	    JLabel label2 = new JLabel("Nombre 2 :");
	    JTextField input2 = new JTextField(10);
	    JLabel label3 = new JLabel("Facteur :");
	    JTextField factor = new JTextField(10);
	    JButton start = new JButton("Calculer");
	    
	    // Ajoute les contrôles au panneau
	    window.add(label1); 
	    window.add(input1);
	    window.add(label2);
	    window.add(input2);
	    window.add(label3);
	    window.add(factor);
	    window.add(start);
	    
	    // Crée le cadre et y ajoute le panneau 
	    JFrame cadre = new JFrame("Ma première calculatrice");

	    cadre.setContentPane(window);
			
	    // Positionne les dimensions et rend la fenêtre visible
	    cadre.setSize(400,100);
	    cadre.setVisible(true);
	    
	}
}
