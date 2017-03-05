package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.CellDimensions;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.AncillaryGUIText;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
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
    ScreenTextList mainMenuChoices;
    
    private static final GUItext NEW_PLAYER = new GUItext("NEW HUNTER");
    private static final AncillaryGUIText HUNT = new AncillaryGUIText("HUNT");
    private static final AncillaryGUIText PLAN = new AncillaryGUIText("PLAN");
    private static final AncillaryGUIText ENTER_TIME = new AncillaryGUIText("REPORT");
    private static final AncillaryGUIText TIMER = new AncillaryGUIText("0:00");
    private static final AncillaryGUIText NEWLINE = new AncillaryGUIText("");
    
    private static final GUItext TUTORIAL = new GUItext("TUTORIAL");
    private static final GUItext OPTIONS   = new GUItext("OPTIONS");
    private static final GUItext EXIT_GAME = new GUItext("EXIT");
    
    public TitleScreen(BitmapContext graphicsContext, ScreenContext screenContext) {
        super(graphicsContext, screenContext);
        
        addGUIelement(ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.RED)
                .initialItem("B L O O D B A T H")
                .centered().y(9)
                .build());
             
        mainMenuChoices = ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .inactiveColor(Color.WHITE).activeColor(Color.RED)
                .centered().y(11)
                .build();
        mainMenuChoices.add(NEW_PLAYER);
        mainMenuChoices.add(HUNT);
        mainMenuChoices.add(ENTER_TIME);
        mainMenuChoices.add(PLAN);
        mainMenuChoices.add(TIMER);
        mainMenuChoices.add(NEWLINE);
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
                mainMenuChoices.cycleDown();
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
        } else if (mainMenuChoices.getCurrentChoice().equals(ENTER_TIME)) {
            stepScreenForwards(new EnterTimeScreen(getBitmapContext(), getScreenContext()));
        }
    }

    @Override
    public void handleFrameChange() {
        
    }
}