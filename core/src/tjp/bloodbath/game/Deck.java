package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.HashMultiset;

public class Deck {
    private List<Card> deck;
    private final List<Card> discard;
    
    @Override
    public String toString() {
        return deck.toString();
    }
    
    public void reconstitute() {
        deck.addAll(discard);
    }
    
    public Deck() {
        this.deck = new ArrayList<Card>();
        this.discard = new ArrayList<Card>();
    }

    public Deck(List<Card> cards) {
        this.deck = checkNotNull(cards);
        this.discard = new ArrayList<Card>();
    }

    public boolean add(Card e) {
        return deck.add(e);
    }
    
    public Card draw() {
        if (deck.isEmpty()) {
            reconstitute();
        }
        Card retVal = deck.remove(deck.size() - 1);
        discard.add(retVal);
        return retVal;
    }

    public Collection<Card> draw(int num) {
        shuffle();
        Collection<Card> cards = HashMultiset.create();
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
