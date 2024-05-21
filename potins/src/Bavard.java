import java.awt.event.ActionEvent;
import java.util.HashMap;


public class Bavard implements PapotageListener {
    private Concierge concierge;
    private String nom;
    private Boolean connecte;
    private HashMap <Bavard, String> messages;

    public Bavard(String nom, Concierge concierge){
        this.nom=nom;
        this.concierge=concierge;
        messages=new HashMap<Bavard, String>();
        this.connecte=false;
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

    public Boolean get_connection(){
        return this.connecte;
    }

    public void set_connection(Boolean etat_connection){
        this.connecte=etat_connection;
        if (etat_connection){
            System.out.println("Le bavard "+this.get_nom()+" est connecté au potin");
            Object source = new Object();
            OnLineBavardEvent ob= new OnLineBavardEvent(source, ActionEvent.ACTION_PERFORMED, "command",this);
            ob.get_message();
        }
        else{
            System.out.println("Le bavard "+this.get_nom()+" n'est pas connecté au potin");
        }
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
