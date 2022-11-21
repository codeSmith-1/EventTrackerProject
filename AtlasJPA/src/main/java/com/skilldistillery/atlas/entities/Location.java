package com.skilldistillery.atlas.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String country;
	private String city;
	private String image;
	private double latitude;
	private double longitude;
	@Column(name="arrival_date")
	private LocalDate arrivalDate;
	@Column(name="departure_date")
	private LocalDate departureDate;
	private String note;
	
	@JsonIgnore
	@OneToMany(mappedBy = "location")
	private List<Visit> visits;
	
	
	public Location() {	}
	
	
	



	public String getNote() {
		return note;
	}






	public void setNote(String note) {
		this.note = note;
	}






	public LocalDate getArrivalDate() {
		return arrivalDate;
	}






	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}






	public LocalDate getDepartureDate() {
		return departureDate;
	}






	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}






	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	@Override
	public String toString() {
		return "Location [id=" + id + ", country=" + country + ", city=" + city + ", image=" + image + ", latitude="
				+ latitude + ", longitude=" + longitude + ", arrivalDate=" + arrivalDate + ", departureDate="
				+ departureDate + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return id == other.id;
	}
	
	
	
	
}
