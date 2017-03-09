package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

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

public class InfoScreen extends Screen { 
    
    public InfoScreen(BitmapContext graphicsContext, ScreenContext screenContext, String line) {
        super(graphicsContext, screenContext);
        
        ScreenTextList tutText = ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .centered()
                .y(12)
                .build();
        
        tutText.add(new GUItext(line));

        addGUIelement(tutText);
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
                stepScreenBackwards();
                stepScreenBackwards();
                break;
            case Keys.ENTER:
                stepScreenBackwards();
                stepScreenBackwards();
                break;
        } 
    }

    @Override
    protected void handleFrameChange() { }

    @Override
    public void stepToScreenTrigger() {}
}