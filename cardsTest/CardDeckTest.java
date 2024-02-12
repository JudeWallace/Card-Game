/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cardgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
/**
 *
 * @author judewallace
 */
@RunWith(Enclosed.class)
public class CardDeckTest {
     
    static CardDeck deck = new CardDeck();
    static int numberOfPlayers = 4;
      
    public static class CardDeckTestOuter {
       
        public CardDeckTestOuter() {
        }
        
        @After
        public void tearDown() {
             deck.fullDeck.removeAll(deck.fullDeck);
        }

        /** 
         * Test of setCardDeck method, of class CardDeck.
         * Check deck is filled with file contents
         */
        @Test
        public void testSetCardDeck() throws Exception {
            deck.setCardDeck("test/resources/testPack.txt", numberOfPlayers);
            
            assertFalse(deck.fullDeck.isEmpty());
            assertEquals(numberOfPlayers*8, deck.fullDeck.size());
        }
        
        /**
         * Test of setCardDeck method, of class CardDeck.
         * Check exception is thrown if file isn't present
         */
        @Test(expected = FileNotFoundException.class)
        public void testSetCardDeckExceptionThrown() throws Exception {
            deck.setCardDeck("exception.txt", numberOfPlayers);
        }
        
        /**
         * Test of setCardDeck method, of class CardDeck.
         * Test file reading stops when letters are in pack, and user is prompted
         * To re-enter a valid pack
         * 
         */
        @Test
        public void testSetCardDeckPackContainsLetters() throws Exception {
            deck.setCardDeck("test/resources/letter.txt", numberOfPlayers);
            
            assertNotEquals(numberOfPlayers*8,deck.fullDeck.size());
        }
        

        /**
         * Test of incorrectDeck method, of class CardDeck.
         * If pack is incorrect check array list has been emptied
         */
        @Test
        public void testIncorrectDeck() {
            Collections.addAll(deck.fullDeck, 1,3);
            
            deck.incorrectDeck();

            assertTrue(deck.fullDeck.isEmpty());
        }

        /**
         * Test of dealCard method, of class CardDeck.
         * Test the card is removed from head of array
         */
        @Test
        public void testDealCard() {
            // Set the deck to have values 
            Collections.addAll(deck.fullDeck, 1,3,4,
                    1,5,4,2,1,1,
                    1,1);
            
            int card = deck.dealCard();
            int newTopCard = deck.fullDeck.get(0);
            
            assertNotEquals(card, newTopCard);
        }
        
        /**
         * Test of dealCard method, of class CardDeck.
         * Test that -1 is returned if deck empty
         */
        @Test
        public void testDealCardEmptyDeck() {
            int card = deck.dealCard();
          
            assertEquals(-1, card);
        }
        
    }
    
    public static class CardDeck_DeckTestInner{
        
        static CardDeck.Deck playerDeck = deck.new Deck();
        
        public CardDeck_DeckTestInner() {
        }

        @After
        public void tearDown() {
            playerDeck.deck.removeAll(playerDeck.deck);
        }
        
        /**
         * Test of setDeck method, of inner class Deck.
         * Test deck gets filled with correct amount of cards
         * And the main deck is empty once all cards have been distributed
         */
        @Test
        public void testSetDeck(){
            Collections.addAll(deck.fullDeck, 1,2,5,6);
            
            playerDeck.setDeck(numberOfPlayers);
           
            assertEquals(numberOfPlayers,playerDeck.deck.size());
            assertEquals(0, deck.fullDeck.size());
            
        }
        
        /**
         * Test of finalDeckToFileCreated of inner class Deck.
         * Check a file containing the deck values with message is created
         */
        @Test
        public void testFinalDeckToFileCreated() throws IOException{
            playerDeck.deckNumber = 15;
            playerDeck.finalDeckToFile();
           
            File file = new File("deck15_output.txt");
  
            assertTrue(file.exists());
            
        }
        
        /**
         * Test of setDeck method, of inner class Deck.
         * Test the deck number is the set with the correct value
         */
        @Test
        public void testSetDeckNumber(){
            playerDeck.setDeckNumber(numberOfPlayers);
            assertEquals(numberOfPlayers, playerDeck.deckNumber); 
        }
        
        /**
         * Test of takeCard method, of inner class Deck.
         * Test the card is picked from the head of the deck and removed
         */
        @Test
        public void testTakeCard(){
            Collections.addAll(playerDeck.deck, 1,3,5,6);
            
            int headCard = playerDeck.deck.get(0);
            int card = playerDeck.takeCard();
            int newHead = playerDeck.deck.get(0);
            
            assertEquals(headCard,card);
            assertEquals(3,newHead);
            assertEquals(3,playerDeck.deck.size());
            
        }
        
        /**
         * Test of takeCard method, of inner class Deck.
         * Test the card is picked from the head of the deck and removed
         */
        @Test
        public void testAddCardToDeck(){
            Collections.addAll(playerDeck.deck, 1,3,5,6);
            
            playerDeck.addCardToDeck(5);
            
            int lastTailCard = playerDeck.deck.get(4);
            
            assertEquals(5,playerDeck.deck.size());
            assertEquals(5, lastTailCard);
        }
        
    }
    
}
