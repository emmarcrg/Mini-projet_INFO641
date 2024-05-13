public class Bavard {
    private Concierge concierge;

    public Bavard(Concierge concierge){
        this.concierge=concierge;
    }

    public Concierge get_concierge(){
        return this.concierge;
    }
    
    public void set_concierge(Concierge nouveau_concierge){
        this.concierge=nouveau_concierge;
    }

}
