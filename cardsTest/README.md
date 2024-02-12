### IDE used: Apache NetBeans IDE 12.5
#### Java Version JDK 17 / Java SE 17
##### :- JUnit 4.13.2 for our test, test suite

### To run the .jar file

A card pack needs to be placed in the same directory as the .jar file. You can then in the terminal run the command 

	java -jar cards.jar

And our game will start
All files created by the game will be stored in the same directory as the .jar file once a winner has been announced

### How run the the tests and test suite
First create a new project in NetBeans called CardGame

Then extract the cards.jar file
- Using jar xf cards.jar

Then in Source Packages of the project create a package called cardgame
Within cardgame add all the *.java files into it you extracted from the cards.jar 
(The cardgame folder is where the *.java files can be found once cards.jar is extracted 
or gotton from the zip file cardTest.zip, where the class name doesn't include *Test.java)

Then in the Test Packages create a package called cardgame 
And add all the *Test.java and testSuite.java files into the package, found in the extracted cardTest.zip

Inside Test Packages also create a new package called resources
Within this package add the *.txt files found in cardTest.zip

#### Adding the Test Libraries to the Project
The final steps is to add the libraries required for the tests
To do this on the project panel on the left side right click on Test Libraries and select "Add Library.."
Once pressed a pop up window will apppear, on that menu press the "Import..." button 
You'll then be shown a list of libraries that could be imported.
Scroll down to find JUnit 4.13.2 
Once found press it, the "Import Library" button should now be highlighted, press "Import Library" 
You'll then be taken back to the original menu and should see JUnit 4.13.2 in the Available Libraries section, again click JUnit 4.13.2 in this section and the "Add Library" button should be highlighted, press it and JUnit 4.13.2 will be added into the Test Libraries for the project.

The above steps will need to be repeated exactly but instead of finding and selection JUnit 4.13.2. You'll need to find and select Hamcrest 1.3

Once the above is completed, to run the test suite, you need to right click on the testSuite.java in the project panel on the left side of the screen and press "Test File". Once pressed all our tests will run sequentially

### The Project in the IDE should have this structure 

CardGame
├── Source Packages
│   └── cardgame
│		├── Player.java
│		├── Card.java
│		├── CardDeck.java
│		└── CardGame.java
├── Test Packages                   
│   ├── cardgame
│	│	├── PlayerTest.java
│	│	├── CardTest.java
│	│	├── CardDeckTest.java
│	│	├── CardGameTest.java
│	│	└── testSuite.java 
│	└── resources  
│	    ├── testPack.txt             
│	    └── letter.txt  
├── Libraries  
│   └── JDK 17  
└── Test Libraries
	    ├── JUnit 4.13.2 - junit-4.13.2.jar            
	    └── Hamcrest1.3 - hamcrest-core-1.3.jar

