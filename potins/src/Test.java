import java.awt.event.ActionEvent;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans le batiment où tous les potins sont permis");   

        // Création du concierge
        Concierge cyprien=new Concierge("Cyprien");

        // Création du batiment
        Batiment batiment = new Batiment("PotinLand", cyprien);

        // Création de nouveaux bavards
        Bavard charlotte=new Bavard("Charlotte", cyprien);
        Bavard emma=new Bavard("Emma", cyprien);

        // Ajout des bavard au batiment
        batiment.ajouter_habitant(charlotte);
        batiment.ajouter_habitant(emma);

        batiment.souscription_potin();
        
        Object source = new Object();
        emma.transmettre_potin(new PapotageEvent(source, ActionEvent.ACTION_PERFORMED, "command","thomas", "pas de nouvelles",emma));
        charlotte.transmettre_potin(new PapotageEvent(source, ActionEvent.ACTION_PERFORMED, "command","pmb", "ils sont tous nul", charlotte));
        //System.out.println("potin : " +cyprien.get_potin());


        // à faire : extend PapotageListener dans ConciergeInterface pour qu'il puisse détecter l'arrivée des noveaux messages

        // modification d'un Bavard : booleen interet s'il veut savoir ce qui se passe 



        // Lancement de l'interface :

        BatimentInterface bat = new BatimentInterface(batiment);
        System.out.println("Ouverture de l'interface graphique");
        bat.setVisible(true);


    }

}
