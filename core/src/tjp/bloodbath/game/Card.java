package tjp.bloodbath.game;

import java.util.concurrent.ThreadLocalRandom;

public class Card implements Cloneable {
    public enum CardType {
        STRENGTH("strength"),
        MONEY("money"),
        SERIOUS_WOUND("serious wound"), 
        WOUND("wound");
        
        private CardType(String name) {
            this.typeName = name;
        }
        
        private String typeName;
        
        protected String getName()  {
            return typeName;
        }
    }
    
    private String name;
    private CardType type;
    
    private int factor;
    
    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    private static final int CARD_DIVISIONS = 3;

    Card() { }
    
    Card(int level) {
        int divisions = ThreadLocalRandom.current().nextInt(1, CARD_DIVISIONS + 1);
        switch (divisions) {
            case 1:
            case 2:
                type = CardType.STRENGTH;
                break;
            case 3:
                type = CardType.MONEY;
                break;
        }
        
        int fuzzer = ThreadLocalRandom.current().nextInt(1, 4);
        factor = (8 - level) * 3 + (2 - fuzzer);
    }
    
    public Card(CardType type) {
        this.type = type;
        this.name = type.name();
    }

    public Card(CardType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Card clone() throws CloneNotSupportedException {
        return (Card) super.clone();
    }
    
    public String getName() {
        return name;
    }
    
    public CardType getType() {
        return type;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.typeName + " (" + factor +")";
    }
}

