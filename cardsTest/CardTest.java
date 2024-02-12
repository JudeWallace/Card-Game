/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cardgame;

import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author judewallace
 */
public class CardTest {
    
    int playerNumber = 1;
    Card cards = new Card();
    CardDeck deck = new CardDeck();
 
    @Before
    public void setUp() {
        // Set the deck to have values 
        Collections.addAll(deck.fullDeck, 1,3,4,
                1,5,4,2,1,1,
                1,1);
    }
    
    @After
    public void tearDown() {
        cards.playersCards.removeAll(cards.playersCards);
        deck.fullDeck.removeAll(deck.fullDeck);
    }

    /**
     * Test of pickCard method, of class Card.
     */
    @Test
    public void testPickCardGoesIntoCards() {
        cards.pickCard(deck);
        assertEquals(1, cards.playersCards.size());
    }

    /**
     * Test of PickCards method, of class Card.
     * Test cards in players hand is of correct length
     */
    @Test
    public void testPickCardCorrectLength() {
        for(int i = 0; i < 4; i++){
            cards.pickCard(deck);
        }
        assertEquals(4, cards.playersCards.size());
    }
    
    /**
     * Test of PickCards method, of class Card.
     * If the player has the limit of cards in hand don't allow more to be added/picked up
     */
    @Test
    public void testPickCardTooManyCardsRequested() {
        for(int i = 0; i < 7; i++){
            cards.pickCard(deck);
        }
        assertEquals(4, cards.playersCards.size());
    }
    
    /**
     * Test of getPlayersCards method, of class Card.
     * Check the players card can be written as a string in the correct formats
     */
    @Test
    public void getPlayersCardsTest(){
        Collections.addAll(cards.playersCards, 4,2,6,0);
        String message = cards.getPlayersCards(playerNumber, "initial");
        String expected = "player 1 initial hand 4 2 6 0";
        assertEquals(expected,message);
    }
    /**
     * Test of changeRandom method, of class Card.
     * check random card is changed, as long as of non-preferred denomination
     */
    @Test
    public void testChangeRandomCard(){
       Collections.addAll(cards.playersCards, 4,2,6,0);
       
       int returnedCard = cards.comparePickedCard(5, playerNumber);
       
       assertTrue(cards.playersCards.contains(5));
       assertFalse(cards.playersCards.contains(returnedCard));
    }
    
    /**
     * Test of changeRandom method, of class Card.
     * check random card never changes preferred denominations
     */
    @Test
    public void testChangeRandomCardNeverChangePrefferedDemominations(){
       Collections.addAll(cards.playersCards, 4,2,3,1);
      
       for(int i = 0; i < 100; i++){
            cards.comparePickedCard(i+5, playerNumber);
       }
      
       assertTrue(cards.playersCards.contains(playerNumber));
    }
    
    /**
     * Test of comparePickedCard method, of class Card.
     * No replaces to be made just discard drawn card
     * [X,X,X,N], Z --> [X,X,X,N], Z
     */
    @Test
    public void testComparePickedCardThreeCardsNoChanges(){
       Collections.addAll(cards.playersCards, 3,3,3,1);
       
       int returnedCard = cards.comparePickedCard(5, playerNumber);
       
       List<Integer> expected = asList(3,3,3,1);
       
       assertTrue(cards.playersCards.equals(expected));
       assertEquals(5,returnedCard);
    }
    
    /**
     * Test of comparePickedCard method, of class Card.
     * Hand to replace Y with X if X is the most common card
     * [X,X,X,Y], X --> [X,X,X,X], Y
     */
    @Test
    public void testComparePickedCardThreeCardsAndDrawnCardTheSame(){
       Collections.addAll(cards.playersCards, 1,1,1,0);
       
       int returnedCard = cards.comparePickedCard(1, playerNumber);
       
       List<Integer> expected = asList(1,1,1,1);
       
       assertTrue(cards.playersCards.equals(expected));
       assertEquals(0,returnedCard);
    }
    
    /**
     * Test of comparePickedCard method, of class Card.
     * Hand to change random value when player number is drawn
     * [X,X,X,N], N --> [X,X,N,N], X
     * where N is the player number
     */
    @Test
    public void testComparePickedCardThreeCardsAndDrawnCardPlayerNumber(){
       Collections.addAll(cards.playersCards, 2,2,2,1);
       
       int returnedCard = cards.comparePickedCard(1, playerNumber);

       List<Integer> expected = asList(2,1,1,1);
 
       assertTrue(cards.playersCards.containsAll(expected));
       assertEquals(2,returnedCard);
    }
    
    /**
     * Test of comparePickedCard method, of class Card.
     * [X,X,Y,Y], X --> [X,X,X,Y], Y
     */
    @Test
    public void testComparePickedCardTwoPairs(){
       Collections.addAll(cards.playersCards, 2,2,4,4);
       
       int returnedCard = cards.comparePickedCard(4, playerNumber);

       List<Integer> expected = asList(2,4,4,4);
 
       assertTrue(cards.playersCards.containsAll(expected));
       assertEquals(2,returnedCard);
    }
    
    /**
     * Test of comparePickedCard method, of class Card.
     * [X,X,Y,Y],N --> [X/Y,X/Y,X/Y,N], X/Y
     */
    @Test
    public void testComparePickedCardTwoPairsAndPlayerNumberDrawn(){
       Collections.addAll(cards.playersCards, 2,2,4,4);
       
       int returnedCard = cards.comparePickedCard(playerNumber, playerNumber);
       boolean matches = returnedCard == 2 || returnedCard == 4;
       
       assertTrue(cards.playersCards.contains(playerNumber));
       assertTrue(matches);
       
    }
    
     /**
     * Test of comparePickedCard method, of class Card.
     * [X,X,Y,Y],Z --> [X,X,Y,Y], Z
     * iff X,Y,Z != player number
     */
    @Test
    public void testComparePickedCardTwoPairsAndDrawnNoMatches(){
       Collections.addAll(cards.playersCards, 3,3,4,4);
       
       int returnedCard = cards.comparePickedCard(2, playerNumber);
      
       assertEquals(2,returnedCard);
       
    }
    /**
     * Test of checkForWinningHand method, of class Card. When winning hand is present
     * [X,X,X,X] --> True
     */
    @Test
    public void testCheckForWinningHand_True() {
        Collections.addAll(cards.playersCards, 1,1,1,1);
        assertTrue(cards.checkForWinningHand());
    }
    
    /**
     * Test of checkForWinningHand method, of class Card. When no winning hand is present
     * [X,Y,A,B] --> False
     */
    @Test
    public void testCheckForWinningHand_False(){
        Collections.addAll(cards.playersCards, 1,2,1,1);
        assertFalse(cards.checkForWinningHand());
    }      
    
}
