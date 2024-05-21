import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatimentInterface extends JFrame implements PapotageListener{ 
    final static int HAUTEUR=500;
	final static int LARGEUR=700; 
    private JPanel zone_affichage;

    private Batiment batiment;
    private PapotageListener potin;
    
    public BatimentInterface(Batiment bat) {
        this.batiment=bat;
        this.setSize(LARGEUR, HAUTEUR);
        this.setTitle("Gestion des potins");
            
        JPanel fond=new JPanel();
        Color couleur_fond=Color.decode("#b7d5d4");
        fond.setBackground(couleur_fond);
        this.getContentPane().add(fond, BorderLayout.CENTER);

        // Zone de choix de l'interface à afficher
        JPanel zone_choix = new JPanel();
        Color couleur_choix = Color.decode("#488286");
        zone_choix.setBackground(couleur_choix);
        fond.add(zone_choix);
        

        JButton bouton_batiment = new JButton("Batiment");
        bouton_batiment.setForeground(couleur_choix);
        bouton_batiment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficher_zone_batiment();
            }
        });
        zone_choix.add(bouton_batiment);

        JButton bouton_concierge = new JButton("Concierge");
        bouton_concierge.setForeground(couleur_choix);
        bouton_concierge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficher_zone_concierge();
            }
        });
        zone_choix.add(bouton_concierge);

        JButton bouton_bavards = new JButton("Bavards");
        bouton_bavards.setForeground(couleur_choix);
        bouton_bavards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficher_zone_bavards();
            }
        });
        zone_choix.add(bouton_bavards);
        zone_affichage=new JPanel();
        Color couleur_affichage=Color.decode("#e5f4e3");
        zone_affichage.setBackground(couleur_affichage);
        zone_affichage.setPreferredSize(new Dimension(600, 400));
        fond.add(zone_affichage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public void afficher_zone_batiment(){
        System.out.println("Affichage de l'interface Batiment");
        this.setTitle("Gestion des potins - Batiment");
        // on commence par effacer l'affichage qui était déjà présent
        zone_affichage.removeAll();

        // on crée l'affichage pour le batiment
        Color couleur_affichage=Color.decode("#e5f4e3");
        zone_affichage.setBackground(couleur_affichage);
        JTextField nom_batiment = new JTextField(batiment.get_nom());
        nom_batiment.setBorder(null);
        nom_batiment.setEditable(false);
        nom_batiment.setFocusable(false);
        nom_batiment.setForeground(Color.decode("305252"));
        nom_batiment.setBackground(couleur_affichage);
        Font ancienne_ecriture=nom_batiment.getFont();
        Font nouvelle_ecriture=ancienne_ecriture.deriveFont(20f); 
        nom_batiment.setFont(nouvelle_ecriture);
        zone_affichage.add(nom_batiment);

        // on créé la zone de création d'un bavard
        JPanel zone_creation_bavard=new JPanel();
        zone_creation_bavard.setBackground(couleur_affichage);
        zone_creation_bavard.setPreferredSize(new Dimension(600, 50));
        JTextArea nom_bavard= new JTextArea("Entrez le nom du nouveau bavard");
        Color fond_bavard=Color.decode("#eAC3CA");
        Color couleur_texte_bavard=Color.decode("#513b56");
        nom_bavard.setBackground(fond_bavard);
        nom_bavard.setForeground(couleur_texte_bavard);
        zone_creation_bavard.add(nom_bavard);
        // on a qu'un seul concierge par batiment donc le bavard sera automatiquement associé à ce concierge
       
        JButton valider_bavard=new JButton("Valider l'ajout");
        valider_bavard.setBackground(fond_bavard);
        valider_bavard.setForeground(couleur_texte_bavard);
        valider_bavard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // on récupère le texte entré dans le JTextArea
                // on récupère les informations qui ont été entrées via l'interface
                for (Component comp : zone_creation_bavard.getComponents()) {
                    if (comp instanceof JTextArea) {
                        JTextArea texte_entre=(JTextArea) comp;
                        String nom_nouveau_bavard=texte_entre.getText();
                        if (nom_nouveau_bavard.isEmpty()) {
                            System.out.println("Il faut entré un nom pour le nouveau bavard");
                            break; // Sortir de la boucle dès qu'un texte est vide
                        }
                        else{
                            // on crée le bavard depuis le batiment
                            batiment.creer_bavard(nom_nouveau_bavard, batiment.get_concierge());
                        }
                    }
                }
            };
        });

        zone_creation_bavard.add(valider_bavard);

        // on créé la zone de connection des bavards aux potins
        JPanel zone_choix_connection=new JPanel();
        zone_choix_connection.setLayout(new BoxLayout(zone_choix_connection, BoxLayout.Y_AXIS));
        zone_choix_connection.setBackground(couleur_affichage);

        // 2 zones différentes : une pour les bavards connectés et une pour ceux non connectés
        // zone des bavards non connectés :
        Color couleur_zone_connection=Color.decode("#B7D5D4");
        JPanel zone_connection_bavard=new JPanel();
        zone_connection_bavard.setLayout(new BoxLayout(zone_connection_bavard, BoxLayout.Y_AXIS));
        zone_connection_bavard.setBackground(couleur_affichage);
        JPanel texte_connection=new JPanel();
        texte_connection.setBackground(couleur_zone_connection);
        JTextField connection_habitant=new JTextField("Habitants non connectés aux potins :");
        connection_habitant.setBorder(null);
        connection_habitant.setEditable(false);
        connection_habitant.setFocusable(false);
        connection_habitant.setForeground(couleur_texte_bavard);
        connection_habitant.setBackground(couleur_zone_connection);
        texte_connection.add(connection_habitant);
        zone_connection_bavard.add(texte_connection);

        JPanel zone_bouton_connection=new JPanel();
        zone_bouton_connection.setBackground(couleur_zone_connection);
        zone_connection_bavard.add(zone_bouton_connection);
        
        // zone des bavards déjà connectés :
        JPanel zone_deconnection_bavard=new JPanel();
        zone_deconnection_bavard.setBackground(couleur_zone_connection);
        zone_deconnection_bavard.setLayout(new BoxLayout(zone_deconnection_bavard, BoxLayout.Y_AXIS));
        JPanel texte_deconnection=new JPanel();
        texte_deconnection.setBackground(couleur_zone_connection);
        JTextField deconnection_habitant=new JTextField("Habitants connectés aux potins :");
        deconnection_habitant.setBorder(null);
        deconnection_habitant.setEditable(false);
        deconnection_habitant.setFocusable(false);
        deconnection_habitant.setForeground(couleur_texte_bavard);
        deconnection_habitant.setBackground(couleur_zone_connection);
        texte_deconnection.add(deconnection_habitant);
        zone_deconnection_bavard.add(texte_deconnection);
        JPanel zone_bouton_deconnection = new JPanel();
        zone_bouton_deconnection.setBackground(couleur_zone_connection);
        zone_deconnection_bavard.add(zone_bouton_deconnection);
        

        // on récupère tous les habitants du batiment
        for (Bavard habitant : batiment.get_habitants()){
            // pour connecter un bavard : on affiche tous les bavards non connecté sous forme de bouton
            if (!habitant.get_connection()){
                JButton connecter_bavard=new JButton(habitant.get_nom());
                connecter_bavard.setBackground(couleur_affichage);
                connecter_bavard.setForeground(Color.decode("#488286"));
                connecter_bavard.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        // si on clique sur un bavard non connecté alors il devient connecté
                        habitant.set_connection(true);
                        // on met à jour l'affichage de l'interface
                        afficher_zone_batiment();
                    };
                });  
                zone_bouton_connection.add(connecter_bavard);
            }
            // pour déconnecter un bavard : on affiche tous les bavards connectés sous forme de bouton
            else{
                JButton deconnecter_bavard=new JButton(habitant.get_nom());
                deconnecter_bavard.setBackground(couleur_affichage);
                deconnecter_bavard.setForeground(Color.decode("#488286"));
                deconnecter_bavard.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        // si on clique sur un bavard connecté il devient non connecté
                        habitant.set_connection(false);
                        // on met à jour l'affichage de l'interface
                        afficher_zone_batiment();
                    };
                });  
                zone_bouton_deconnection.add(deconnecter_bavard);
            }
        }

        zone_choix_connection.add(zone_connection_bavard);
        zone_choix_connection.add(zone_deconnection_bavard);

        zone_affichage.add(zone_creation_bavard);
        zone_affichage.add(zone_choix_connection);
        zone_affichage.revalidate();
        zone_affichage.repaint();
    }

    public void afficher_zone_concierge(){
        System.out.println("Affichage de l'interface Concierge");
        this.setTitle("Gestion des potins - Concierge");
        zone_affichage.removeAll();

        // on créé l'affichage pour le concierge
        Color couleur_affichage=Color.decode("#e5f4e3");
        zone_affichage.setBackground(couleur_affichage);
        JTextField nom_concierge = new JTextField(batiment.get_concierge().get_prenom());
        nom_concierge.setBorder(null);
        nom_concierge.setEditable(false);
        nom_concierge.setFocusable(false);
        nom_concierge.setForeground(Color.decode("305252"));
        nom_concierge.setBackground(couleur_affichage);
        Font ancienne_ecriture=nom_concierge.getFont();
        Font nouvelle_ecriture=ancienne_ecriture.deriveFont(20f); 
        nom_concierge.setFont(nouvelle_ecriture);
        zone_affichage.add(nom_concierge);




        zone_affichage.revalidate();
        zone_affichage.repaint();
    }


    public void afficher_zone_bavards(){
        System.out.println("Affichage de l'interface Bavards");
        this.setTitle("Gestion des potins - Bavards");
        zone_affichage.removeAll();

        zone_affichage.revalidate();
        zone_affichage.repaint();
    }

//.......//

    @Override
    public void transmettre_potin(PapotageEvent potin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transmettre_potin'");
    }

    @Override
    public void recevoir_potin(PapotageEvent potin) {
        String nouveau_message=potin.print_message();
        System.out.println(nouveau_message);       
    }

}