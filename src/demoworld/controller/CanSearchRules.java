package demoworld.controller;

/**
 * Interface that indicates anything that implements it supports opening the search screen for
 * values from the rulebook
 */
public interface CanSearchRules {
     /**
      * Can open the search panel and give it values from the
      * {@link demoworld.model.RuleBook} to represent.
      */
     void openRulesSearch();
}
