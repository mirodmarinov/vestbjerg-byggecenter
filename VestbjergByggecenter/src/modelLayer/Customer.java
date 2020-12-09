package modelLayer;

/**
 * This class is part of the System developed for 
 * Vestbjerg Byggecenter. The class contains all the
 * knowledge needed about the customers, as well
 * as the methods needed to access and mutate
 * this information.
 */

public class Customer 
{
    private int phone; //phone number without country code
    private int discount; //based on the group
    private String name;
    private String address;
    private String group; 

    public Customer(int phone, int discount, String name, String address, String group)
    {
        this.phone = phone;
        this.discount = discount;
        this.name = name;
        this.address = address;
        this.group = group;
    }

    /**
     * The following methods are the getters
     * and setters for all the fields
     */
	public int getPhone() 
	{
		return phone;
	}

	public void setPhone(int phone) 
	{
		this.phone = phone;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public int getDiscount() 
	{
		return discount;
	}

	public void setDiscount(int discount) 
	{
		this.discount = discount;
	}

	public String getGroup() 
	{
		return group;
	}

	public void setGroup(String group) 
	{
		this.group = group;
	}
}
