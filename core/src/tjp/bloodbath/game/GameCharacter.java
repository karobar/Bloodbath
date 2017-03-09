package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameCharacter {
    private int age;
    private List<Card> deck;
    private String firstName;
    private String lastName;
    
    private int level;

    private String title;

    //for javaBeans
    public GameCharacter() { 
        this.deck = new ArrayList<Card>();
    }

    public GameCharacter(int level, String firstName, String lastName, String title, int age) {
        this.deck = new ArrayList<Card>(Arrays.asList(Card.GOOD, Card.GOOD, Card.GOOD, Card.GOOD,
                Card.GOOD, Card.GOOD, Card.GOOD));
        this.firstName = checkNotNull(firstName);
        this.lastName = checkNotNull(lastName);
        this.title = checkNotNull(title);
        this.age = age;
    }

    public GameCharacter(String name) {
        this.deck = new ArrayList<Card>(Arrays.asList(Card.GOOD, Card.GOOD, Card.GOOD, Card.GOOD,
                Card.GOOD, Card.GOOD, Card.GOOD));
        this.firstName = checkNotNull(name);
    }
    
    public GameCharacter(String name, List<Card> deck) {
        this.deck = deck;
        this.firstName = checkNotNull(name);
    } 
    
    public void addCard(Card card) {
        deck.add(card);
    }
    
    public int getAge() {
        return age;
    }
    
    public List<Card> getDeck() {
        return this.deck;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getLevel() {
        return level;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    //for bean
    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
    
    //for javaBeans
    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    public void setLastName(String name) {
        this.lastName = name;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
