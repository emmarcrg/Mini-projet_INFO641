public class PapotageEvent {
    private String sujet;
    private String corps;

    public PapotageEvent(String sj, String cp){
        this.sujet=sj;
        this.corps=cp;
    }
    public PapotageEvent(){
        super();
    }

    public void setMessage (String sj, String cp){
        this.sujet=sj;
        this.corps=cp;
    }

    public String print_message (){
        return "Message reçu : sujet "+ this.sujet+" - contenu : "+this.corps;
    }
}

