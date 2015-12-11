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
	private Model _model = null;
	private buttonNumListener _bNumListen = new buttonNumListener();
	private buttonOperator _bOpeListen = new buttonOperator();
	private butonAdvCalcListener _bAdvCaListen = new butonAdvCalcListener();
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton button0 = new JButton("0");
	JButton buttonComma = new JButton(",");
	JButton buttonAC = new JButton("AC");
	JButton buttonC = new JButton("C");
	JButton buttonEqual = new JButton("="); 
	JButton buttonPlus = new JButton("+");
	JButton buttonSubs = new JButton("-");
	JButton buttonMult = new JButton("x");
	JButton buttonDiv = new JButton("/");
	JButton buttonMod = new JButton("%");
	JButton buttonSquare = new JButton("x²");
	JButton buttonSquareRoot = new JButton("root");
	JButton buttonInvertSign = new JButton("-x");
	JButton buttonCosinus = new JButton("cos");
	JButton buttonSinus = new JButton("sin");
	JButton buttonTangente = new JButton("tan");
	JButton buttonLogNeper = new JButton("ln");
	JButton buttonExponentiel = new JButton("e");
	/*
	 * grand changement a faire !
	 * entre le controller et la view
	 * la view ne prend plus de controller et
	 * c'est le controller qui est observer
	 * 		-> c'est bon c'est fait :-)
	 * 		-> euu pour le faite que la vue prenne un controller :  a reflechir 
	 */
	
	public View(Controller ctrl)
	{
		_ctrl = ctrl;
		Font font = new Font("SansSerif", Font.BOLD, 18);
		
		this.setTitle("bonjour");
		this.setSize(300, 275);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    ecran.setEditable(false);
	    ecran.setFont(font);
	    ecran.setHorizontalAlignment(SwingConstants.RIGHT);
	    
	    //calculette.setBackground(Color.ORANGE);        
	    //On pr�vient notre JFrame que notre JPanel sera son content pane
	    //this.setContentPane();
	    
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
	    
	    /*
	     * JButton buttonCosinus = new JButton("cos");
	JButton buttonSinus = new JButton("sin");
	JButton buttonTangente = new JButton("tan");
	JButton buttonLogNeper = new JButton("ln");
	JButton buttonExponentiel = new JButton("e");
	     */
	    
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
	
	class OperatorAction implements ActionListener{
		public void actionPerformed(ActionEvent act)
		{
			System.out.println("dans Action operateur");
		}
	}
	
	public void setScreen(String str)
	{
		System.out.println("dans set string " + str.length());
		
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
		//ici je vais mettre tout ce que j'ai mis plus haut
	}
	
	/*@Override
	public void update(Observable o, Object arg)
	{
		Model model = (Model) o;
		
		//weatherUpdate = (ObservableExample) observable;
		//System.out.println("Weather Report Live. Its "+weatherUpdate.getWeather());
		this.setScreen(model.getToEval());
		System.out.println("il y a une MAJ de l'obj");
	}*/
	
	public class buttonNumListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String num = ((JButton)act.getSource()).getText();
			System.out.println("Ici view !");
			_ctrl.sendNumberToModel(num);
		}
	}
	
	public class buttonOperator implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String ope = ((JButton)act.getSource()).getText();
			System.out.println("la !");
			_ctrl.sendOperator(ope);
		}
	}
	public class butonAdvCalcListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String ope = ((JButton)act.getSource()).getText();
			System.out.println("la !");
			_ctrl.sendAdvancedCalc(ope);
		}
	}
}