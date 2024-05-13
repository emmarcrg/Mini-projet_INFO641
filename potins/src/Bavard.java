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

    public void transmettre_potins(String sujet, String corps){
        message_envoye.setMessage(sujet,corps);
        this.concierge.recevoir_potins(this.message_envoye);
    }

    public String recevoir_potins(){
        this.concierge.transmettre_potins(this.message_recu);
        return this.message_recu.print_message();
    }

}
