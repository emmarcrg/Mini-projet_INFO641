public class Batiment {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans le batiment où tous les potins sont permis");

        // Création du concierge
        Concierge cyprien=new Concierge();

        // Création de nouveaux bavards liés au concierge
        // dès qu'un bavard est ajouté au batiment, il s'intéresse automatiquement aux messages du concierge
        Bavard charlotte=new Bavard(cyprien);
        cyprien.ajouter_bavard(charlotte);
        Bavard emma=new Bavard(cyprien);
        cyprien.ajouter_bavard(emma);

       

    }

}
