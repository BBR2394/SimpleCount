import java.util.Observer;
import java.util.Observable;

public class Model extends Observable {
	private boolean dynCalc;
	private double res;
	private String toEval = "&";
	private View _view = null;
	
	private void setEvaluator(String newEval)
	{
		toEval = newEval;
	}
	
	public Model(View v)
	{
		_view = v;
		dynCalc = true;
	}
	
	public boolean add()
	{
		return true;
	}
	
	public boolean sub()
	{
		return true;
	}
	
	public void addNum(String num)
	{
		if (toEval == "&")
		{
			toEval = num;
		}
		else
			toEval += num;
		setEvaluator(toEval);
		System.out.println(toEval);
		System.out.println(num);
	}
	
}
