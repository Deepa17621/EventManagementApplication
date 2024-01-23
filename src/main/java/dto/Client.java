package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Client 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	private String clientName;
	private String email;
	private String password;
	private long clientContact;
	private String clientAddress;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientEvent> clientEvent;
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public long getClientContact() {
		return clientContact;
	}
	public void setClientContact(long clientContact) {
		this.clientContact = clientContact;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public List<ClientEvent> getClientEvent() {
		return clientEvent;
	}
	public void setClientEvent(List<ClientEvent> clientEvent) {
		this.clientEvent = clientEvent;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", email=" + email + ", password="
				+ password + ", clientContact=" + clientContact + ", clientAddress=" + clientAddress + ", clientEvent="
				+ clientEvent + "]";
	}
	
	

}
