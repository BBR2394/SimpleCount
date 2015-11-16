import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller implements ActionListener {
	View view = null;
	Model model = null;
	public Controller()
	{
		view = new View(this);
		model = new Model(view);
		model.addObserver(view);
	}
	
	public void actionPerformed(ActionEvent act)
	{
		System.out.println("Ici !");
		model.addNum("1");
	}
	
}
