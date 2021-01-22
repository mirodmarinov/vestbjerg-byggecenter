package modelLayer;

import java.io.Serializable;

/**
 * This class is part of the System developed for 
 * Vestbjerg Byggecenter. The class contains all the
 * knowledge needed about the customers, as well
 * as the methods needed to access and mutate
 * this information.
 * It implement Serializable in order ot be able to save it to a file.
 */

public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * This method returns an Array of Strings
	 * containing the different information
	 * of the fields of a Customer Object.
	 * 
	 * @return String[] of info
	 */
	public String[] tableFill()
	{
		String[] data = new String[5];
		data[0] = name;
		data[1] = Integer.toString(phone);
		data[2] = address;
		data[3] = group;
		data[4] = Integer.toString(discount);
		return data;
	}
}
