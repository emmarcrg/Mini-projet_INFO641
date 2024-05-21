import java.awt.event.ActionEvent;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans le batiment où tous les potins sont permis");   

        // Création du concierge
        Concierge cyprien=new Concierge("cyprien");

        // Création du batiment
        Batiment batiment = new Batiment("PotinLand", cyprien);

        // Création de nouveaux bavards
        Bavard charlotte=new Bavard("charlotte",cyprien);
        Bavard emma=new Bavard("emma", cyprien);

        // Ajout des bavard au batiment
        batiment.ajouter_habitant(charlotte);
        batiment.ajouter_habitant(emma);

        batiment.souscription_potin();
        Object source = new Object();
        emma.transmettre_potin(new PapotageEvent(source, ActionEvent.ACTION_PERFORMED, "command","thomas", "pas de nouvelles",emma));
        System.out.println(charlotte.get_messages().toString());

        //BatimentInterface bi = new BatimentInterface();
    }

}
