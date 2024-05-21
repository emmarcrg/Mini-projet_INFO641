public class Bavard implements PapotageListener {
    private Concierge concierge;
    private String nom;
    private Boolean connecte;

    public Bavard(String nom, Concierge concierge){
        this.nom=nom;
        this.concierge=concierge;
        this.connecte=false;
    }

    public Concierge get_concierge(){
        return this.concierge;
    }

    public void transmettre_potin(PapotageEvent message_envoye){
        this.concierge.recevoir_potin(message_envoye);
    }
    
    public void recevoir_potin(PapotageEvent message_recu){
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
        }
        else{
            System.out.println("Le bavard "+this.get_nom()+" n'est pas connecté au potin");
        }
    }

}
