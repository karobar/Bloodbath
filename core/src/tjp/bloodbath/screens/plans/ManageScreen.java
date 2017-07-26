package tjp.bloodbath.screens.plans;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.Card;
import tjp.bloodbath.game.Deck;
import tjp.bloodbath.game.Save;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.LongList;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class ManageScreen extends AbstractPlanScreen {     
    private final LongList<Card> cardList;
    
    public ManageScreen(BitmapContext bitmapContext, ScreenContext screenContext, Save save) {
        super(bitmapContext, screenContext, save);
        
        ensureSavedHandExists();
        
        Collection<Card> hand = getSave().getSavedHand();
        
        cardList = LongList.<Card>newLongListBuilder()
                .bitmapContext(bitmapContext)
                .inactiveColor(Color.WHITE).activeColor(Color.RED)
                .centered().y(9)
                .build();
        
        for (Card card : hand) {
            cardList.add(new GUItext(card.toString()), card);
        }
        addGUIelement(cardList);
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
                cardList.cycleUp();
                break;
            case Keys.DOWN:
                cardList.cycleDown();
                break;
            case Keys.ENTER:
                handleSelection();
                break;
            case Keys.ESCAPE:
                stepScreenBackwards();
                break;
        }  
    }
    
    private void ensureSavedHandExists() {
        if (getSave().getSavedHand() == null) {
            Deck deck = checkNotNull(getSave().getMainCharacter().getDeck());
            Collection<Card> hand = deck.draw(HAND_SIZE);
            getSave().setSavedHand(hand);
            deck.reconstitute();
        } 
    }
    
    private void handleSelection() {
//        if (mainMenuChoices.getCurrentChoice().equals(EXIT)) {
//            stepScreenBackwards();
//        }
    }
    
    private static final int HAND_SIZE = 5;
    
    @Override
    public void stepToScreenTrigger() {
        ensureSavedHandExists();
    }
    
    @Override
    protected void outOfTimeTrigger() { }
}