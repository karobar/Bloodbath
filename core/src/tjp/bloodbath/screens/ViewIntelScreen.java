package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

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

public class ViewIntelScreen extends Screen { 
    public ViewIntelScreen(BitmapContext bitmapContext, ScreenContext screenContext, Timer timer) {
        super(bitmapContext, screenContext);
        
        ScreenTextList timerList = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .y(0)
                .build();
        timerList.add(timer);
        addGUIelement(timerList);
        
        ScreenTextList flee = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .color(Color.BLUE)
                .y(20)
                .build();
        flee.add(new GUItext("PICK AS NEXT TARGET"));
        addGUIelement(flee);
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        switch(event.getIntCode()) {
            case Keys.ENTER:
                stepScreenBackwards();
                break;
        
            case Keys.ESCAPE:
                stepScreenBackwards();
                break;
        } 
    }
    
    private void chooseAsNextTarget() {
        
    }

    @Override
    protected void handleFrameChange() { }

    @Override
    public void stepToScreenTrigger() {

    }
}
