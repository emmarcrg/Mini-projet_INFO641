import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BatimentInterface extends JFrame implements PapotageListener{ 
    final static int HAUTEUR=500;
	final static int LARGEUR=700; 
    private JPanel zone_affichage;
    private Bavard bavard_selectionne;
    private Batiment batiment;
    private String[] liste_themes;
    private String theme_selectionne;
    
    public BatimentInterface(Batiment bat, String[] liste_themes) {
        this.batiment=bat;
        this.liste_themes=liste_themes;
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
        // on lance automatiquement l'interface pour le 1e bavard de la liste
        bavard_selectionne=batiment.get_concierge().get_liste_bavards().get(0);
        bouton_bavards.setForeground(couleur_choix);
        bouton_bavards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficher_zone_bavards(bavard_selectionne, theme_selectionne);
            }
        });
        zone_choix.add(bouton_bavards);
        zone_affichage=new JPanel();
        Color couleur_affichage=Color.decode("#e5f4e3");
        zone_affichage.setBackground(couleur_affichage);
        zone_affichage.setPreferredSize(new Dimension(600, 400));
        JLabel message=new JLabel("Bienvenue dans l'interface des Potins ! Selectionnez l'interface que vous voulez afficher.");
        message.setForeground(couleur_choix);
        zone_affichage.add(message);
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
        JTextArea nom_bavard=new JTextArea("Entrez le nom du nouveau bavard");
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
                // on remet l'interface à jour
                afficher_zone_batiment();
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
        JLabel connection_habitant=new JLabel("Habitants non connectés aux potins :");
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
        JLabel deconnection_habitant=new JLabel("Habitants connectés aux potins :");
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
        Concierge concierge = batiment.get_concierge();
        zone_affichage.removeAll();

        // on créé l'affichage pour le concierge
        Color couleur_affichage=Color.decode("#e5f4e3");
        zone_affichage.setBackground(couleur_affichage);

        JPanel zone_nom_concierge=new JPanel();
        zone_nom_concierge.setPreferredSize(new Dimension(600, 50 ));
        zone_nom_concierge.setBackground(couleur_affichage);
        JTextField nom_concierge=new JTextField(concierge.get_nom());
        nom_concierge.setBorder(null);
        nom_concierge.setEditable(false);
        nom_concierge.setFocusable(false);
        nom_concierge.setForeground(Color.decode("305252"));
        nom_concierge.setBackground(couleur_affichage);
        Font ancienne_ecriture=nom_concierge.getFont();
        Font nouvelle_ecriture=ancienne_ecriture.deriveFont(20f); 
        nom_concierge.setFont(nouvelle_ecriture);
        zone_nom_concierge.add(nom_concierge);
        zone_affichage.add(zone_nom_concierge);

        Color couleur_zone_concierge=Color.decode("#B7D5D4");
        Color couleur_texte_concierge=Color.decode("#513B56");

        JPanel zone_affichage_messages=new JPanel();
        zone_affichage_messages.setBackground(couleur_zone_concierge);
        zone_affichage_messages.setLayout(new BoxLayout(zone_affichage_messages, BoxLayout.Y_AXIS));

        JPanel texte_concierge=new JPanel();
        texte_concierge.setBackground(couleur_zone_concierge);
        JTextField texte_liste=new JTextField("Liste des messages reçus :");
        texte_liste.setBorder(null);
        texte_liste.setEditable(false);
        texte_liste.setFocusable(false);
        texte_liste.setForeground(couleur_texte_concierge);
        texte_liste.setBackground(couleur_zone_concierge);
        texte_concierge.add(texte_liste); 
        zone_affichage_messages.add(texte_concierge);
        
        JPanel zone_message=new JPanel();
        zone_message.setBackground(couleur_zone_concierge);
        zone_message.setLayout(new BoxLayout(zone_message, BoxLayout.Y_AXIS));


        for (int i=0; i<concierge.get_nombre_message();i++ ){
            JPanel panel_message=new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel_message.setBackground(couleur_zone_concierge);
            JTextField message_recu=new JTextField(concierge.get_message(i));
            message_recu.setBorder(null);
            message_recu.setEditable(false);
            message_recu.setFocusable(false);
            message_recu.setForeground(couleur_texte_concierge);
            message_recu.setBackground(couleur_zone_concierge);
            panel_message.add(message_recu);
            zone_message.add(panel_message);
        }
        
        zone_affichage_messages.add(zone_message);
        zone_affichage.add(zone_affichage_messages);
        zone_affichage.revalidate();
        zone_affichage.repaint();
    }


    public void afficher_zone_bavards(Bavard bavard_selectionne, String theme_selectionnne){
        System.out.println("Affichage de l'interface Bavards");
        this.setTitle("Gestion des potins - Bavards");
        zone_affichage.removeAll();
        
        // on créé l'affichage pour les bavards
        Color couleur_affichage=Color.decode("#e5f4e3");
        Color couleur_texte=Color.decode("#513b56");
        zone_affichage.setBackground(couleur_affichage);
        JPanel affichage_bavard=new JPanel();
        affichage_bavard.setBackground(couleur_affichage);
        affichage_bavard.setLayout(new BoxLayout(affichage_bavard, BoxLayout.Y_AXIS));

        JPanel zone_bavard=new JPanel();
        zone_bavard.setPreferredSize(new Dimension(600, 50));
        zone_bavard.setBackground(couleur_affichage);

        String[] liste_bavards=batiment.get_concierge().get_nom_bavards();
        JComboBox<String> choix_bavard=new JComboBox<>(liste_bavards);
        zone_bavard.add(choix_bavard);
        choix_bavard.setForeground(Color.decode("#488286"));
        choix_bavard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Récupérer l'option sélectionnée
                String selection_nom_bavard=(String) choix_bavard.getSelectedItem();
                System.out.println("Bavard sélectionnée : " + selection_nom_bavard);
                ArrayList<Bavard> liste_bavard=batiment.get_concierge().get_liste_bavards();
                for (Bavard bavard : liste_bavard){
                    System.out.println(selection_nom_bavard);
                    if (bavard.get_nom().equals(selection_nom_bavard)){
                        Bavard bavard_selectionne=bavard;
                        // on déplace le bavard sélectionné au début de la liste 
                        batiment.get_concierge().deplacer_bavard_debut_liste(bavard_selectionne);                        
                        // on remet l'affichage à jour après avoir selectionné le bavard
                        afficher_zone_bavards(bavard_selectionne, theme_selectionnne);
                    }
                }
                
            };
        });
        zone_bavard.add(choix_bavard);
        final Bavard selection_bavard=bavard_selectionne;
        if (bavard_selectionne==null){
            // Normalement cela ne devrait pas arriver mais on re récupère le 1e bavard de la liste
           bavard_selectionne=batiment.get_concierge().get_liste_bavards().get(0);
        }        
        // maintenant qu'on a recupéré le bavard selectionné, on va pouvoir afficher ses informations :
        // on affiche son état de connection
        if (bavard_selectionne.get_connection()) {
            JLabel etat_bavard=new JLabel(bavard_selectionne.get_nom() + " est connecté.e");
            etat_bavard.setForeground(Color.decode("#488286"));
            JButton deconnection = new JButton("Déconnection");
            deconnection.setForeground(Color.decode("#488286"));
            deconnection.setBackground(couleur_affichage);
            deconnection.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    selection_bavard.set_connection(false);
                    // on remet l'affichage à jour après avoir déconnecté le bavard
                    afficher_zone_bavards(selection_bavard, theme_selectionnne);
                };  
            }); 
            bavard_selectionne=selection_bavard;
            zone_bavard.add(etat_bavard);
            zone_bavard.add(deconnection); 
        }
        else{
            JLabel etat_bavard=new JLabel(bavard_selectionne.get_nom()+" n'est pas connecté.e");
            etat_bavard.setForeground(Color.decode("#488286"));
            JButton connection = new JButton("Connection");
            connection.setForeground(Color.decode("#488286"));
            connection.setBackground(couleur_affichage);
            connection.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    selection_bavard.set_connection(true);
                    // on remet l'affichage à jour après avoir connecté le bavard
                    afficher_zone_bavards(selection_bavard, theme_selectionnne);
                };
            }); 
            bavard_selectionne=selection_bavard;
            zone_bavard.add(etat_bavard);
            zone_bavard.add(connection);
        }

        
        affichage_bavard.add(zone_bavard);

        // zone d'envoie d'un message
        JPanel zone_envoie_message=new JPanel();
        zone_envoie_message.setPreferredSize(new Dimension(600, 50));
        Color couleur_fond_texte_message=Color.decode("#EAC3CA");
        zone_envoie_message.setBackground(couleur_fond_texte_message);
        JComboBox<String> theme_message=new JComboBox<>(liste_themes);
        theme_message.setForeground(couleur_texte);
        theme_message.setBackground(couleur_fond_texte_message);
        zone_envoie_message.add(theme_message);
        JTextArea sujet_message=new JTextArea("Entrez le sujet du message");
        sujet_message.setForeground(couleur_texte);
        sujet_message.setBackground(couleur_fond_texte_message);
        zone_envoie_message.add(sujet_message);
        JTextArea message=new JTextArea("Entrez le contenu du message");
        message.setForeground(couleur_texte);
        message.setBackground(couleur_fond_texte_message);
        zone_envoie_message.add(message);
        JButton envoyer=new JButton("Envoyer");
        envoyer.setForeground(couleur_texte);
        envoyer.setBackground(couleur_fond_texte_message);
        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Un message a été envoyé par "+ selection_bavard.get_nom());
                // on récupère le thème du message
                String theme=(String) theme_message.getSelectedItem();
                // on récupère le texte entré dans les JTextArea :
                String sujet=sujet_message.getText();
                String contenu=message.getText();
                selection_bavard.transmettre_potin(new PapotageEvent(new Object(), ActionEvent.ACTION_PERFORMED, "command", theme, sujet, contenu, selection_bavard));
                // on remet à jour l'interface
                afficher_zone_bavards(selection_bavard, theme_selectionnne);
            }
        });
        zone_envoie_message.add(envoyer);
        affichage_bavard.add(zone_envoie_message);    
        
        // zone d'affichage des messages reçus :
        JPanel zone_message=new JPanel();
        zone_message.setBackground(couleur_affichage);

        // chaque message est affiché en dessous de l'autre
        zone_message.setLayout(new BoxLayout(zone_message, BoxLayout.Y_AXIS));

        Color couleur_zone_messages=Color.decode("#B7D5D4");
        Color couleur_texte_messages=Color.decode("#513B56");

        JPanel zone_texte_message=new JPanel();
        zone_texte_message.setBackground(couleur_affichage);
        JLabel texte_message=new JLabel("Messagerie : ");
        texte_message.setForeground(couleur_texte_messages);
        zone_texte_message.add(texte_message);
        zone_message.add(zone_texte_message);
        
        for (int i=0; i<bavard_selectionne.get_nombre_message();i++ ){
            JPanel panel_message=new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel_message.setBackground(couleur_zone_messages);
            JTextField message_recu=new JTextField(bavard_selectionne.get_message(i));
            message_recu.setBorder(null);
            message_recu.setEditable(false);
            message_recu.setFocusable(false);
            message_recu.setForeground(couleur_texte_messages);
            message_recu.setBackground(couleur_zone_messages);
            panel_message.add(message_recu);
            zone_message.add(panel_message);
        }


        //// Selection des thèmes que le bavard veut afficher
        JPanel zone_theme=new JPanel();
        zone_theme.setBackground(couleur_affichage);
        // affichage de chaque message du thème selectionné les un en dessous des autres
        zone_theme.setLayout(new BoxLayout(zone_theme, BoxLayout.Y_AXIS));

        // Selection du thème
        JPanel zone_choix_theme=new JPanel();
        zone_choix_theme.setPreferredSize(new Dimension(600, 30));
        zone_choix_theme.setBackground(couleur_affichage);
        // on lance automatiquement l'interface pour le 1e thème de la liste
        theme_selectionne=liste_themes[0];
        // JCombo box pour choisir un thème à afficher
        JComboBox<String> choix_theme=new JComboBox<>(liste_themes);
        choix_theme.setBackground(couleur_affichage);
        choix_theme.setForeground(couleur_texte_messages);
        choix_theme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String theme_selectionne=choix_theme.getSelectedItem().toString();
                System.out.println("Theme selectionné : "+theme_selectionne);
                // on déplace le thème sélectionné au début de la liste 
                List<String> liste_modifiee=new ArrayList<>(Arrays.asList(liste_themes));
                liste_modifiee.remove(theme_selectionne);
                liste_modifiee.add(0, theme_selectionne);
                liste_themes=liste_modifiee.toArray(new String[0]);
                // on remet à jour l'interface après avoir selectionné le thème
                afficher_zone_bavards(selection_bavard, theme_selectionne);

            };
        });
        final String theme=theme_selectionne;
        JLabel texte_choix_theme = new JLabel("Selectionnez le thème à afficher");
        texte_choix_theme.setForeground(couleur_texte_messages);
        zone_choix_theme.add(texte_choix_theme);
        zone_choix_theme.add(choix_theme);
        zone_theme.add(zone_choix_theme);
        
        //Zone d'affichage des messages correspondant au thème
        JPanel zone_affichage_theme=new JPanel();
        zone_affichage_theme.setBackground(couleur_zone_messages);
        zone_affichage_theme.setLayout(new BoxLayout(zone_affichage_theme, BoxLayout.Y_AXIS));

        for (int i=0; i<bavard_selectionne.get_nombre_message();i++ ){
            String message_traite=bavard_selectionne.get_message(i);
            // on prend en compte seulement les messages qui parlent du thème
            if (message_traite.contains(theme)){
                JPanel panel_message=new JPanel(new FlowLayout(FlowLayout.CENTER));
                panel_message.setBackground(couleur_zone_messages);
                JTextField message_recu=new JTextField(bavard_selectionne.get_message(i));
                message_recu.setBorder(null);
                message_recu.setEditable(false);
                message_recu.setFocusable(false);
                message_recu.setForeground(couleur_texte_messages);
                message_recu.setBackground(couleur_zone_messages);
                panel_message.add(message_recu);
                zone_affichage_theme.add(panel_message);
            }
        }
        zone_theme.add(zone_affichage_theme);        
        
        // ajout des élémennt dans l'affichage final
        affichage_bavard.add(zone_message);
        affichage_bavard.add(zone_theme);
        zone_affichage.add(affichage_bavard);
        zone_affichage.revalidate();
        zone_affichage.repaint();
    }

//.......//

    @Override
    public void recevoir_potin(PapotageEvent potin) {
        for (Bavard habitant : batiment.get_habitants()){
            if (habitant.get_connection() && habitant!=potin.getEnvoyeur()){
                String nouveau_message=potin.affichage_simple();
                System.out.println(nouveau_message);
            } 
        }   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}