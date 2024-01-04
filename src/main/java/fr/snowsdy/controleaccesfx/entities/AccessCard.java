package fr.snowsdy.controleaccesfx.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class AccessCard implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String serialNumber;

    public AccessCard() {
    }

    public AccessCard(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return this.id + " : " + this.serialNumber;
    }
}
