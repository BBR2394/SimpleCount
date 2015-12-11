import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.util.Observer;

public class Controller implements Observer {
	View view = null;
	Model model = null;
	private boolean _endCalc = true;
	private boolean _LastAction = false;
	
	/*
	 * Ce quil reste a faire :
	 * 	- la precision et les zeros apres la virgule
	 * 	- le MR MC M+ M- ((mouai pas tres envie))
	 * 	- positif -> negatif
	 * 	- l'inverse ?
	 * 	-> la calculatrice scientifique
	 */
	
	public Controller()
	{
		model = new Model();
		view = new View(this);
		model.addObserver((Observer)this);
	}
	
	public void sendNumberToModel(String nb)
	{
		if (_endCalc == true)
			model.resetCalc();
		if (nb == "-x")
			model.putInNegativ();
		else
			model.addNum(nb);
		_LastAction = false;
		_endCalc = false;
	}
	
	public boolean sendOperator(String ope)
	{
		System.out.println("	-> je vais envoyer un ope a model");
		System.out.println(ope);
		if (_endCalc == true && _LastAction == true)
		{
			model.numberAns(ope);
			_endCalc = false;
			_LastAction = false;
		}
		if (ope.equals("AC"))
		{
			_endCalc = false;
			model.resetCalc();
			System.out.println("j'ai reinit");
		}
		else if (_LastAction == false)
		{
			System.out.println(" -> un operateur");
			_endCalc = false;
			if (ope == "+")
				model.calc("+");
			else if (ope == "-")
				model.calc("-");
			else if (ope == "x")
				model.calc("*");
			else if (ope == "/")
			{
				if (model.calc("/") == false)
				{
					model.error();
					System.out.println("division error");
					_endCalc = true;
				}
			}
			else if (ope == "%")
			{
				if (model.calc("%") == false)
				{
					model.error();
					System.out.println("modulo error");
					_endCalc = true;
				}
			}
			else if (ope == "=")
			{
				if (_endCalc == true)
					return true;
				if (model.equal() == false)
					model.error();
				_endCalc = true;
			}
			_LastAction = true;
			return true;
		}
		return true;
	}
	
	public void sendAdvancedCalc(String ope)
	{
		System.out.println("	-> advanced calc !");
		switch (ope)
		{
		case "x²":
			model.advanceCalc("2");
			break;
		case "root":
			model.advanceCalc("root");
			break;
		case "cos":
			model.advanceCalc("cos");
			break;
		case "sin":
			model.advanceCalc("sin");
			break;
		case "tan": 
			model.advanceCalc("tan");
			break;
		case "ln":
			model.advanceCalc("ln");
			break;
		case "e":
			model.advanceCalc("e");
			break;
		default	:
			break;
		}
		_endCalc = true;
		_LastAction = true;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		Model model = (Model) o;
		
		//weatherUpdate = (ObservableExample) observable;
		//System.out.println("Weather Report Live. Its "+weatherUpdate.getWeather());
		view.setScreen(model.getToEval());
		System.out.println("il y a une MAJ de l'obj");
	}
	
}
