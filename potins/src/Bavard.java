import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class Bavard implements PapotageListener {
    private Concierge concierge;
    private String nom;
    private Boolean connecte;
    private ArrayList <Bavard> bavards;
    private ArrayList <String> messages;

    public Bavard(String nom, Concierge concierge){
        this.nom=nom;
        this.concierge=concierge;
        this.bavards=new ArrayList<Bavard>();
        this.messages=new ArrayList<String>();
        this.connecte=false;
    }

    public Concierge get_concierge(){
        return this.concierge;
    }

    public void transmettre_potin(PapotageEvent message_envoye){
        this.concierge.recevoir_potin(message_envoye);
    }
    
    public void recevoir_potin(PapotageEvent message_recu){
        if (this.connecte){
            bavards.add(message_recu.getEnvoyeur());
            messages.add( message_recu.print_message());
            System.out.println(message_recu.print_message());
        }
        // si le bavard ne veut pas être connecté on fait rien
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
            OnLineBavardEvent on= new OnLineBavardEvent(source, ActionEvent.ACTION_PERFORMED, "command",this);
            on.get_message();
        }
        else{
            System.out.println("Le bavard "+this.get_nom()+" n'est pas connecté au potin");
            Object source = new Object();
            OnLineBavardEvent of= new OnLineBavardEvent(source, ActionEvent.ACTION_PERFORMED, "command",this);
            of.get_message();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this.recevoir_potin();
        System.out.println("Le bavard affiche le message");
    }

    public String get_messages(){
        String res="";
        for(int i=0;i<bavards.size();i++){
            res+=bavards.get(i).get_nom() + " : ";
            res+=messages.get(i).toString()+ "; ";
        }
        return res;
    }

    public String get_messages_bavard(Bavard b){
        String res="";
        res+=b.get_nom() + " : ";
        for(int i=0;i<bavards.size();i++){
            if(b.equals(bavards.get(i))){
                res+=messages.get(i).toString()+ "; ";
            }
        }
        return res;
    }

}
