import java.awt.event.ActionEvent;

public class PapotageEvent extends ActionEvent {
    private String sujet;
    private String corps;

    public PapotageEvent(Object source, int id, String command, String sj, String cp){
        super(source, id, command);
        this.sujet=sj;
        this.corps=cp;
    }
    public PapotageEvent(Object source, int id, String command){
        super(source, id, command);
    }

    public void setMessage (String sj, String cp){
        this.sujet=sj;
        this.corps=cp;
    }

    public String print_message (){
        return "Message reçu : sujet "+ this.sujet+" - contenu : "+this.corps;
    }
}

