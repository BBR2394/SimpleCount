import java.util.Observer;
import java.util.Observable;

public class Model extends Observable {
	private boolean dynCalc;
	private double res = 0;
	private double precRes = 0;
	private boolean _comma;
	private String toEval = " ";
	private boolean firstcalc = true;

	private boolean _dynamicCalc = true;
	private String _lastOpe = "";
	
	/* a supprimer avant la fin du projet */
	void printData()
	{
		System.out.println("to eval et le resultata sont egaux a ");
		System.out.println(toEval);
		System.out.println(res);
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
			toEval = newEval;
			/*
			if (haveItDecimal())
				toEval = newEval;
			else
				toEval = temp[0];
				*/
		}
		else
			toEval = newEval;
		printData();
		setChanged();
        notifyObservers();
	}
	
	private boolean haveItDecimal()
	{
		String temp = toEval.substring(toEval.lastIndexOf(".") + 1);
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
		toEval = " ";
		setEvaluator(" ");
		return true;
	}
	
	public boolean add()
	{
		if (res == 0)
		{
			System.out.println("un premier plus");
			res = Double.parseDouble(toEval);
			toEval += "+";
			_lastOpe = "+";
			_comma = false;
			setEvaluator(toEval);
		}
		else if (res != 0 && _dynamicCalc == true)
		{
			System.out.println("dans le deuxieme plus " + toEval);
			res += Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1));
			System.out.println("le resultat");
			System.out.println(res);
			_comma = false;
			toEval = Double.toString(res);
			//res = 0;
			toEval += "+";
			_lastOpe = "+";
			setEvaluator(toEval);
		}
		return true;
	}
	
	public boolean sub()
	{
		if (res == 0)
		{
			res = Double.parseDouble(toEval);
			toEval += "-";
			_lastOpe = "-";
			_comma = false;
			setEvaluator(toEval);
		}
		else if (res != 0 && _dynamicCalc == true)
		{
			res -= Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1));
			System.out.println("le resultat");
			System.out.println(res);
			_comma = false;
			toEval = Double.toString(res);
			//res = 0;
			toEval += "-";
			_lastOpe = "-";
			setEvaluator(toEval);
		}
		return true;
	}
	
	public boolean mult()
	{
		if (res == 0)
		{
			res = Double.parseDouble(toEval);
			toEval += "*";
			_lastOpe = "*";
			_comma = false;
			setEvaluator(toEval);
		}
		else if (res != 0 && _dynamicCalc == true)
		{
			res *= Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1));
			System.out.println("le resultat");
			System.out.println(res);
			_comma = false;
			toEval = Double.toString(res);
			//res = 0;
			toEval += "*";
			_lastOpe = "*";
			setEvaluator(toEval);
		}
		return true;
	}
	
	private boolean checkIsZero(Double nb)
	{
		if (nb == 0)
			return true;
		else 
			return false;
	}
	
	public boolean div()
	{
		if (res == 0)
		{
			res = Double.parseDouble(toEval);
			toEval += "/";
			_lastOpe = "/";
			_comma = false;
			setEvaluator(toEval);
		}
		else if (res != 0 && _dynamicCalc == true)
		{
			if (checkIsZero(Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1))) == true)
				return false;
			res /= Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1));
			System.out.println("le resultat");
			System.out.println(res);
			_comma = false;
			toEval = Double.toString(res);
			//res = 0;
			toEval += "/";
			_lastOpe = "/";
			setEvaluator(toEval);
		}
		return true;
	}
	
	public boolean modulo()
	{
		if (res == 0)
		{
			res = Double.parseDouble(toEval);
			toEval += "%";
			_lastOpe = "%";
			_comma = false;
			setEvaluator(toEval);
		}
		else if (res != 0 && _dynamicCalc == true)
		{
			if (checkIsZero(Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1))) == true)
				return false;
			res %= Double.parseDouble(toEval.substring(toEval.lastIndexOf(_lastOpe) + 1));
			System.out.println("le resultat");
			System.out.println(res);
			_comma = false;
			toEval = Double.toString(res);
			//res = 0;
			toEval += "%";
			_lastOpe = "%";
			setEvaluator(toEval);
		}
		return true;
	}
	
	public boolean error()
	{
		setEvaluator("Err.");
		_lastOpe = "=";
		return true;	
	}
	
	public boolean square()
	{
		if (res != 0 && _lastOpe == "")
		{
			res = Double.parseDouble(toEval);
			res *= res;
			setEvaluator(Double.toString(res));
			_lastOpe = "=";
		}
		return true;
	}
	
	public boolean equal()
	{
		boolean rtr = true;
		if (_lastOpe == "+")
			this.add();
		else if (_lastOpe == "-")
			this.sub();
		else if (_lastOpe == "*")
			this.mult();
		else if (_lastOpe == "/")
			rtr = this.div();
		else if (_lastOpe == "%")
			rtr = this.modulo();
		_lastOpe = "=";
		printData();
		if (rtr != false)
		{
			setEvaluator(Double.toString(res));
			precRes = res;
		}
		else
			return false;
		firstcalc = false;
		return true;	
	}
	
	public boolean recycle(String ope)
	{
		if (firstcalc != true && precRes == res)
		{
			_comma = false;
			//_lastOpe = ope;
			res = 0;
			//toEval  = Double.toString(precRes);
			setEvaluator(Double.toString(precRes));
			printData();
			System.out.println("Ce putain de toEval  " + toEval);
			return true;
		}
		else
			return false;
	}
	
	public boolean addNum(String num)
	{
		if (toEval == " " && (num == "0" || num == ","))
			return true;
		else if (toEval == " ")
		{
			toEval = num;
			precRes = 0;
		}
		else if (num == "," && _comma != true)
		{
			_comma = true;
			toEval += ".";
		}
		else if (num != ",")
			toEval += num;
			/*if (toEval == "&")
				toEval = num;
			else	
				toEval += num;*/
		setEvaluator(toEval);
		System.out.println(toEval);
		System.out.println(num);
		return true;
	}
	
	public String getToEval()
	{
		return toEval;
	}
	
}
