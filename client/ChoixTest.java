import java.util.*;

public class ChoixTest{
    public static void main(String[] args) {
        Choix C = new Choix();
        ArrayList<String> l1 = new ArrayList<String>();
        ArrayList<String> l2 = new ArrayList<String>();
        l1.add("choix1");
        l1.add("choix2");
        l1.add("choix3");
        l1.add("choix4");
        l1.add("choix1");
        l1.add("choix2");
        l1.add("choix4");
        l2.add("choix2");
        l2.add("choix1");
        l2.add("choix2");
        C.setListe_choix_pref(l1);
        C.setListe_choix_sec(l2);
        System.out.println(C.pasDeDoublon(l1));
        System.out.println(C.pasDeDoublon(l2));

        C.setChoix_pref("coucou",2,l1);
        C.setChoix_pref("coucou",2,l2);
        System.out.println(C.getListe_choix_pref());
        System.out.println(C.getListe_choix_sec());
        System.out.println(C.choixValide());
    }

}