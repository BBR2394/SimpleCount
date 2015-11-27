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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class View extends JFrame implements Observer{
	private JPanel pan = new JPanel();
	private JTextField textField;
	private JPanel calculette = new JPanel();
	private JPanel numbers = new JPanel();
	private JPanel operator = new JPanel();
	private JTextField ecran = new JTextField("&");
	private Controller _ctrl = null;
	private Model _model = null;
	private boutonNumListener _bNumListen = null;
	private boutonOperator _bOpeListen = new boutonOperator();
	JButton bouton1 = new JButton("1");
	JButton bouton2 = new JButton("2");
	JButton bouton3 = new JButton("3");
	JButton bouton4 = new JButton("4");
	JButton bouton5 = new JButton("5");
	JButton bouton6 = new JButton("6");
	JButton bouton7 = new JButton("7");
	JButton bouton8 = new JButton("8");
	JButton bouton9 = new JButton("9");
	JButton bouton0 = new JButton("0");
	JButton boutonComma = new JButton(",");
	//OperatorAction opAct = new OperatorAction();
	JButton buttonAC = new JButton("AC");
	JButton buttonC = new JButton("C");
	JButton buttonEqual = new JButton("="); 
	JButton boutonPlus = new JButton("+");
	JButton boutonSubs = new JButton("-");
	JButton boutonMult = new JButton("x");
	
	public View(Controller ctrl, Model mod)
	{
		_ctrl = ctrl;
		_model = mod;
		this.setTitle("bonjour");
		this.setSize(250, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    ecran.setEditable(false);
	    ecran.setHorizontalAlignment(SwingConstants.RIGHT);
	    
	    calculette.setBackground(Color.ORANGE);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    //this.setContentPane();
	    
	    /*
	    textField = new JTextField();
	    textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    textField.setEditable(false);
	    textField.setColumns(10);
	    */
	    
	    ecran.setPreferredSize(new Dimension(225, 25));
	    numbers.setPreferredSize(new Dimension(150, 200));

	    boutonPlus.addActionListener(_bOpeListen);
	    boutonSubs.addActionListener(_bOpeListen);
	    boutonMult.addActionListener(_bOpeListen);
	    buttonEqual.addActionListener(_bOpeListen);
	    operator.add(buttonEqual);
	    operator.add(boutonPlus);
	    operator.add(boutonSubs);
	    operator.add(boutonMult);
	    
	    _bNumListen = new boutonNumListener();
	    bouton1.addActionListener(_bNumListen);
	    bouton2.addActionListener(_bNumListen);
	    bouton3.addActionListener(_bNumListen);
	    bouton4.addActionListener(_bNumListen);
	    bouton5.addActionListener(_bNumListen);
	    bouton6.addActionListener(_bNumListen);
	    bouton7.addActionListener(_bNumListen);
	    bouton8.addActionListener(_bNumListen);
	    bouton9.addActionListener(_bNumListen);
	    bouton0.addActionListener(_bNumListen);
	    boutonComma.addActionListener(_bNumListen);
	    numbers.add(bouton1);
	    numbers.add(bouton2);
	    numbers.add(bouton3);
	    numbers.add(bouton4);
	    numbers.add(bouton5);
	    numbers.add(bouton6);
	    numbers.add(bouton7);
	    numbers.add(bouton8);
	    numbers.add(bouton9);
	    numbers.add(bouton0);
	    numbers.add(boutonComma);
	    calculette.add(ecran, BorderLayout.NORTH);
	    calculette.add(numbers, BorderLayout.CENTER);
	    calculette.add(operator,  BorderLayout.EAST);
	    
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
		ecran.setText(str);
	}

	public void initWindow()
	{		
		//ici je vais mettre tout ce que j'ai mis plus haut
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		_model = (Model) o;
		
		//weatherUpdate = (ObservableExample) observable;
		//System.out.println("Weather Report Live. Its "+weatherUpdate.getWeather());
		this.setScreen(_model.getToEval());
		System.out.println("il y a une MAJ de l'obj");
	}
	
	public class boutonNumListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String num = ((JButton)act.getSource()).getText();
			System.out.println("Ici !");
			_ctrl.sendNumberToModel(num);
		}
	}
	
	public class boutonOperator implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			String ope = ((JButton)act.getSource()).getText();
			System.out.println("la !");
			_ctrl.sendOperator(ope);
		}
	}

}