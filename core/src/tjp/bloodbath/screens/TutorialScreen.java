package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUIText;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
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
public class TutorialScreen extends Screen { 
    private static String LINE1 = "This game is intended to complement the Pomodoro time management"
            + " technique.";
    private static String LINE2 = "Work for 25 minutes on whatever suits you,";
    private static String LINE3 = "while your character hunts down vampires.";
    private static String LINE4 = "Then take direct control while you play for 5 minutes.";
    private static String EMPTY = "";
    private static String LINE5 = "The ability to input work time directly has also been added in ";
    private static String LINE6 = "order to track time spent away from the computer (and for "
            + "testing).";
    
    public TutorialScreen(BitmapContext graphicsContext, ScreenContext screenContext) {
        super(graphicsContext, screenContext);
        
        ScreenTextCollection tutText = ScreenTextCollection.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .centered()
                .y(8)
                .build();
        
        tutText.add(new GUIText(LINE1));
        tutText.add(new GUIText(LINE2));
        tutText.add(new GUIText(LINE3));
        tutText.add(new GUIText(LINE4));
        tutText.add(new GUIText(EMPTY));
        tutText.add(new GUIText(LINE5));
        tutText.add(new GUIText(LINE6));
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
                break;
            case Keys.ENTER:
                stepScreenBackwards();
                break;
        } 
    }

    @Override
    protected void handleFrameChange() { }
}