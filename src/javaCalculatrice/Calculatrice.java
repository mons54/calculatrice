package javaCalculatrice;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Calculatrice extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private String[] tabString = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
	private JLabel screen = new JLabel();
	private Dimension dim = new Dimension(50, 40);
	private Dimension dim2 = new Dimension(50, 31);
	private String currentNumber = "0";
	private String currentOperator;
	private String display = "";
	
	Calculatrice() {
		
		this.setSize(240, 260); 
		this.setTitle("Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    screen = new JLabel(currentNumber);
	    screen.setFont(new Font("Arial", Font.BOLD, 13));
	    screen.setHorizontalAlignment(JLabel.RIGHT);
	    screen.setPreferredSize(new Dimension(220, 20));
	    JPanel operators = new JPanel();      
	    operators.setPreferredSize(new Dimension(55, 225));
	    JPanel numbers = new JPanel();
	    numbers.setPreferredSize(new Dimension(165, 225));
	    JPanel panScreen = new JPanel();
	    panScreen.setPreferredSize(new Dimension(220, 30));

	    for(int i = 0; i < tabString.length; i++) {
	    	String symbol = tabString[i];
	    	JButton button = new JButton(symbol);
	    	button.setPreferredSize(dim);
	        
	        if (symbol == "=") {
	        	button.addActionListener(new EgalListener());
	        	numbers.add(button);
	        } else if (symbol == "C") {
	        	button.setForeground(Color.red);
	        	button.setPreferredSize(dim2);
	        	button.addActionListener(new ResetListener());
	        	operators.add(button);
	        } else if (symbol == "+") {
	        	button.addActionListener(new AddListener());
	        	button.setPreferredSize(dim2);
	        	operators.add(button);
	        } else if (symbol == "-") {
	        	button.addActionListener(new SubtractListener());
	        	button.setPreferredSize(dim2);
	        	operators.add(button);
	        } else if (symbol == "*") {
	        	button.addActionListener(new MultiplyListener());
	        	button.setPreferredSize(dim2);
	        	operators.add(button);
	        } else if (symbol == "/") {
	        	button.addActionListener(new DivideListener());
	        	button.setPreferredSize(dim2);
	        	operators.add(button);
	        } else {
	        	button.addActionListener(new NumberListener());
	        	numbers.add(button);
	        }
	    }
	    
	    panScreen.add(screen);
	    panScreen.setBorder(BorderFactory.createLineBorder(Color.black));
	    panel.add(panScreen, BorderLayout.NORTH);
	    panel.add(numbers, BorderLayout.CENTER);
	    panel.add(operators, BorderLayout.EAST);
	    this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	private void addSymbol (String symbol) {
		if (currentOperator == symbol) {
			return;
		}
		if (currentOperator.isEmpty()) {
			display += " " + symbol + " ";
		} else {
			display = display.substring(0, display.length() - 3) + " " + symbol + " ";
		}
		currentNumber = "";
		currentOperator = symbol;
		screen.setText(display);
	}
	
	class NumberListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			String value = ((JButton)arg0.getSource()).getText();
			
			if (value == "." && currentNumber.contains(".")) {
				return;
			}
			
			currentOperator = "";
			currentNumber += value;
			display += value;
			screen.setText(display);
		}
	}

	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    
		    try {
		    	String result;
		    	double value = Double.parseDouble(engine.eval(screen.getText()).toString());
		    	if (value == (long) value) {
			    	result = String.format("%d",(long) value);
			    } else {
			    	result = String.valueOf(value);
			    }
		    	currentNumber = display = result;
		    	currentOperator = "";
		    	screen.setText(result);
		    } catch(ScriptException e) {
		    	System.out.println("Err");
	    	}
		}
	}

	class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addSymbol("+");
		}
	}

	class SubtractListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addSymbol("-");
		}
	}

	class MultiplyListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addSymbol("*");
		}
	}

	class DivideListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			addSymbol("/");
		}
	}

	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			currentOperator = "";
			currentNumber = "0";
			display = "";
			screen.setText(currentNumber);
		}
	}      

	
	public static void main(String[] args) {
		new Calculatrice();
	}
}
