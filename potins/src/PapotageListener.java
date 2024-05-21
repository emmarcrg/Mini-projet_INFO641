import java.awt.event.ActionListener;

public interface PapotageListener extends ActionListener {
    void recevoir_potin(PapotageEvent potin);
}

