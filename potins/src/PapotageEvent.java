import java.awt.event.ActionEvent;

public class PapotageEvent extends ActionEvent {
    private String sujet;
    private String corps;
    private String theme;
    private Bavard envoyeur;

    public PapotageEvent(Object source, int id, String command, String theme, String sj, String cp,  Bavard envoyeur){
        super(source, id, command);
        this.theme=theme;
        this.sujet=sj;
        this.corps=cp;
        this.envoyeur=envoyeur;
    }
    public PapotageEvent(Object source, int id, String command){
        super(source, id, command);
    }

    public void setMessage (String theme, String sj, String cp){
        this.theme=theme;
        this.sujet=sj;
        this.corps=cp;
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

