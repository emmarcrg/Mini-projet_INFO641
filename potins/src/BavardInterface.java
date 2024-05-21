import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BavardInterface extends JFrame {
    private Bavard bavard;
    private JTextArea sujetArea;
    private JTextArea descriptionArea;

    public BavardInterface(Bavard bavard) {
        this.bavard = bavard;

        setTitle("Interface du Bavard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel pour la saisie du sujet
        JPanel sujetPanel = new JPanel();
        sujetPanel.setLayout(new BorderLayout());
        sujetPanel.setBorder(BorderFactory.createTitledBorder("Sujet"));
        sujetArea = new JTextArea(3, 20);
        JScrollPane sujetScrollPane = new JScrollPane(sujetArea);
        sujetPanel.add(sujetScrollPane, BorderLayout.CENTER);

        // Panel pour la saisie de la description
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        descriptionArea = new JTextArea(5, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionPanel.add(descriptionScrollPane, BorderLayout.CENTER);

        // Bouton pour envoyer le sujet et la description
        JButton sendButton = new JButton("Envoyer");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujet = sujetArea.getText();
                String description = descriptionArea.getText();
                if (!sujet.isEmpty() && !description.isEmpty()) {
                    bavard.transmettre_potin(new PapotageEvent(sujet, description));
                    sujetArea.setText("");
                    descriptionArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(BavardInterface.this, "Veuillez saisir un sujet et une description.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ajout des composants au panneau principal
        mainPanel.add(sujetPanel, BorderLayout.NORTH);
        mainPanel.add(descriptionPanel, BorderLayout.CENTER);
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Bavard bavard = new Bavard("Jean", new Concierge("cyprien"));
        BavardInterface interfaceBavard = new BavardInterface(bavard);
    }
}
