package tjp.bloodbath.screens.plans;

import java.util.Collection;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.Card;
import tjp.bloodbath.game.GameCharacter;
import tjp.bloodbath.game.Save;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.gui.TextList;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class LootScreen extends AbstractPlanScreen {    
    private static int HAND_SIZE = 5;
    
    
    public LootScreen(BitmapContext graphicsContext, ScreenContext screenContext, Save save) {

        super(graphicsContext, screenContext, save);
        
        Collection<Card> hand = save.getSavedHand();
        if (hand == null) {
            hand = save.getMainCharacter().getDeck().draw(HAND_SIZE);
        }
        
        GameCharacter target = new GameCharacter("Unknown");
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
        
        if (winBattle()) {
            tutText.add(new GUItext(target.getFullName() + ", " + target.getTitle() + ", "
                    + "has been put to rest."));
            save.killCharacter(namedChar, target);
        } else {
            tutText.add(new GUItext(target.getFullName() + ", " + target.getTitle() + ","  +
                    "has wounded you during your hunt and got away."));
        }

    }
    
    private boolean checkForInstantDeath(Collection<Card> hand) {
        int numberOfMortalWounds = 0;
        int numWounds = 0;
        for(Card card : hand) {
            switch(card) {
                case SERIOUS_WOUND:
                    numberOfMortalWounds++;
                    numWounds++;
                    break;
                case WOUND:
                    numWounds++;
                    break;
                default:
                    break;
            }
        }
        return numberOfMortalWounds > 1 || numWounds == hand.size();
    }
    
    private boolean winBattle() {
        return true;
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        switch(event.getIntCode()) {
            case Keys.ESCAPE:
                shadowStepForwards(
                        new PlanScreen(getBitmapContext(), getScreenContext(), getSave()));
                break;
            case Keys.ENTER:
                shadowStepForwards(
                        new PlanScreen(getBitmapContext(), getScreenContext(), getSave()));
                break;
        } 
    }
}