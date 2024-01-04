package fr.snowsdy.controleaccesfx.entities;

import jakarta.persistence.*;

import java.io.Serializable;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Attribution implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private AccessCard accessCard;

    public Attribution() {
    }

    public Attribution(User user, AccessCard accessCard) {
        this.user = user;
        this.accessCard = accessCard;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    @Override
    public String toString() {
        return "Badge : " + this.accessCard.getSerialNumber() + " affected to " + this.user.toString();
    }
}
