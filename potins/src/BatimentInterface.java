import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BatimentInterface extends JFrame {
    private Batiment bat;
    private ArrayList<Bavard> bavards;
    private JPanel bavardPanel;   

    public BatimentInterface() {
        Concierge cyprien=new Concierge();
        bat = new Batiment("PotinLand", cyprien);
        setTitle("Interface du Bâtiment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bavards = new ArrayList<>();

        // Panel principal pour les bavards
        bavardPanel = new JPanel();
        bavardPanel.setLayout(new BoxLayout(bavardPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(bavardPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel pour ajouter un bavard
        JPanel addBavardPanel = new JPanel();
        JTextField bavardNameField = new JTextField(10);
        JButton addButton = new JButton("Ajouter Bavard");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = bavardNameField.getText();
                if ((!name.isEmpty())){
                    boolean bavardExiste=false;
                    for (Bavard b : bavards) {
                        if (b.get_nom().equals(name)) {
                            bavardExiste = true;
                            break;
                    }}
                    if(!bavardExiste) {
                        Bavard bavard = new Bavard(name, cyprien);
                        bavards.add(bavard);
                        addBavardToPanel(bavard);
                        bavardNameField.setText("");
                    }
                    }else{
                        //On se connecte sur la page du Bavard
                        //On veut l'index 
                        int idx=0;
                        while(bavards.get(idx).get_nom()!=name){
                            idx+=1;
                        }
                        bavards.get(idx);
                    }
                }
            }
        );
        addBavardPanel.add(new JLabel("Nom du Bavard: "));
        addBavardPanel.add(bavardNameField);
        addBavardPanel.add(addButton);
        add(addBavardPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void addBavardToPanel(Bavard bavard) {
        JLabel bavardLabel = new JLabel(bavard.get_nom());
        bavardPanel.add(bavardLabel);
        revalidate();
        repaint();
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BatimentInterface();
            }
        });
    }
}