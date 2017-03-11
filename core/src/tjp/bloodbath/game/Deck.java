package tjp.bloodbath.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    
    @Override
    public String toString() {
        return deck.toString();
    }
    
    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public Deck(List<Card> cards) {
        this.deck = cards;
    }

    public boolean add(Card e) {
        return deck.add(e);
    }
    
    public Card draw() {
        return deck.get(0);
    }

    public Collection<Card> draw(int num) {
        Collection<Card> cards = new HashSet<Card>();
        for (int i = 0; i < num; i++) {
            cards.add(draw());
        }
        return cards;
    }
    
    public List<Card> getDeck() {
        return deck;
    }
    
    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
    
    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed)); 
    }
}
