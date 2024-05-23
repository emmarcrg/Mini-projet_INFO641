import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class Concierge {
    private ArrayList<Bavard> liste_bavards;
    private String nom;
    private ArrayList <Bavard> bavards;
    private ArrayList <String> messages;

    public Concierge(String nom){
        
        this.nom=nom;
        this.bavards=new ArrayList<Bavard>();
        this.messages=new ArrayList<String>();
        liste_bavards= new ArrayList<>();
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
        bavards.add(potin.getEnvoyeur());
        messages.add(potin.print_message());
        transmettre_potin(potin);
    }

    public void transmettre_potin(PapotageEvent potin) {
        for (Bavard bavard : liste_bavards) {
            // chaque bavard de la liste reçoit le potin
            //System.out.println("Message transmi : "+potin.print_message());
            bavard.recevoir_potin(potin);
        }
    }

    public String get_messages(){
        String res="";
        for(int i=0;i<bavards.size();i++){
            res+=bavards.get(i).get_nom() + " : ";
            res+=messages.get(i).toString()+ "; ";
        }
        return res;
    }

    public String get_nom(){
        return this.nom;
    }

    public ArrayList<Bavard> get_liste_bavards(){
        return this.liste_bavards;
    }

    public String[] get_nom_bavards(){
        List<String> noms_bavards=new ArrayList<>();
        for (Bavard bavard : liste_bavards){
            noms_bavards.add(bavard.get_nom());
        }
        String[] bavards=noms_bavards.toArray(new String[0]);
        return bavards;
    }

}
