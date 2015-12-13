import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.util.Observer;

public class Controller implements Observer{
	View _view = null;
	Model _model = null;
	private boolean _endCalc = true;
	private boolean _LastAction = false;
	
	
	public Controller()
	{
		_model = new Model();
		_view = new View(this);
		_model.addObserver((Observer)this);
	}
	
	public void sendNumberToModel(String nb)
	{
		if (_endCalc == true)
			_model.resetCalc();
		if (nb == "-x")
			_model.putInNegativ();
		else
			_model.addNum(nb);
		_LastAction = false;
		_endCalc = false;
	}
	
	public boolean sendOperator(String ope)
	{
		if (_endCalc == true && _LastAction == true)
		{
			_model.numberAns(ope);
			_endCalc = false;
			_LastAction = false;
		}
		if (ope.equals("AC"))
		{
			_endCalc = false;
			_model.resetCalc();
		}
		else if (_LastAction == false)
		{
			_endCalc = false;
			if (ope == "+")
				_model.calc("+");
			else if (ope == "-")
				_model.calc("-");
			else if (ope == "x")
				_model.calc("*");
			else if (ope == "/")
			{
				if (_model.calc("/") == false)
				{
					_model.error();
					_endCalc = true;
				}
			}
			else if (ope == "%")
			{
				if (_model.calc("%") == false)
				{
					_model.error();
					_endCalc = true;
				}
			}
			else if (ope == "=")
			{
				if (_endCalc == true)
					return true;
				if (_model.equal() == false)
					_model.error();
				_endCalc = true;
			}
			_LastAction = true;
			return true;
		}
		return true;
	}
	
	public void sendAdvancedCalc(String ope)
	{
		switch (ope)
		{
		case "x²":
			_model.advanceCalc("2");
			break;
		case "root":
			_model.advanceCalc("root");
			break;
		case "cos":
			_model.advanceCalc("cos");
			break;
		case "sin":
			_model.advanceCalc("sin");
			break;
		case "tan": 
			_model.advanceCalc("tan");
			break;
		case "ln":
			_model.advanceCalc("ln");
			break;
		case "e":
			_model.advanceCalc("e");
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
		Model _model = (Model) o;

		_view.setScreen(_model.getToEval());
	}
	
}
