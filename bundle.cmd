@echo OFF
setlocal enabledelayedexpansion

:: Backup existing submission
if not exist submission.zip goto nozip
echo - Backing up existing submission.zip

:: Get the current date and time in a consistent format
for /f "tokens=2 delims==" %%A in ('"wmic os get localdatetime /value"') do set datetime=%%A

:: Extract date and time components
set yy=!datetime:~0,4!
set mm=!datetime:~4,2!
set dd=!datetime:~6,2!
set hh=!datetime:~8,2!
set min=!datetime:~10,2!
set sec=!datetime:~12,2!

:: Format the timestamp
set timestamp=!yy!!mm!!dd!!hh!!min!!sec!

:: Rename the file
ren submission.zip submission-!timestamp!.zip

:nozip

:: See if there is an existing zip on this system
echo - Checking for an installed zip program
set zipath=
"%zipath%zip" >nul 2>nul
if %ERRORLEVEL%==0 echo   Yes, found one && goto COMMON
echo   No, none found.

:: See if we've been here before and already install gnu zip
echo - Checking if we have installed one before
set zipath=%ProgramFiles(x86)%\GnuWin32\bin\
"%zipath%zip" >nul 2>nul
if %ERRORLEVEL%==0 echo   Yes, we have && goto COMMON

echo   No, we haven't. Let's install one.
echo.
echo You be be asked to allow "download.exe" to run.
echo Please click on YES when it asks for permission.
echo.
pause

winget install gnuwin32.zip

:COMMON
"%zipath%zip" submission.zip src/demoworld/controller/Controller.java
"%zipath%zip" submission.zip src/demoworld/controller/FeatureSearchController.java
"%zipath%zip" submission.zip src/demoworld/controller/GameController.java
"%zipath%zip" submission.zip src/demoworld/controller/MainController.java
"%zipath%zip" submission.zip src/demoworld/controller/MenuBarController.java
"%zipath%zip" submission.zip src/demoworld/controller/MenuController.java
"%zipath%zip" submission.zip src/demoworld/controller/sheetcontrollers/DiceController.java
"%zipath%zip" submission.zip src/demoworld/controller/sheetcontrollers/SheetController.java
"%zipath%zip" submission.zip src/demoworld/controller/SpecialtySearchController.java
"%zipath%zip" submission.zip src/demoworld/model/Character.java
"%zipath%zip" submission.zip src/demoworld/model/Dice.java
"%zipath%zip" submission.zip src/demoworld/model/DiceRoller.java
"%zipath%zip" submission.zip src/demoworld/model/EntryManager.java
"%zipath%zip" submission.zip src/demoworld/scholar/Scholar.java
"%zipath%zip" submission.zip src/demoworld/view/MainView.java
"%zipath%zip" submission.zip src/demoworld/view/Menu.java
"%zipath%zip" submission.zip src/demoworld/view/MenuBar.java
"%zipath%zip" submission.zip src/demoworld/view/Notification.java
"%zipath%zip" submission.zip src/demoworld/view/Search.java
"%zipath%zip" submission.zip src/demoworld/view/Sheet.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/DicePanel.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/EntryList.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/FeatureList.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/HpPanel.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/SpecialtyList.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/StatsPanel.java
"%zipath%zip" submission.zip src/demoworld/view/sheetpanels/XpPanel.java
"%zipath%zip" submission.zip src/demoworld/view/View.java

"%zipath%zip" submission.zip test/demoworld/model/ValueTest.java
"%zipath%zip" submission.zip test/demoworld/model/StatTest.java
"%zipath%zip" submission.zip test/demoworld/model/HitpointsTest.java
"%zipath%zip" submission.zip test/demoworld/model/CharacterTest.java

"%zipath%zip" submission.zip refs.md

:DONE
endlocal
