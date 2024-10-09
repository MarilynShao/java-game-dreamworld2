#!/bin/bash

if [ -f submission.zip ]; then
  old=$(date +%s)
  echo "Backup up old submission as submission-${old}"
  echo
  mv submission.zip submission-"${old}".zip
fi

touch refs.md

zip submission.zip src/demoworld/controller/Controller.java
zip submission.zip src/demoworld/controller/FeatureSearchController.java
zip submission.zip src/demoworld/controller/GameController.java
zip submission.zip src/demoworld/controller/MainController.java
zip submission.zip src/demoworld/controller/MenuBarController.java
zip submission.zip src/demoworld/controller/MenuController.java
zip submission.zip src/demoworld/controller/sheetcontrollers/DiceController.java
zip submission.zip src/demoworld/controller/sheetcontrollers/SheetController.java
zip submission.zip src/demoworld/controller/SpecialtySearchController.java
zip submission.zip src/demoworld/model/Character.java
zip submission.zip src/demoworld/model/Dice.java
zip submission.zip src/demoworld/model/DiceRoller.java
zip submission.zip src/demoworld/model/EntryManager.java
zip submission.zip src/demoworld/scholar/Scholar.java
zip submission.zip src/demoworld/view/MainView.java
zip submission.zip src/demoworld/view/Menu.java
zip submission.zip src/demoworld/view/MenuBar.java
zip submission.zip src/demoworld/view/Notification.java
zip submission.zip src/demoworld/view/Search.java
zip submission.zip src/demoworld/view/Sheet.java
zip submission.zip src/demoworld/view/sheetpanels/DicePanel.java
zip submission.zip src/demoworld/view/sheetpanels/EntryList.java
zip submission.zip src/demoworld/view/sheetpanels/FeatureList.java
zip submission.zip src/demoworld/view/sheetpanels/HpPanel.java
zip submission.zip src/demoworld/view/sheetpanels/SpecialtyList.java
zip submission.zip src/demoworld/view/sheetpanels/StatsPanel.java
zip submission.zip src/demoworld/view/sheetpanels/XpPanel.java
zip submission.zip src/demoworld/view/View.java

zip submission.zip test/demoworld/model/ValueTest.java
zip submission.zip test/demoworld/model/StatTest.java
zip submission.zip test/demoworld/model/HitpointsTest.java
zip submission.zip test/demoworld/model/CharacterTest.java

zip submission.zip refs.md
