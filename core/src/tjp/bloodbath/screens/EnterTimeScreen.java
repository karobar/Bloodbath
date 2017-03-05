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

/**
 * A screen which is accessible from the title screen which allows for 
 * modifying program options (such as window size).
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class EnterTimeScreen extends Screen { 
    
    public EnterTimeScreen(BitmapContext graphicsContext, ScreenContext screenContext) {
        super(graphicsContext, screenContext);
        
        ScreenTextList setScene = 
                ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .centered()
                .y(5)
                .build();
        
        setScene.add(new GUItext("You take a short drive up to this week's parking lot."));
        setScene.add(new GUItext("Your contact is already parked, waiting."));
        setScene.add(new GUItext("You both roll down your windows."));
        setScene.add(new GUItext("Even at this distance you can smell his hops-scented breath as he asks you:"));
        addGUIelement(setScene);
        
        ScreenTextList response = ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.LIGHT_BLUE)
                .centered()
                .y(10)
                .initialItem("What have you learned about these fuckers?")
                .build();
        addGUIelement(response);
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
                break;
            case Keys.ENTER:
                stepScreenBackwards();
                break;
        } 
    }

    @Override
    protected void handleFrameChange() { }
}