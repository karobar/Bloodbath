package tjp.bloodbath.game;

import com.badlogic.gdx.Input.Keys;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUIText;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenTextCollection;
import tjp.wiji.gui.TextCollection;
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
public class OptionsScreen extends Screen { 
    public OptionsScreen(BitmapContext graphicsContext) {
        super(graphicsContext);
        
        ScreenTextCollection placeholder = 
                new ScreenTextCollection(graphicsContext, TextCollection.DEFAULT_ACTIVE_COLOR, 37,0);
        placeholder.add(new GUIText("OPTIONS"));
        addGUIelement(placeholder);
    }
    
    @Override
    protected
    ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        switch(event.getIntCode()) {
            case Keys.ESCAPE:
                break;
            case Keys.PLUS:
                break;
            case Keys.MINUS:
                break;
            case Keys.T:
                break;
            case Keys.D:
                break;
            case Keys.ENTER:
                break;
        } 
    }

    @Override
    public void handleFrameChange() {
        
    }
}