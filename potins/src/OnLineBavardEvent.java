import java.awt.event.ActionEvent;

public class OnLineBavardEvent extends ActionEvent{
    private Bavard bavard;
    public OnLineBavardEvent(Object source, int id, String command, Bavard b) {
        super(source, id, command);
        this.bavard=b;
        System.out.println("Le bavard "+ b.get_nom() +" est connecté." );
    }

    public String get_message(){
        return "Le bavard "+ this.bavard.get_nom() +" est connecté." ;
    }

}