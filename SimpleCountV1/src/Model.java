import java.util.Observer;
import java.util.Observable;
import java.text.DecimalFormat;

public class Model extends Observable {
	private double 		_res = 0;
	private double 		_presRes = 0;
	private boolean		_comma = false;
	private String 		_toEval = " ";
	private boolean 	_endCalc = true;
	private boolean 	_dynamicCalc = true;
	private String 		_lastOpe = "";
	private boolean		_negative = false;
	private DecimalFormat _format  = new DecimalFormat("0.########");
	
	private void setEvaluator(String newEval)
	{
		_toEval = newEval;
		setChanged();
        notifyObservers();
	}
	
	public Model()
	{
	}
	
	public boolean resetCalc()
	{
		_res = 0;
		_lastOpe = "";
		_comma = false;
		_toEval = " ";
		_negative = false;
		setEvaluator(" ");
		return true;
	}
	
	
	public boolean calc(String ope)
	{
		if (_res == 0)
		{
			_res = Double.parseDouble(_toEval);
			_toEval += ope;
			_lastOpe = ope;
			_comma = false;
			setEvaluator(_toEval);
		}
		else if (_res != 0 && _dynamicCalc == true)
		{
			switch(_lastOpe)
			{
			case "+" : 
				_res += Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
			break;
			case "-" :
				_res -= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			case "*":
				_res *= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			case "/":
				if (checkIsZero(Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1))) == true)
					return false;
				_res /= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			case "%":
				if (checkIsZero(Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1))) == true)
					return false;
				_res %= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			default :  System.out.println("probleme avec le switch");
			break;
			}
			_comma = false;
			_lastOpe = ope;
			setEvaluator(_format.format(_res) + ope);
		}
		_endCalc = false;
		return true;
	}
	
	private boolean checkIsZero(Double nb)
	{
		if (nb == 0)
			return true;
		else 
			return false;
	}

	public boolean error()
	{
		setEvaluator("Err.");
		_lastOpe = "=";
		return true;	
	}
	
	private boolean makeAdvancedCalc(String ope)
	{
		switch (ope)
		{
		case "2":
			_res *= _res;
			break;
		case "root":
			_res  = Math.sqrt(_res);
			break;
		case "cos":
			_res = Math.cos(_res);
			break;
		case "sin":
			_res = Math.sin(_res);
			break;
		case "tan": 
			_res = Math.tan(_res);
			break;
		case "ln":
			_res = Math.log(_res);
			break;
		case "e":
			_res = Math.exp(_res);
			break;
		default	:
			return false;
		}
		return true;
	}
	
	public boolean advanceCalc(String ope)
	{
		if (_toEval == " ")
			return true;
		if (_lastOpe == "")
		{
			_res = Double.parseDouble(_toEval);
			makeAdvancedCalc(ope);
			_presRes = _res;
			setEvaluator(_format.format(_res));
			equal();
		}
		else
		{
			equal();
			_res = Double.parseDouble(_toEval);
			makeAdvancedCalc(ope);
			_presRes = _res;
			setEvaluator(_format.format(_res));
			equal();
		}
		return true;
	}
	
	public boolean equal()
	{
		if (_endCalc == true)
			return true;
		boolean rtr = true;
		if (_lastOpe == "+")
			this.calc("+");
		else if (_lastOpe == "-")
			this.calc("-");
		else if (_lastOpe == "*")
			this.calc("*");
		else if (_lastOpe == "/")
			rtr = this.calc("/");
		else if (_lastOpe == "%")
			rtr = this.calc("%");
		_lastOpe = "=";
		if (rtr != false)
		{
			setEvaluator(_format.format(_res));
			_presRes = _res;
		}
		else
			return false;
		_endCalc = true;
		return true;	
	}
	
	public boolean numberAns(String ope)
	{
		if (_endCalc == true)
		{
			resetCalc();
			this.addNum(Double.toString(_presRes));
			return true;
		}
		else
			return false;
	}
	
	public boolean putInNegativ()
	{
		String subString;
		
		if (_lastOpe == "")
		{
			if(_negative == true && _lastOpe == "")
			{
				_toEval = _toEval.substring(_toEval.lastIndexOf("-")+1);
				_negative = false;
			}
			else
			{
				_toEval = "-" + _toEval;
				_negative = true;
			}
			setEvaluator(_toEval);
		}
		else
		{
			if (_lastOpe == "-")
			{
				subString  = _toEval.substring(0, _toEval.lastIndexOf("-"));
				_lastOpe = "+";
				subString += "+";
				subString += _toEval.substring(_toEval.lastIndexOf("-")+1);
				setEvaluator(subString);				
			}
			else if (_lastOpe == "+")
			{
				subString  = _toEval.substring(0, _toEval.lastIndexOf("+"));
				_lastOpe = "-";
				subString += "-";
				subString += _toEval.substring(_toEval.lastIndexOf("+")+1);
				setEvaluator(subString);
			}
			else
			{
				subString  = _toEval.substring(0, _toEval.lastIndexOf(_lastOpe)+1);
				if (_negative == false)
				{
					subString += "-";
					subString += _toEval.substring(_toEval.lastIndexOf(_lastOpe)+1);
					_negative = true;
				}
				else
				{
					subString += _toEval.substring(_toEval.lastIndexOf(_lastOpe)+2);
					_negative = false;
				}
				setEvaluator(subString);
			}
		}
		return true;
	}
	
	public boolean addNum(String num)
	{
		if (_toEval == "0" && num == "0")
			return true;
		else if (_toEval == " " && num == ",")
			return true;
		else if (num == "," && _comma == false)
		{
			_comma = true;
			_toEval += ".";
		}
		else if (_toEval == " ")
			_toEval = num;
		else if (num != ",")
			_toEval += num;
		if (num == "-x")
			putInNegativ();
		setEvaluator(_toEval);
		return true;
	}
	
	public String getToEval()
	{
		return _toEval;
	}
	
}
