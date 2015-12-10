import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller {
	View view = null;
	Model model = null;
	private boolean _endCalc = false;
	private boolean _LastAction = false;
	public Controller()
	{
		model = new Model();
		view = new View(this);
		model.addObserver(view);
	}
	
	public void sendNumberToModel(String nb)
	{
		if (_endCalc == true)
			model.resetCalc();
		model.addNum(nb);
		_LastAction = false;
	}
	
	public boolean sendOperator(String ope)
	{
		System.out.println("	-> je vais envoyer un ope a model");
		System.out.println(ope);
		if (model.recycle(ope) == true)
			_LastAction = false;
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
				model.add();
			else if (ope == "-")
				model.sub();
			else if (ope == "x")
				model.mult();
			else if (ope == "/")
			{
				if (model.div() == false)
				{
					model.error();
					System.out.println("division error");
					_endCalc = true;
				}
			}
			else if (ope == "%")
			{
				if (model.modulo() == false)
				{
					model.error();
					System.out.println("modulo error");
					_endCalc = true;
				}
			}
			else if (ope == "=")
			{
				if (model.equal() == false)
					model.error();
				_endCalc = true;
				return true;
			}
			_LastAction = true;
			return true;
		}
		return true;
	}
	
	public void sendAdvancedCalc(String ope)
	{
		System.out.println("	-> advanced calc !");
		model.square();
	}
	
}
