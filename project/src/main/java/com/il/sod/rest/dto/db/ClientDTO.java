package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class ClientDTO {
	private int idClient;
	private String email;
	private String lastName;
	private String name;
	private String password;
	private String twitter;
	private List<Integer> orders;
	// key value  
	// key = id 
	// value = map {"name"}
//	private List<AccessKeyDTO> accessKeys;
	private List<AddressDTO> addresses;
	private List<PhoneNumberDTO> phoneNumbers;
	
	private Date created;
	private Date updated;
	
	private List<ClientPaymentInfoDTO> clientPaymentInfos;
	
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public List<Integer> getOrders() {
		return orders;
	}
	public void setOrders(List<Integer> orders) {
		this.orders = orders;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public List<PhoneNumberDTO> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumberDTO> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<ClientPaymentInfoDTO> getClientPaymentInfos() {
		return clientPaymentInfos;
	}
	public void setClientPaymentInfos(List<ClientPaymentInfoDTO> clientPaymentInfos) {
		this.clientPaymentInfos = clientPaymentInfos;
	}
}
