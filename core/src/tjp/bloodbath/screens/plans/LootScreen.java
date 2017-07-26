package tjp.bloodbath.screens.plans;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.Card;
import tjp.bloodbath.game.Deck;
import tjp.bloodbath.game.GameCharacter;
import tjp.bloodbath.game.Save;
import tjp.bloodbath.game.Card.CardType;
import tjp.bloodbath.game.Card.StrengthAspect;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.LongList;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class LootScreen extends AbstractPlanScreen {    
    private static int HAND_SIZE = 5;
    LongList<Card> lootChoices;
    
    public LootScreen(BitmapContext graphicsContext, ScreenContext screenContext, Save save) {

        super(graphicsContext, screenContext, save);
        
        Collection<Card> hand = save.getSavedHand();
        
        GameCharacter mainCharacter = save.getMainCharacter();
        if (hand == null) {
            Deck deck = checkNotNull(mainCharacter.getDeck());
            hand = deck.draw(HAND_SIZE);
            deck.reconstitute();
        }
        
        GameCharacter target = new GameCharacter("Unknown", 7);
        target.setTitle("Feral Vampire");
        boolean namedChar = false;
        
        GameCharacter savedTarget = save.getTarget();
        if (savedTarget != null) {
            target = savedTarget;
            namedChar = true;
        }   
        
        if (checkForInstantDeath(hand)) {
            save.permadeath();
            System.exit(0);
        }
        
        ScreenTextList tutText = ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .centered()
                .y(11)
                .build();
        addGUIelement(tutText);
        
        lootChoices = LongList.<Card>newLongListBuilder()
                .bitmapContext(graphicsContext)
                .inactiveColor(Color.WHITE).activeColor(Color.RED)
                .centered().y(18)
                .build();

        Collection<Card> rivalDeck = target.getDeck().draw(HAND_SIZE);
        target.getDeck().reconstitute();
        if (winBattle(hand, rivalDeck)) {
            tutText.add(new GUItext(target.getFullName() + ", " + target.getTitle() + ", "
                    + "has been put to rest."));
            save.killCharacter(namedChar, target);
            addLoot(3, target, lootChoices);
        } else {
            tutText.add(new GUItext(target.getFullName() + ", " + target.getTitle() + ","  +
                    "has wounded you during your hunt and got away."));
            addLoot(2, target, lootChoices);
            target.getDeck().reconstitute();
            mainCharacter.addCard(new Card(CardType.WOUND, "wound"));
        }
        addGUIelement(lootChoices);
        
        Deck deck = checkNotNull(getSave().getMainCharacter().getDeck());
        Collection<Card> newHand = deck.draw(HAND_SIZE);
        getSave().setSavedHand(newHand);
        deck.reconstitute();
    }
    
    private void addLoot(int numCards, GameCharacter target, LongList<Card> lootChoices) {
        Collection<Card> cards = target.getDeck().draw(numCards);
        for(Card card : cards) {
            try {
                lootChoices.add(new GUItext(card.toString()), card.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            } 
        }
    }
    
    private boolean checkForInstantDeath(Collection<Card> hand) {
        int numberOfMortalWounds = 0;
        int numWounds = 0;
        for(Card card : hand) {
            if (card.getType() == Card.CardType.SERIOUS_WOUND) {
                    numberOfMortalWounds++;
                    numWounds++;
            } else if (card.getType() == Card.CardType.WOUND) {
                    numWounds++;
            } 
        }
        return numberOfMortalWounds > 1 || numWounds == hand.size();
    }
    
    private boolean winBattle(Collection<Card> playerHand, Collection<Card> rivalHand) {
        double versusBat = 0;
        double versusBlood = 0;
        double versusMist = 0;
        double versusWolf = 0;
        
        for(Card card : playerHand) {
            if (card.getType() == Card.CardType.STRENGTH) {
                if (card.getAspect() == StrengthAspect.BAT) {
                    versusBat += card.getFactor();
                    versusBlood += card.getFactor() * 3;
                    versusMist += card.getFactor();
                    versusWolf += 0.5 * card.getFactor();
                } else if (card.getAspect() == StrengthAspect.BLOOD) {
                    versusBat += card.getFactor() * 0.5;
                    versusBlood += card.getFactor();
                    versusMist += card.getFactor() * 3;
                    versusWolf += card.getFactor();
                } else if (card.getAspect() == StrengthAspect.MIST) {
                    versusBat += card.getFactor() ;
                    versusBlood += card.getFactor() * 0.5;
                    versusMist += card.getFactor() ;
                    versusWolf += card.getFactor() * 3;
                } else if (card.getAspect() == StrengthAspect.WOLF) {
                    versusBat += card.getFactor() * 3;
                    versusBlood += card.getFactor();
                    versusMist += card.getFactor() * 0.5;
                    versusWolf += card.getFactor();
                } else { 
                    versusBat += card.getFactor();
                    versusBlood += card.getFactor();
                    versusMist += card.getFactor();
                    versusWolf += card.getFactor();
                }
            }
        }
        
        for(Card card : rivalHand) {
            if (card.getType() == Card.CardType.STRENGTH) {
                if (card.getAspect() == StrengthAspect.BAT) {
                    versusBat -= card.getFactor();
                    versusBlood -= card.getFactor() * 3;
                    versusMist -= card.getFactor();
                    versusWolf -= card.getFactor() * 0.5;
                } else if (card.getAspect() == StrengthAspect.BLOOD) {
                    versusBat -= card.getFactor() * 0.5;
                    versusBlood -= card.getFactor();
                    versusMist -= card.getFactor() * 3;
                    versusWolf -= card.getFactor();
                } else if (card.getAspect() == StrengthAspect.MIST) {
                    versusBat -= card.getFactor() ;
                    versusBlood -= card.getFactor() * 0.5;
                    versusMist -= card.getFactor() ;
                    versusWolf -= card.getFactor() * 3;
                } else if (card.getAspect() == StrengthAspect.WOLF) {
                    versusBat -= card.getFactor() * 3;
                    versusBlood -= card.getFactor();
                    versusMist -= card.getFactor() * 0.5;
                    versusWolf -= card.getFactor();
                } else { 
                    versusBat -= card.getFactor();
                    versusBlood -= card.getFactor();
                    versusMist -= card.getFactor();
                    versusWolf -= card.getFactor();
                }
            }
        }
        
        return versusBat + versusBlood + versusMist + versusWolf >= 0;
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        switch(event.getIntCode()) {
            case Keys.UP:
                lootChoices.cycleUp();
                break;
            case Keys.DOWN:
                lootChoices.cycleDown();
                break;
            case Keys.ESCAPE:
                selectAndGo();
                break;
            case Keys.ENTER:
                selectAndGo();
                break;
        } 
    }
    
    private void selectAndGo() {
        getSave().getMainCharacter().addCard(lootChoices.getCurrentElement());
        shadowStepForwards(
                new PlanScreen(getBitmapContext(), getScreenContext(), getSave()));
    }

    @Override
    protected void outOfTimeTrigger() { }
}