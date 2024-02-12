package cardgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Card {
    // Current cards of the player
    ArrayList<Integer> playersCards = new ArrayList<Integer>();
    
    public void pickCard(CardDeck deck){
        // Pick a card off the top of the deck and add it to players hand 
        if(playersCards.size() >= 4){
            return;
        }else{
            int cardPicked = deck.dealCard();
            
            if(cardPicked == -1){
                return;
            }else{
                playersCards.add(cardPicked);
            }
        }
    }
    
    public String getPlayersCards(int playerId, String messsage){
        // Return the cards in the players hand as a srting 
        String cards = Arrays.toString(playersCards.toArray())
                .replace("[", " ")
                .replace("]", "").replace(",", "");
        return "player "+ playerId + " " + messsage + " hand"+ cards;
    }
    
    public Integer changeRandomCard(int drawnCard, int playerNumber){
        int randomIndex = 0;

        while(true){
            // If the card is the players number, change to a different index card
            randomIndex = (int) (Math.random() * playersCards.size());
            if(playersCards.get(randomIndex) == playerNumber){
                continue;

            }else{
                break;
            }
        }
        // Returns the card at the index being swapped
        for(int j = 0; j < playersCards.size(); j++){
            if(j == randomIndex){
                int cardInHandToDrop = playersCards.get(j);
                playersCards.set(j, drawnCard);
                return cardInHandToDrop;
            }

        } 
        
        return drawnCard;
    }
    
    public Integer comparePickedCard(int drawnCard, int playerNumber){  
        // Create a hashmap to count the card frequency in players hand
        Map<Integer, Integer> countMap = new HashMap<>();

        for (Integer item: playersCards) {

            if (countMap.containsKey(item))
                countMap.put(item, countMap.get(item) + 1);
            else
                countMap.put(item, 1);
        }
        // Check if the player holds 3 of the same card
        if(countMap.containsValue(3)){
            int majorityCard = 0;
            
            for(Integer key : countMap.keySet()){
                if(countMap.get( key ).equals(3)){
                    majorityCard = key;
                    break;
                }
            }
            // If [3,3,3,1] draw a 1 -> [3,3,1,1] (if player num is 1)
            if(countMap.get(majorityCard) == 3 && 
                    playersCards.contains(playerNumber) && drawnCard == playerNumber){
                 return changeRandomCard(drawnCard, playerNumber);
            }
            // else keep [3,3,3,X] until better card comes
            for (int i = 0; i < playersCards.size(); i++){
                if(playersCards.get(i) != majorityCard){
                    if(playersCards.get(i) == playerNumber){
                        return drawnCard;
                    }else{
                        // Get the number stored at the index value
                        int cardInHand = playersCards.get(i);

                        // Change the card in that index to new element 
                        playersCards.set(i, drawnCard);

                        // Discard card
                        return cardInHand;
                    }
                }
            }          
            
        }
        // Check if player holds a pair of cards
        else if(countMap.containsValue(2)){     
            for (int j = 0; j < playersCards.size(); j++){
                    int card = playersCards.get(j);
                    if(countMap.get(card) == 2){
                        continue;
                    }else{
                        if(playersCards.get(j) == playerNumber){
                            continue;
                        }else{
                             int cardInHandToDrop = playersCards.get(j);
                             playersCards.set(j, drawnCard);

                             return cardInHandToDrop;
                       }
                }
            }
            // If the players hand looks like [X,X,Y,Y] check drawn card matches
            // X or Y
            // Change if drawn card is a preferred denomination
            if(playersCards.contains(drawnCard) == false && drawnCard == playerNumber){
                return changeRandomCard(drawnCard, playerNumber);
            }
            else if(playersCards.contains(drawnCard)){
                for (int j = 0; j < playersCards.size(); j++){
                    if(playersCards.get(j) == playerNumber){
                        continue;
                    }
                    else if(playersCards.get(j) != drawnCard){
                        int cardInHandToDrop = playersCards.get(j);
                        playersCards.set(j, drawnCard);

                        return cardInHandToDrop;
                    }
                }
            }
            // If doen't match X or Y return the drawn card to be discarded
            return drawnCard;
            
            
        }
        // Case for if each card is of unqiue denomination
        else{
            // Changes a random card in the players hand with the drawn card
            return changeRandomCard(drawnCard, playerNumber);
        }
        // Code should never reach this point
        return drawnCard;
    }
    
    public boolean checkForWinningHand(){
        // Check if all 4 cards in the hand are the same denomination
        boolean won = false;
        
        won = playersCards.stream().distinct().count() <=1;
        if(won){
            // If they all are the same the player has won, return trues
            return true;
        }
        
        return won;
    }
}
