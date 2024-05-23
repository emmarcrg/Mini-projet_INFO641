import java.awt.event.ActionEvent;

public class PapotageEvent extends ActionEvent {
    private String sujet;
    private String corps;
    private String theme;
    private Bavard envoyeur;

    public PapotageEvent(Object source, int id, String command, String sj, String cp, String theme,  Bavard envoyeur){
        super(source, id, command);
        this.sujet=sj;
        this.corps=cp;
        this.theme=theme;
        this.envoyeur=envoyeur;
    }
    public PapotageEvent(Object source, int id, String command){
        super(source, id, command);
    }

    public void setMessage (String sj, String cp, String theme){
        this.sujet=sj;
        this.corps=cp;
        this.theme=theme;
    }

    public String print_message (){
        return "thème : "+ this.theme + "sujet : "+ this.sujet+" - contenu : "+this.corps;
    }

    public String affichage_simple(){
        return this.theme+ " : "+ this.sujet + " - " + this.corps;
    }

    public String affichage_message(){
        return "thème : "+ this.theme+ "sujet : "+this.sujet+" - contenu : "+this.corps;
    }

    public Bavard getEnvoyeur(){
        return this.envoyeur;
    }
}

