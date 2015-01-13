package com.example.newapplication.data;

import android.os.Parcel;
import android.os.Parcelable;
// modele de json
public class Resto implements Parcelable {
	private String id;
	private String nom;
	private String adresse;
	private String complementAdresse;
	private String codePostal;
	private String ville;
	private String latitude;
	private String longitude;
	private String accesHandicape;
	private String Telephone;
	private String parking;
	private String terrasse;
	private String espaceEnfant;
	private String photo;

	public Resto() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getComplementAdresse() {
		return complementAdresse;
	}

	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAccesHandicape() {
		return accesHandicape;
	}

	public void setAccesHandicape(String accesHandicape) {
		this.accesHandicape = accesHandicape;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getTerrasse() {
		return terrasse;
	}

	public void setTerrasse(String terrasse) {
		this.terrasse = terrasse;
	}

	public String getEspaceEnfant() {
		return espaceEnfant;
	}

	public void setEspaceEnfant(String espaceEnfant) {
		this.espaceEnfant = espaceEnfant;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(nom);
		dest.writeString(adresse);
		dest.writeString(complementAdresse);
		dest.writeString(codePostal);
		dest.writeString(ville);
		dest.writeString(latitude);
		dest.writeString(longitude);
		dest.writeString(accesHandicape);
		dest.writeString(Telephone);
		dest.writeString(parking);
		dest.writeString(terrasse);
		dest.writeString(espaceEnfant);
		dest.writeString(photo);

	}
}
