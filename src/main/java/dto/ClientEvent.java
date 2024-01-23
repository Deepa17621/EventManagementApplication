package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientEvent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientEventId;
	private int noOfPeople;
	private EventType eventType;
	private String startDate;
	private int noOfDays;
	private double totalCost;
	private String location;
	@ManyToOne
	private Client client;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientService> clientService;
	
	public int getClientEventId() {
		return clientEventId;
	}
	public void setClientEventId(int clientEventId) {
		this.clientEventId = clientEventId;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<ClientService> getClientService() {
		return clientService;
	}
	public void setClientService(List<ClientService> clientService) {
		this.clientService = clientService;
	}
	public int getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "ClientEvent [noOfPeople=" + noOfPeople + ", eventType=" + eventType + ", startDate=" + startDate
				+ ", noOfDays=" + noOfDays + ", totalCost=" + totalCost + ", location=" + location + "]";
	}
	
}
