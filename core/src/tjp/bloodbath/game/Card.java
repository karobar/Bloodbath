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
    
    public enum StrengthAspect {
        WOLF, BAT, MIST, BLOOD;
    }
    
    private String name;
    private CardType type;
    private StrengthAspect aspect;
    
    public StrengthAspect getAspect() {
        return aspect;
    }

    public void setAspect(StrengthAspect aspect) {
        this.aspect = aspect;
    }

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
                aspect = rollAspect();
                break;
            case 3:
                type = CardType.MONEY;
                break;
        }
        
        int fuzzer = ThreadLocalRandom.current().nextInt(1, 4);
        factor = (8 - level) * 3 + (2 - fuzzer);
    }
    
    private StrengthAspect rollAspect() {
        int roll = ThreadLocalRandom.current().nextInt(1, 5);
        switch (roll) {
            case 1:
                return StrengthAspect.WOLF;
            case 2:
                return StrengthAspect.BAT;
            case 3:
                return StrengthAspect.MIST;
            case 4:
                return StrengthAspect.BLOOD;
            default:
                return StrengthAspect.BAT;
        }
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
        String aspectStr = "";
        if (aspect != null) {
            aspectStr = aspect.toString() + " ";
        }
        return this.type.typeName + " (" + aspectStr + factor +")";
    }
}

