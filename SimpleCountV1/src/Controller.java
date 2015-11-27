import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller {
	View view = null;
	Model model = null;
	private boolean _LastAction = false;
	public Controller()
	{
		model = new Model(view);
		view = new View(this, model);
		model = new Model(view);
		model.addObserver(view);
	}
	
	public void sendNumberToModel(String nb)
	{
		model.addNum(nb);
		_LastAction = false;
	}
	
	public void sendOperator(String ope)
	{
		System.out.println("	-> je vais envoyer un ope a model");
		System.out.println(ope);
		if (_LastAction == false)
		{
			if (ope == "+")
				model.add();
			else if (ope == "-")
				model.sub();
			else if (ope == "x")
				model.mult();
			else if (ope == "/")
				model.div();
			else if (ope == "AC")
			{
				model.resetCalc();
				System.out.println("j'ai fait");
			}
			else if (ope == "=")
				model.equal();
			_LastAction = true;
		}
	}
	
	/*
	public class BoutonListener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) {
	    	model.addNum("1");        
	    }
	  }
	*/
}
