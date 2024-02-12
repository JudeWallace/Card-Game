/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author judewallace
 */
public class CardDeck {
    
    ArrayList<Integer> fullDeck = new ArrayList<Integer>();
    
    public void setCardDeck(String file, int numberOfPlayers) throws FileNotFoundException{
            // Read the inputted pack from user enter path
            File f = new File(file);
            Scanner s = new Scanner(f);

            int ctr = 0;
            
            while(s.hasNextInt())
            {
             ctr++;
             s.nextInt();
            };

            int[] arr = new int[ctr];

            Scanner s1 = new Scanner(f);

            for (int i = 0; i < arr.length; i++)
                arr[i] = s1.nextInt();

            for(int card: arr){
                // Add the cards in the pack to the games main deck
                fullDeck.add(card);
            }  
    }
    
    public void incorrectDeck(){
        fullDeck.removeAll(fullDeck);
    }
    
    public Integer dealCard(){
        if(fullDeck.isEmpty()){
            return -1;
        }
        // Get the first card in the fullDeck
        int card = fullDeck.get(0);
        // Pop card off the deck
        fullDeck.remove(0);
        
        return card;
    }
    
    class Deck {
        
        volatile ArrayList<Integer> deck = new ArrayList<Integer>(); // kept upto date and doesnt return cached version
        int deckNumber;

        public void setDeck(int n){
            // Fill in the deck with the reamining cards
            // take n cards from the remaining deck
            n = ((8*n) - 4*n)/n;
            
            for(int i = 0; i < n; i++){
                int cards = dealCard();
                if(cards == -1){
                    continue;
                }else{
                deck.add(cards);
                }   
            }
        }
        
        public void setDeckNumber(int deckNumber){
            this.deckNumber = deckNumber;
        }
            
        public void finalDeckToFile(){
            // Create a deck file and print its remaining values to it
            String filename = "deck" + deckNumber + "_output.txt";
            String finalDeck = Arrays.toString(deck.toArray())
                .replace("[", " ")
                .replace("]", "").replace(",", "");
            String content = "deck" + deckNumber + " contains" + finalDeck;
            
            try{
                // Create new file
                File file = new File(filename);

                // If file doesn't exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                // Write in file
                bw.write(content);

                // Close connection
                bw.close();
            }
            catch(Exception e){
            }
        }
        
        public synchronized Integer takeCard(){
            // Take card off the top of the deck and return it back to the player
            int card = deck.get(0);
            deck.remove(0);
            
            return card;
        }
        
        public synchronized void addCardToDeck(int card){
            // Add card that has been discarded to the bottom of the deck
            deck.add(card);
            
        }      
    }
}
