package demoworld;

import demoworld.controller.Controller;
import demoworld.model.Character;
import demoworld.model.DemoWorld;
import demoworld.model.RuleBook;
import demoworld.scholar.Scholar;
import demoworld.view.View;
import javax.swing.*;

/**
 * Launch the application GUI.
 */
public class Main {

    /**
     * Launch the application on the Java AWT Event Dispatching Thread (EDT) using
     * Swing Utilities invokeLater.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RuleBook rulebook = new DemoWorld();
            Character character = new Scholar(rulebook).build("./demo.sheet").getCharacter();
            View view = new View(
                    rulebook.getName()
                            + " | "
                            + rulebook.getEdition(),
                    900,
                    800
            );
            Controller controller = new Controller(view, rulebook, character);
        });
    }
}