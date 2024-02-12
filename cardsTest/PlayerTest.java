/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cardgame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author judewallace
 */
public class PlayerTest {
    
        CardDeck mainDeck = new CardDeck();
        static Player player = new Player();
        
    public PlayerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        player.setnumberPlayer(1);
        
    }
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Collections.addAll(mainDeck.fullDeck, 1,3,4,
                    1,5,4,2,1,1,
                    1,1);
    }
    
    @After
    public void tearDown() {
        player.setnumberPlayer(1);
        mainDeck.fullDeck.removeAll(mainDeck.fullDeck);
        player.allDecks.removeAll(player.allDecks);
        
    }

    /**
     * Test of createPlayersDeckObject method, of class Player.
     * Check a deck object is made for the player
     */
    @Test
    public void testCreatePlayersDeckObject() {
        player.createPlayersDeckObject(mainDeck);
        
        assertEquals(1, player.allDecks.size());
        assertEquals(1,player.playersDeck.deckNumber);
        assertNotNull(player.playersDeck);  
    }

    /**
     * Test of createCardObject method, of class Player.
     * Check a card object is made for the player
     */
    @Test
    public void testCreateCardObject() {
        player.createCardObject();
        
        assertNotNull(player.playersCards);
        
        
    }

    /**
     * Test of setnumberPlayer method, of class Player.
     * Set the player number
     */
    @Test
    public void testSetnumberPlayer() {
        player.setnumberPlayer(5);
        
        assertEquals(5, player.numberPlayer);
    }

    /**
     * Test of setupPlayerCards method, of class Player.
     * Checks the players cards are made
     */
    @Test
    public void testSetupPlayerCards() {
        player.createCardObject();
        player.setupPlayerCards(mainDeck);

        assertEquals(1,player.playersCards.playersCards.size());

    }
    
    /**
     * Test of setupPlayerCards method, of class Player.
     */
    @Test
    public void testSetupPlayerCardsMainDeckEmpty() {
        mainDeck.fullDeck.removeAll(mainDeck.fullDeck);
        player.createCardObject();
        player.setupPlayerCards(mainDeck);
 
        assertEquals(0,player.playersCards.playersCards.size());

    }

    /**
     * Test of setPlayersDeck method, of class Player.
     * Test to see if the deck is created and filled
     */
    @Test
    public void testSetPlayersDeck() {
        CardDeck.Deck deck = mainDeck.new Deck();
        player.playersDeck = deck;
        
        player.setPlayersDeck(4);
        
        assertNotNull(player.playersDeck.deck);
        assertEquals(4, player.playersDeck.deck.size());  
    }

    /**
     * Test of createFile method, of class Player.
     * Checks to see if the file is created in the root directory
     */
    @Test
    public void testCreateFile() {
        player.createFile("playerTest_output.txt");
        
        File file = new File("playerTest_output.txt");
        assertTrue(file.exists());
    }
    
    /**
     * Test of appenedToFile method, of class Player.
     * Check the file contains the right message
     */
    @Ignore
    public void testAppenedToFile() throws IOException {
        player.appenedToFile("Test", "playerTest_output.txt" );
        
        Path p = Path.of("","playerTest_output.txt");
        
        Path file = p.resolve(p);
        String content = Files.readString(file);
        
        assertEquals("Test",content);
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        String response = player.toString();
        assertEquals("player 1", response);
    }

    /**
     * Test of setDeckToRight method, of class Player.
     * Test player gets correct deck from allDecks array list
     */
    @Test
    public void testSetDeckToRightPlayerNumberOne() {
        CardDeck.Deck deck2 = mainDeck.new Deck();
        player.createPlayersDeckObject(mainDeck);
        player.allDecks.add(deck2);
        
        player.setDeckToRight();

        assertEquals(2, player.allDecks.size());
        assertNotNull(player.allDecks);
        
        assertNotNull(player.deckToRight);
        
    }
    
    /**
     * Test of setDeckToRight method, of class Player.
     * Test player gets correct deck from allDecks array list
     */
    @Test
    public void testSetDeckToRightPlayerNumberTwo() {
        CardDeck.Deck deck2 = mainDeck.new Deck();
        player.numberPlayer = 2;
        player.allDecks.add(deck2);
        player.createPlayersDeckObject(mainDeck);
        
        
        player.setDeckToRight();

        assertEquals(2, player.allDecks.size());
        assertNotNull(player.allDecks);
        
        assertNotNull(player.deckToRight);
        
    }

    /**
     * Test of checkWin method, of class Player.
     */
    @Test
    public void testCheckWinValueAlreadySet() {
        player.playerWon = true;
        player.checkWin();
        
        assertTrue(player.playerWon);
        
    }
    
    /**
     * Test of checkWin method, of class Player.
     * Check member value set when a win is declared
     */
    @Test
    public void testCheckWinValueSet() {
        player.playerWon = false;
        assertFalse(player.playerWon);  
    }
    
}
