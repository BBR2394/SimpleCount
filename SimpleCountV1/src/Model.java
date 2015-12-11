import java.util.Observer;
import java.util.Observable;
import java.text.DecimalFormat;

public class Model extends Observable {
	private boolean 	dynCalc;
	private double 		res = 0;
	private double 		precRes = 0;
	private boolean		_comma;
	private String 		_toEval = " ";
	private boolean 	endCalc = true;
	private boolean 	_dynamicCalc = true;
	private String 		_lastOpe = "";
	private boolean		_negative = false;
	private DecimalFormat _format  = new DecimalFormat("0.########");
	/* a supprimer avant la fin du projet */
	void printData()
	{
		System.out.println("to eval et le resultata sont egaux a ");
		System.out.println(_toEval);
		System.out.println(res);
		System.out.println(precRes);
	}
	
	private void setEvaluator(String newEval)
	{
		String[] temp = newEval.split(".");
		//System.out.println(temp[0]);
		if (_lastOpe == "=")
		{
			//System.out.println("new Eval apres =");
			//System.out.println(newEval);
			//System.out.println(temp[0]);
			//System.out.println(temp[1]);
			_toEval = newEval;
			/*
			if (haveItDecimal())
				_toEval = newEval;
			else
				_toEval = temp[0];
				*/
		}
		else
			_toEval = newEval;
		
		//String format = decimalFormat.format(123456789.123);
		//System.out.println(format);
		
		printData();
		setChanged();
        notifyObservers();
	}
	
	private boolean haveItDecimal()
	{
		String temp = _toEval.substring(_toEval.lastIndexOf(".") + 1);
		int deci;
		
		deci = Integer.parseInt(temp);
		if (deci == 0)
			return false;
		else
			return true;
	}
	
	public Model()
	{
		dynCalc = true;
		_comma = false;
	}
	
	public boolean resetCalc()
	{
		res = 0;
		_lastOpe = "";
		_comma = false;
		_toEval = " ";
		_negative = false;
		setEvaluator(" ");
		return true;
	}
	
	
	public boolean calc(String ope)
	{
		if (res == 0)
		{
			System.out.println("un premier calc DANS CALC");
			res = Double.parseDouble(_toEval);
			_toEval += ope;
			_lastOpe = ope;
			_comma = false;
			setEvaluator(_toEval);
		}
		else if (res != 0 && _dynamicCalc == true)
		{
			
			System.out.println("dans le deuxieme plus DANS CALC" + _toEval);
			/*res += Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
			veuillez insérer un switch ici
			*/
			switch(_lastOpe)
			{
			case "+" : System.out.println("++ plus !! ++");
				res += Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
			break;
			case "-" :
				res -= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			case "*":
				res *= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			case "/":
				if (checkIsZero(Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1))) == true)
					return false;
				res /= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			case "%":
				if (checkIsZero(Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1))) == true)
					return false;
				res %= Double.parseDouble(_toEval.substring(_toEval.lastIndexOf(_lastOpe) + 1));
				break;
			default :  System.out.println("probleme avec le switch");
			break;
			}
			System.out.println("le resultat dans calc ");
			System.out.println(res);
			_comma = false;
			_lastOpe = ope;
			setEvaluator(_format.format(res) + ope);
		}
		endCalc = false;
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
	/*
	public boolean square()
	{
		System.out.println("dans carré");
		if (_lastOpe == "")
		{
			res = Double.parseDouble(_toEval);
			res *= res;
			precRes = res;
			System.out.println("je vais faire un carré");
			setEvaluator(_format.format(res));
			equal();
		}
		else
		{
			equal();
			res = Double.parseDouble(_toEval);
			res *= res;
			precRes = res;
			System.out.println("je vais faire un carré");
			setEvaluator(_format.format(res));
			equal();
		}
		return true;
	}
	
	public boolean squareRoot()
	{
		System.out.println("dans racine carré");
		if (_lastOpe == "")
		{
			res = Double.parseDouble(_toEval);
			res  = Math.sqrt(res);
			precRes = res;
			System.out.println("je vais faire une racine carré");
			setEvaluator(_format.format(res));
			equal();
		}
		else
		{
			equal();
			res = Double.parseDouble(_toEval);
			res  = Math.sqrt(res);
			precRes = res;
			System.out.println("je vais faire un carré");
			setEvaluator(_format.format(res));
			equal();
		}
		return true;
	}
	*/
	private boolean makeAdvancedCalc(String ope)
	{
		switch (ope)
		{
		case "2":
			res *= res;
			break;
		case "root":
			res  = Math.sqrt(res);
			break;
		case "cos":
			res = Math.cos(res);
			break;
		case "sin":
			res = Math.sin(res);
			break;
		case "tan": 
			res = Math.tan(res);
			break;
		case "ln":
			res = Math.log(res);
			break;
		case "e":
			res = Math.exp(res);
			break;
		default	:
			return false;
		}
		return true;
	}
	
	public boolean advanceCalc(String ope)
	{
		System.out.println("dans advance calc");
		if (_toEval == " ")
			return true;
		if (_lastOpe == "")
		{
			res = Double.parseDouble(_toEval);
			makeAdvancedCalc(ope);
			precRes = res;
			System.out.println("je vais faire une racine carré");
			setEvaluator(_format.format(res));
			equal();
		}
		else
		{
			equal();
			res = Double.parseDouble(_toEval);
			makeAdvancedCalc(ope);
			precRes = res;
			System.out.println("je vais faire un carré");
			setEvaluator(_format.format(res));
			equal();
		}
		return true;
	}
	
	public boolean equal()
	{
		if (endCalc == true)
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
		//printData();
		if (rtr != false)
		{
			setEvaluator(_format.format(res));
			precRes = res;
		}
		else
			return false;
		endCalc = true;
		printData();
		return true;	
	}
	
	public boolean numberAns(String ope)
	{
		System.out.println("dans number Ans");
		if (endCalc == true)
		{
			resetCalc();
			this.addNum(Double.toString(precRes));
			printData();
			System.out.println("Ce putain de _toEval  " + _toEval);
			return true;
		}
		else
			return false;
	}
	
	public boolean putInNegativ()
	{
		String subString;
		
		System.out.println("dans met en negatif ");
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
				System.out.println("ICICI  " + subString);
				
			}
			else if (_lastOpe == "+")
			{
				subString  = _toEval.substring(0, _toEval.lastIndexOf("+"));
				_lastOpe = "-";
				subString += "-";
				subString += _toEval.substring(_toEval.lastIndexOf("+")+1);
				setEvaluator(subString);
				System.out.println("ICICI  " + subString);
			}
			else
			{
				subString  = _toEval.substring(0, _toEval.lastIndexOf(_lastOpe)+1);
				if (_negative == false)
				{
					subString += "-";
					System.out.println("ICICI  " + subString);
					subString += _toEval.substring(_toEval.lastIndexOf(_lastOpe)+1);
					_negative = true;
				}
				else
				{
					subString += _toEval.substring(_toEval.lastIndexOf(_lastOpe)+2);
					_negative = false;
				}
				setEvaluator(subString);
				System.out.println("ICICI  " + subString);
			}
		}
		return true;
	}
	
	public boolean addNum(String num)
	{
		System.out.println("au debut du add num   " + num + _comma + "|" + _toEval + "|");
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
		System.out.println("a la fin du addnum  "+_toEval);
		System.out.println(num);
		return true;
	}
	
	public String getToEval()
	{
		return _toEval;
	}
	
}
