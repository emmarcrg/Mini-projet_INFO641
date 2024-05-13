import java.util.ArrayList;

public class Batiment {
    private String nom_batiment;
    private Concierge concierge;
    private ArrayList<Bavard> habitants;

    public Batiment(String bat, Concierge conc){
        this.nom_batiment=bat;
        this.concierge=conc;
        this.habitants=new ArrayList<>();
    }

    public String get_nom_batiment(){
        return this.nom_batiment;
    }

    public Concierge get_concierge(){
        return this.concierge;
    }

    public void souscription_potin(){
        for (Bavard habitant : habitants){
            this.concierge.ajouter_bavard(habitant);
        }
    }

    public void ajouter_habitant(Bavard nouvel_habitant){
        this.habitants.add(nouvel_habitant);
    }

    public void supprimer_habitant(Bavard ancier_habitant){
        this.habitants.remove(ancier_habitant);
    }
    

    

}
