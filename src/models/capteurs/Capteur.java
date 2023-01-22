package models.capteurs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Capteur {
    private static int idCourantIncremente = 0;
    private int id;
    private StringProperty nom = new SimpleStringProperty();





    public Capteur(String nom){
        this.nom.set(nom);
        idCourantIncremente += 1;
        id = idCourantIncremente;
    }
    public StringProperty nomProperty(){
        return nom;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getId() {
        return id;
    }
}
