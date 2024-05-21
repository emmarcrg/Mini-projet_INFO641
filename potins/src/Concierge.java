import java.util.ArrayList;
import java.util.HashMap;


public class Concierge {
    private ArrayList<Bavard> liste_bavards;
    private String nom;
    private HashMap <Bavard, String> messages;

    public Concierge(String nom){
        
        this.nom=nom;
        messages=new HashMap<Bavard, String>();
    }

    // Gestion des bavards
    public void ajouter_bavard(Bavard nouveau_bavard){
        this.liste_bavards.add(nouveau_bavard);
    }

    public void suprimer_bavard(Bavard ancier_bavard){
        this.liste_bavards.remove(ancier_bavard);
    }
    
    // Gestion de la reception des messages
    public void recevoir_potin(PapotageEvent potin) {
        //System.out.println("Message reçu : "+potin.print_message());
        messages.put(potin.getEnvoyeur(), potin.print_message());
        transmettre_potin(potin);
    }

    public void transmettre_potin(PapotageEvent potin) {
        for (Bavard bavard : liste_bavards) {
            // chaque bavard de la liste reçoit le potin
            //System.out.println("Message transmi : "+potin.print_message());
            bavard.recevoir_potin(potin);
        }
    }

    public HashMap<Bavard,String> get_messages(){
        return this.messages;
    }

    public String get_nom(){
        return this.nom;
    }
}
