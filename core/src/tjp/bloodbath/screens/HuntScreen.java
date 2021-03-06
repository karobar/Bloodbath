package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.Card;
import tjp.bloodbath.game.Save;
import tjp.bloodbath.screens.plans.LootScreen;
import tjp.bloodbath.screens.plans.PlanScreen;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.gui.Timer;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class HuntScreen extends Screen { 
    //private final static int HUNT_SECONDS = 1500;
    private final static int HUNT_SECONDS = 2;
    
    int secondsRemaining = HUNT_SECONDS;
    
    private final static int STARTING_FLEE_TIME = 10;
    
    int remainingFleeTime = STARTING_FLEE_TIME;
    
    private final Timer timer = new Timer("00:00", true);
    
    private final GUItext fleeTimer = new GUItext(getFleeText());
    
    private Color backgroundColor = Color.BLACK;
    
    private Save save;
    
    private String getFleeText() {
        String retval = "FLEE";
        if (remainingFleeTime > 0) {
            return retval + " (" + remainingFleeTime + ")";
        } else {
            return retval;
        }
    }
    
    public HuntScreen(BitmapContext bitmapContext, ScreenContext screenContext, Save save) {
        super(bitmapContext, screenContext);
        this.save = save;
               
        addGUIelement(ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .color(Color.WHITE)
                .initialItem("Hunting...")
                .y(12)
                .build());
        
        ScreenTextList textList = ScreenTextList.newBuilder()
            .bitmapContext(bitmapContext)
            .centered()
            .y(13)
            .build();
        textList.add(timer);
        addGUIelement(textList);
        
        ScreenTextList flee = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .color(Color.BLUE)
                .y(15)
                .build();
        flee.add(fleeTimer);
        addGUIelement(flee);
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        float percent = (float) (HUNT_SECONDS - secondsRemaining) / (float) HUNT_SECONDS;
        int rows = Math.round(percent * 25f);
        if (25 - j < rows) {
            return new GraphicRepresentation(Color.BLACK, backgroundColor, Graphic.EMPTY_CELL,
                    getBitmapContext().getCharPixelWidth(),
                    getBitmapContext().getCharPixelHeight());
        } else {
            return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                    getBitmapContext().getCharPixelWidth(),
                    getBitmapContext().getCharPixelHeight());
        }
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        switch(event.getIntCode()) {
            case Keys.ENTER:
            case Keys.ESCAPE:
                if (remainingFleeTime <= 0) {
                    save.getMainCharacter().addCard(new Card(Card.CardType.WOUND));
                    stepScreenForwards(new InfoScreen(getBitmapContext(), getScreenContext(), 
                            "Your target dealt a wound during a botched retreat"));
                } else {
                    stepScreenBackwards(); 
                }
                break;
        } 
    }
    
    private Color computeBackgroundColor() {
        final float SCALE_FACTOR = 255;
        float percent = (float) (HUNT_SECONDS - secondsRemaining) / (float) HUNT_SECONDS;
        return new Color(Math.round(percent * SCALE_FACTOR), 0, 0);
    }

    @Override
    protected void handleFrameChange() {
        secondsRemaining--;
        timer.setTime(secondsRemaining);
        if (secondsRemaining < 1) {
            completedTimeTrigger();
        }
        backgroundColor = computeBackgroundColor(); 
        
        remainingFleeTime--;
        fleeTimer.setText(getFleeText());
    }
    
    private void completedTimeTrigger() {
        save.addTime(HUNT_SECONDS);
        shadowStepForwards(new LootScreen(getBitmapContext(), getScreenContext(), save));
    }

    @Override
    public void stepToScreenTrigger() {
        timer.setTime(secondsRemaining);
    }
}
