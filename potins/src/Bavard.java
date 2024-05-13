public class Bavard implements PapotageListener {
    private Concierge concierge;
    private PapotageEvent message_envoye;
    private PapotageEvent message_recu;

    public Bavard(Concierge concierge){
        this.concierge=concierge;
        this.message_envoye=new PapotageEvent();
        this.message_recu=new PapotageEvent();
    }

    public Concierge get_concierge(){
        return this.concierge;
    }

    public void transmettre_potin(PapotageEvent message_envoye){
        this.concierge.recevoir_potin(this.message_envoye);
    }

    public String recevoir_potin(PapotageEvent message_recu){
        return message_recu.print_message();
    }

}
