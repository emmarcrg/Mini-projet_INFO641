import java.awt.event.ActionEvent;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans le batiment où tous les potins sont permis");   

        // Création du concierge
        Concierge cyprien=new Concierge("Cyprien");

        // Création du batiment
        Batiment batiment=new Batiment("PotinLand", cyprien);

        // Création de nouveaux bavards
        Bavard charlotte=new Bavard("Charlotte", cyprien);
        Bavard emma=new Bavard("Emma", cyprien);

        // Ajout des bavard au batiment
        batiment.ajouter_habitant(charlotte);
        batiment.ajouter_habitant(emma);

        batiment.souscription_potin();
        
        Object source = new Object();
        emma.transmettre_potin(new PapotageEvent(source, ActionEvent.ACTION_PERFORMED, "command","potins", "thomas", "pas de nouvelles",emma));
        charlotte.transmettre_potin(new PapotageEvent(source, ActionEvent.ACTION_PERFORMED, "command","potins", "pmb", "ils sont tous nul", charlotte));
        
        String[] liste_themes={"potins", "sport", "cours", "sorties", "vacances", "autre"};
        // Lancement de l'interface :
        BatimentInterface bat=new BatimentInterface(batiment, liste_themes);
        System.out.println("Ouverture de l'interface graphique");
        bat.setVisible(true);


    }

}
