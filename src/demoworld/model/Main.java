package demoworld.model;
import demoworld.scholar.Scholar;

/**
 * A main routine to test the model. Not used to run the GUI.
 *
 * @see demoworld.Main
 */
public class Main {

    /**
     * A main routine to test the model only.
     */
    public static void main(String[] args) {
        System.out.println("##############################");
        RuleBook rulebook = new DemoWorld();

        Character character = new Scholar(rulebook).build("demo.sheet").getCharacter();
        System.out.println(character.getHitpoints().toString());
        System.out.println(character.getStatByName("force").toString());
        System.out.println(character.getStatByName("quickness").toString());
        System.out.println(character.getStatByName("analytical").toString());
        System.out.println(character.getStatByName("empathy").toString());
        System.out.println(character.getStatByName("resilience").toString());
        for(Specialty specialty : character.getSpecialty().all()){
            System.out.println(specialty);
        }
        for(Feature feature : character.getFeature().all()){
            System.out.println(feature);
        }
        System.out.println("-----------------------------------");
    }
}