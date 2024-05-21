import java.awt.event.ActionEvent;
import java.util.HashMap;


public class Bavard implements PapotageListener {
    private Concierge concierge;
    private String nom;
    private HashMap <Bavard, String> messages;

    public Bavard(String nom, Concierge concierge){
        this.nom=nom;
        this.concierge=concierge;
        messages=new HashMap<Bavard, String>();
    }

    public Concierge get_concierge(){
        return this.concierge;
    }

    public void transmettre_potin(PapotageEvent message_envoye){
        this.concierge.recevoir_potin(message_envoye);
    }
    
    public void recevoir_potin(PapotageEvent message_recu){
        messages.put( message_recu.getEnvoyeur(), message_recu.print_message());
        System.out.println(message_recu.print_message());
    }

    public String get_nom(){
        return this.nom;
    }

    public void seConnecter(){
        BavardInterface bav = new BavardInterface(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this.recevoir_potin();
        System.out.println("Le bavard affiche le message");
    }

    public HashMap<Bavard,String> get_messages(){
        return this.messages;
    }
}