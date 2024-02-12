package cardgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author judewallace
 */
public class Player implements Runnable {
    
    static boolean playerWon = false;
    static String whoWon;
    private volatile boolean exit = false;
    
    static ArrayList<CardDeck.Deck> allDecks = new ArrayList<CardDeck.Deck>();
    
    int numberPlayer;
    Card playersCards;
    CardDeck.Deck playersDeck;
    CardDeck.Deck deckToRight;
    
    String file;
    
    public void createPlayersDeckObject(CardDeck d){
        // Create a deck for the player
        CardDeck.Deck deck = d.new Deck();
        deck.setDeckNumber(numberPlayer);
        playersDeck = deck;
        allDecks.add(deck);
    }
    
    public void createCardObject(){
        // Create players card object to store there hand in the game
        Card playersCard = new Card();
        playersCards = playersCard; 
    }
    
    public void setnumberPlayer(int n){
        this.numberPlayer = n;
    }
    
    public void setupPlayerCards(CardDeck deck){
        playersCards.pickCard(deck);
    }
    
    public void setPlayersDeck(int numberOfPlayers){
        // create inner class object
        playersDeck.setDeck(numberOfPlayers);
    }
    
    public void createFile(String file){
        try{
            File playerFile = new File(file);
            if(playerFile.createNewFile()){
                // DoNothing
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void appenedToFile(String content, String filename){
        // Append correct message to players output file
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
          
    public String toString(){

        return "player " + numberPlayer;
    }
    
    public void setDeckToRight(){
        // Get the deck object of the deck to the players right
        if(numberPlayer > allDecks.size() - 1){
            deckToRight = allDecks.get(0);
        }else{
            deckToRight = allDecks.get(numberPlayer);
        }
    }
    
    public synchronized void checkWin(){
        // synchronized method to change the class memeber values
        if(playerWon){
            return;
        }
        
        whoWon = toString();
        playerWon = true;
         
        try {
            Thread.sleep(1000);
            // appened the correct message to the end of the players output file
            System.out.println(toString() + " wins");
            appenedToFile("player " + numberPlayer + " wins", file);
            appenedToFile("player " + numberPlayer + " exits", file);
            appenedToFile(playersCards.getPlayersCards(
                                numberPlayer,"final"), file);
            
            // Write the final deck content at the end of the game 
            playersDeck.finalDeckToFile();
            
        } catch (InterruptedException ex) {ex.printStackTrace();}
   }
    
    @Override
    public void run() {
        file = "player"+ numberPlayer + "_output.txt";
        createFile(file);
        
        String initialMessage = playersCards.getPlayersCards(numberPlayer,"initial");
        appenedToFile(initialMessage, file);
        // Start the game loop
        while(!exit){
            // Check if the Player has won 
            if(playerWon){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
          
                    }
                    String informMessage = whoWon + " has informed " + toString()
                            + " that " + whoWon+ " has won"; 
                    appenedToFile(informMessage, file);
                    appenedToFile("player " + numberPlayer + " exits", file);
                    appenedToFile(playersCards.getPlayersCards(
                            numberPlayer,"final"), file);
                    
                    playersDeck.finalDeckToFile();
                    // Stop the thread
                    exit = true;
                    
            }else{
                // Check if the player has a winning hand
                if(playersCards.checkForWinningHand() && playerWon == false){
                    checkWin();
                    exit = true;
                }
                // Check if deck has cards to take from
                else if(playersDeck.deck.isEmpty()){
                     //If false, restart loop
                    continue;
                }
                else{
                    // Pick up card from the 
                    int drawnCard = playersDeck.takeCard();

                    String drawCardMessage = "player " + numberPlayer + " draws a " 
                                + drawnCard + " from deck " + numberPlayer;

                    int x = playersCards.comparePickedCard(drawnCard, numberPlayer);
           
                    deckToRight.addCardToDeck(x);

                    String discardMessage = "player " + numberPlayer + " discards a " 
                            + x + " to deck " + deckToRight.deckNumber;
                    // Add messages for drawn card and the card being discarded
                    // to the respetive files
                    appenedToFile(drawCardMessage, file);
                    appenedToFile(discardMessage, file);
                }
                    
            }
        }
               
    }
 
}
