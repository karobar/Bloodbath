package tjp.bloodbath.screens.plans;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.Save;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.gui.Timer;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class PlanScreen extends AbstractPlanScreen { 
    ScreenTextList mainMenuChoices;
    
    private static final GUItext VIEW_INTEL = new GUItext("VIEW INTEL");
    private static final GUItext EXIT = new GUItext("EXIT");
    
    public PlanScreen(BitmapContext bitmapContext, ScreenContext screenContext, Save save) {
        super(bitmapContext, screenContext, save);
        
        mainMenuChoices = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .inactiveColor(Color.WHITE).activeColor(Color.RED)
                .centered().y(11)
                .build();
        mainMenuChoices.add(VIEW_INTEL);
        mainMenuChoices.add(EXIT);
        addGUIelement(mainMenuChoices);
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
                mainMenuChoices.cycleUp();
                break;
            case Keys.DOWN:
                mainMenuChoices.cycleDown();
                break;
            case Keys.ENTER:
                handleSelection();
                break;
        }  
    }
    
    private void handleSelection() {
        if (mainMenuChoices.getCurrentChoice().equals(EXIT)) {
            stepScreenBackwards();
        } else if (mainMenuChoices.getCurrentChoice().equals(VIEW_INTEL)) {
            stepScreenForwards(
                    new ViewIntelScreen(getBitmapContext(), getScreenContext(), getSave()));
        } 
    }
    
    @Override
    protected void outOfTimeTrigger() { }
}