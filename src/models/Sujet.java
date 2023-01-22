package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Sujet {

    private List<Observateur> ListObservateurs = new ArrayList<>();



    public List<Observateur> getListObservateurs(){
        return Collections.unmodifiableList(ListObservateurs);
    }
    public void ajouterObservateur(Observateur observateur){
        ListObservateurs.add(observateur);
    }
    public void enleverObservateur(Observateur observateur){
        ListObservateurs.remove(observateur);
    }
    public void notifier(){
        for (Observateur observateur : ListObservateurs){
            observateur.update();
        }
    }

}
