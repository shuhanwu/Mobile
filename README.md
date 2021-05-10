# Test The Score Mobile App
<!--
*** Thanks for checking out. Due to the time constraint if you have suggestions
*** and/or questions that would make this better, please email shuhan.wu@hotmail.com
*** Thanks again!
-->

The repo include the maven and testng file with data sheet for parameters.

1. Install the emulator (suggest Pixel 4 API 30) for windows by following
https://developer.android.com/studio/run/emulator

2. Update the TheScoreData.xlsx for the device configuration and league name to be used for the test suite

3. Place the Data File "TheScoreData.xlsx" under /src/test/java/TheScore/TheScoreTest/ folder

4. Import the project into Eclipse IDE

5. Clean build the project and make sure the proper class path is set for testng.xml (i.e. <class name="TheScore.TheScoreTest.ScoreAppTest"/>)

6. Install Appium for windows and open Appium server with default settings

7. Right Click "testng.xml" and select "Run As" then choose "TestNG Suite"

The Test will start to run and check out this video to see 

https://www.youtube.com/watch?v=LCz5dJjOs6A
