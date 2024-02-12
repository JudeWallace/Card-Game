/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cardgame;

import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author judewallace
 * @author williamsee
 */
class CardGame {
    
    static CardDeck mainDeck = new CardDeck();
    static Scanner input = new Scanner(System.in);
    
    static void disrtibuteCards(Player[] players, int numberOfPlayers){
        //Create an object Card/Deck for each player
        for(Player p3: players){
                p3.createCardObject();
                p3.createPlayersDeckObject(mainDeck);
        }
        //Deal each player 4 cards in sequence
        int totalCardsDelt = 0;
        
        while(totalCardsDelt < 4){
            for(Player p1: players){
                p1.setupPlayerCards(mainDeck);
            }
            totalCardsDelt++;
            
        }
        for(Player p5: players){
                p5.setDeckToRight();
                p5.setPlayersDeck(numberOfPlayers);
        }
    }
    
    static Integer getNumberOfPlayers(){
        System.out.println("Please enter the number of players: ");
        int numberOfPlayers = 0;
        boolean flag = true;
        while(flag){
            try{    
                numberOfPlayers = Integer.valueOf(input.nextLine());
                if (numberOfPlayers <= 0){
                    System.out.println("Input is not a positive Integer ");
                    System.out.println("Please enter the number of players: ");
                }else{
                    flag = false;
                }
            }catch(NumberFormatException e){
                System.out.println("Input is not a positive Integer ");
                System.out.println("Please enter the number of players: ");
                
            }   
        }
        return numberOfPlayers;
    }
    
    static void getPackFileAndLoadIt(int numberOfPlayers){
        System.out.println("Please enter location of pack to load: ");
        String file;
        
        boolean c = true;
        while(c){
            try{
                file = input.nextLine();
                //CardDeck mainDeck = new CardDeck();
                mainDeck.setCardDeck(file, numberOfPlayers);
                if(mainDeck.fullDeck.size() != numberOfPlayers*8){
                    System.out.println("Pack isn't of correct size");
                    mainDeck.incorrectDeck();
                    System.out.println("Please enter location of pack to load: ");
                }
                else{
                    c = false;
                }
            }catch(FileNotFoundException ex){
                System.out.println("Pack not found");
                System.out.println("Please enter location of pack to load: ");
            }catch(NumberFormatException e){
                System.out.println("Pack contains non-integer values");
                System.out.println("Please enter location of pack to load: ");
            }
        }
    }
    
    public static void main(String[] args) {
        
        int numberOfPlayers = getNumberOfPlayers();
        
        getPackFileAndLoadIt(numberOfPlayers);
         
        // create player objects
        Player players[] = new Player[numberOfPlayers];
        for(Player p: players){
            p = new Player();
        }
        
        for(int i = 0; i < players.length; i++){
            players[i] = new Player();
            Player player = players[i];
            player.setnumberPlayer(i+1);
        }
        
        disrtibuteCards(players,numberOfPlayers);
        
        //Start and run threads for each players
        for(Player thread: players){
           Thread object = new Thread(thread);
           object.start();
        }
    }  
}


