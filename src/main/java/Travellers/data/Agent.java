package Travellers.data;

import java.util.ArrayList;

/**
 * Created by bittstream on 12/9/2015.
 */
public class Agent {
	private String name;
	private String address;
	private String telephone;
	private String fax;
	private String email;
	private String website;
	private ArrayList<String> productsOffered = new ArrayList<String>();

	public void addProduct(String product) {
		this.productsOffered.add(product);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public ArrayList<String> getProductsOffered() {
		return productsOffered;
	}

	public void setProductsOffered(ArrayList<String> productsOffered) {
		this.productsOffered = productsOffered;
	}

	@Override
	public String toString() {
		return "Agent{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", telephone='" + telephone + '\'' +
				", fax='" + fax + '\'' +
				", email='" + email + '\'' +
				", website='" + website + '\'' +
				", productsOffered=" + productsOffered +
				'}';
	}
}

