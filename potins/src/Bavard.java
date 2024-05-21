public class Bavard implements PapotageListener {
    private Concierge concierge;
    private String nom;

    public Bavard(String nom, Concierge concierge){
        this.nom=nom;
        this.concierge=concierge;
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

    public void seConnecter(){
        BavardInterface bav = new BavardInterface(this);
    }
}