import java.awt.event.ActionEvent;

public class OnLineBavardEvent extends ActionEvent{

    public OnLineBavardEvent(Object source, int id, String command, Bavard b) {
        super(source, id, command);
        System.out.println("Le bavard "+ b.get_nom() +" est connecté." );
    }


}