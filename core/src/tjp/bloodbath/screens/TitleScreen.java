package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.CellDimensions;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUIText;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextCollection;
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
public class TitleScreen extends Screen {    
    ScreenTextCollection mainMenuChoices;
    
    private static final GUIText TUTORIAL = new GUIText("TUTORIAL");
    private static final GUIText OPTIONS   = new GUIText("OPTIONS");
    private static final GUIText EXIT_GAME = new GUIText("EXIT");
    
    public TitleScreen(BitmapContext graphicsContext, ScreenContext screenContext) {
        super(graphicsContext, screenContext);
        
        addGUIelement(ScreenTextCollection.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.RED)
                .initialItem("B L O O D B A T H")
                .centered().y(9)
                .build());
             
        mainMenuChoices = ScreenTextCollection.newBuilder()
                .bitmapContext(graphicsContext)
                .inactiveColor(Color.GRAY).activeColor(Color.RED)
                .centered().y(11)
                .build();
        mainMenuChoices.add(TUTORIAL);
        mainMenuChoices.add(OPTIONS);
        mainMenuChoices.add(EXIT_GAME);
        addGUIelement(mainMenuChoices);
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
            case Keys.UP:
                mainMenuChoices.cycleUp();
                break;
            case Keys.DOWN:
                mainMenuChoices.cycleUp();
                break;
            case Keys.ENTER:
                handleSelection();
                break;
        } 
    }
    
    // on enter
    private void handleSelection() {     
        if (mainMenuChoices.getCurrentChoice().equals(TUTORIAL)) {
            stepScreenForwards(new TutorialScreen(getBitmapContext(), getScreenContext()));
        } else if (mainMenuChoices.getCurrentChoice().equals(OPTIONS)) {
            stepScreenForwards(new OptionsScreen(getBitmapContext(), getScreenContext()));
        } else if (mainMenuChoices.getCurrentChoice().equals(EXIT_GAME)) {
            System.exit(0);
        }
    }

    @Override
    public void handleFrameChange() {
        
    }
}