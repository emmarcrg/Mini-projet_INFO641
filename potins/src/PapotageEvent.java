public class PapotageEvent {
    private String sujet;
    private String corps;

    public PapotageEvent(){
        super();
    }

    public void setMessage (String sj, String cp){
        this.sujet=sj;
        this.corps=cp;
    }

    public String print_message (){
        return "Le sujet "+ this.sujet+". Le message : "+this.corps;
    }
}

