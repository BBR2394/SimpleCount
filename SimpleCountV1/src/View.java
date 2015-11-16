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
	private JTextField ecran = new JTextField("&");
	private Controller _ctrl = null;
	private Model _model = null;
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
	OperatorAction opAct = new OperatorAction();
	
	public View(Controller ctrl)
	{
		_ctrl = ctrl;
		this.setTitle("bonjour");
		this.setSize(250, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    //ecran.setEditable(false);
	    
	    calculette.setBackground(Color.ORANGE);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    //this.setContentPane();
	    
	    /*
	    textField = new JTextField();
	    textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    textField.setEditable(false);
	    textField.setColumns(10);
	    */
	    /*
	
		*/
	    ecran.setPreferredSize(new Dimension(225, 25));
	    numbers.setPreferredSize(new Dimension(150, 200));
	    bouton1.addActionListener((ActionListener)_ctrl);
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
	    calculette.add(ecran, BorderLayout.NORTH);
	    calculette.add(numbers, BorderLayout.CENTER);

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
		
		
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		System.out.println("il y a une MAJ de l'obj");
		
	}
}
