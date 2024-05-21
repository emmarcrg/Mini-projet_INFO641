import java.awt.event.ActionListener;

public interface PapotageListener extends ActionListener {
    void transmettre_potin(PapotageEvent potin);
    void recevoir_potin(PapotageEvent potin);
}

