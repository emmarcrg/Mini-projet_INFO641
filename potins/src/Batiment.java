public class Batiment {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans le batiment où tous les potins sont permis");

        // Création du concierge
        Concierge cyprien=new Concierge();

        // Création de nouveaux bavards liés au concierge
        Bavard charlotte=new Bavard(cyprien);
        Bavard emma=new Bavard(cyprien);

       

    }

}
