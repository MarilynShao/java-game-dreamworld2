package demoworld.controller;

/**
 * Interface that indicates anything that implements it supports opening the search screen for
 * values from a Character
 */
public interface CanSearchCharacter {
     /**
      * Can open the search panel and give it values from the current
      * {@link demoworld.model.Character} to represent.
      */
     void openCharacterSearch();
}
