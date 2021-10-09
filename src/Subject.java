import java.*;
import java.util.List;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:25 AM
 */
public interface Subject {

	public List<Observer> observers = null;

	/**
	 * 
	 * @param observer
	 */
	public void attach(Observer observer);

	/**
	 * 
	 * @param observer
	 */
	public void detach(Observer observer);

	public void notifyObservers();

}