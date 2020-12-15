package modelLayer;

/**
 * An exception for no customer found
 * @author Miroslav
 *
 */
public class CustomerNotFoundException extends Exception
{
	public CustomerNotFoundException(String message)
	{
		super(message);
	}
}
