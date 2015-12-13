import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel; 
import java.awt.GridBagLayout;
import java.util.Observer;
import java.util.Observable;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class View extends JFrame {
	private JTextField textField;
	private JPanel calculette = new JPanel();
	private JPanel numbers = new JPanel();
	private JPanel operator = new JPanel();
	private JPanel calcAdvance = new JPanel();
	private JTextField ecran = new JTextField(" ");
	private Controller _ctrl = null;
	private buttonNumListener _bNumListen = new buttonNumListener();
	private buttonOperator _bOpeListen = new buttonOperator();
	private butonAdvCalcListener _bAdvCaListen = new butonAdvCalcListener();
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	private JButton button4 = new JButton("4");
	private JButton button5 = new JButton("5");
	private JButton button6 = new JButton("6");
	private JButton button7 = new JButton("7");
	private JButton button8 = new JButton("8");
	private JButton button9 = new JButton("9");
	private JButton button0 = new JButton("0");
	private JButton buttonComma = new JButton(",");
	private JButton buttonAC = new JButton("AC");
	//private JButton buttonC = new JButton("C");
	private JButton buttonEqual = new JButton("="); 
	private JButton buttonPlus = new JButton("+");
	private JButton buttonSubs = new JButton("-");
	private JButton buttonMult = new JButton("x");
	private JButton buttonDiv = new JButton("/");
	private JButton buttonMod = new JButton("%");
	private JButton buttonSquare = new JButton("x²");
	private JButton buttonSquareRoot = new JButton("root");
	private JButton buttonInvertSign = new JButton("-x");
	private JButton buttonCosinus = new JButton("cos");
	private JButton buttonSinus = new JButton("sin");
	private JButton buttonTangente = new JButton("tan");
	private JButton buttonLogNeper = new JButton("ln");
	private JButton buttonExponentiel = new JButton("e");
	
	public View(Controller ctrl)
	{
		_ctrl = ctrl;
		initWindow();
	}
	
	public void setScreen(String str)
	{
		if (str.length() < 18)
		{
			Font f = new Font("SansSerif", Font.BOLD, 18);
			ecran.setFont(f);
		}
		else if (str.length() >= 18 && str.length() < 23)
		{
			Font f = new Font("SansSerif", Font.BOLD, 15);
			ecran.setFont(f);
		}
		else if (str.length() >= 23 && str.length() < 29)
		{
			Font f = new Font("SansSerif", Font.BOLD, 12);
			ecran.setFont(f);
		}
		else if (str.length() >= 29)
		{
			Font f = new Font("SansSerif", Font.BOLD, 9);
			ecran.setFont(f);
		}
		ecran.setText(str);
	}

	public void initWindow()
	{		
		Font font = new Font("SansSerif", Font.BOLD, 18);
		
		this.setTitle("bonjour");
		this.setSize(300, 275);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    ecran.setEditable(false);
	    ecran.setFont(font);
	    ecran.setHorizontalAlignment(SwingConstants.RIGHT);
	    	    
	    ecran.setPreferredSize(new Dimension(225, 30));
	    numbers.setPreferredSize(new Dimension(150, 125));
	    operator.setPreferredSize(new Dimension(100, 125));
	    calcAdvance.setPreferredSize(new Dimension(250, 100));
	    
	    buttonPlus.addActionListener(_bOpeListen);
	    buttonSubs.addActionListener(_bOpeListen);
	    buttonMult.addActionListener(_bOpeListen);
	    buttonEqual.addActionListener(_bOpeListen);
	    buttonDiv.addActionListener(_bOpeListen);
	    buttonAC.addActionListener(_bOpeListen);
	    buttonMod.addActionListener(_bOpeListen);
	    operator.add(buttonEqual);
	    operator.add(buttonPlus);
	    operator.add(buttonSubs);
	    operator.add(buttonMult);
	    operator.add(buttonDiv);
	    operator.add(buttonMod);
	    operator.add(buttonAC);
	    
	    button1.addActionListener(_bNumListen);
	    button2.addActionListener(_bNumListen);
	    button3.addActionListener(_bNumListen);
	    button4.addActionListener(_bNumListen);
	    button5.addActionListener(_bNumListen);
	    button6.addActionListener(_bNumListen);
	    button7.addActionListener(_bNumListen);
	    button8.addActionListener(_bNumListen);
	    button9.addActionListener(_bNumListen);
	    button0.addActionListener(_bNumListen);
	    buttonInvertSign.addActionListener(_bNumListen);;
	    buttonComma.addActionListener(_bNumListen);
	    button1.setMnemonic(KeyEvent.VK_1);
	    numbers.add(button1);
	    numbers.add(button2);
	    numbers.add(button3);
	    numbers.add(button4);
	    numbers.add(button5);
	    numbers.add(button6);
	    numbers.add(button7);
	    numbers.add(button8);
	    numbers.add(button9);
	    numbers.add(button0);
	    numbers.add(buttonComma);
	    numbers.add(buttonInvertSign);
	    
	    buttonSquare.addActionListener(_bAdvCaListen);
	    buttonSquareRoot.addActionListener(_bAdvCaListen);
	    buttonCosinus.addActionListener(_bAdvCaListen);
	    buttonSinus.addActionListener(_bAdvCaListen);
	    buttonTangente.addActionListener(_bAdvCaListen);
	    buttonLogNeper.addActionListener(_bAdvCaListen);
	    buttonExponentiel.addActionListener(_bAdvCaListen);
	    calcAdvance.add(buttonSquareRoot);
	    calcAdvance.add(buttonSquare);	   
	    calcAdvance.add(buttonLogNeper);
	    calcAdvance.add(buttonExponentiel);
	    calcAdvance.add(buttonCosinus);
	    calcAdvance.add(buttonSinus);
	    calcAdvance.add(buttonTangente);

	    calculette.add(ecran, BorderLayout.NORTH);
	    calculette.add(numbers, BorderLayout.WEST);
	    calculette.add(operator,  BorderLayout.EAST);
	    calculette.add(calcAdvance, BorderLayout.SOUTH);
	    
	    setContentPane(calculette);
	    setVisible(true);
	}
	
	
	public class buttonNumListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String num = ((JButton)act.getSource()).getText();
			_ctrl.sendNumberToModel(num);
		}
	}
	
	public class buttonOperator implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String ope = ((JButton)act.getSource()).getText();
			_ctrl.sendOperator(ope);
		}
	}
	
	public class butonAdvCalcListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String ope = ((JButton)act.getSource()).getText();
			_ctrl.sendAdvancedCalc(ope);
		}
	}
}