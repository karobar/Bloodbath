package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.PlayerContext;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.CellDimensions;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
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
    private final PlayerContext playerContext;
    
    private final GUItext newPlayer = new GUItext("NEW HUNTER");
    private final GUItext hunt = new GUItext("HUNT", true);
    private final GUItext plan = new GUItext("PLAN", true);
    private final GUItext enterTime = new GUItext("REPORT", true);
    private static final GUItext TIMER = new GUItext("0:00", true);
    private static final GUItext NEWLINE = new GUItext("", true);
    
    private static final GUItext TUTORIAL = new GUItext("TUTORIAL");
    private static final GUItext OPTIONS   = new GUItext("OPTIONS");
    private static final GUItext EXIT_GAME = new GUItext("EXIT");
    
    public TitleScreen(BitmapContext graphicsContext, ScreenContext screenContext, 
            PlayerContext playerContext) {

        super(graphicsContext, screenContext);
        this.playerContext = playerContext;
        
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
        mainMenuChoices.add(newPlayer);
        mainMenuChoices.add(hunt);
        mainMenuChoices.add(enterTime);
        mainMenuChoices.add(plan);
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
    
    @Override
    public void stepToScreenTrigger() {
         newPlayer.setIsAncillary(playerContext.hasMainCharacter());
         hunt.setIsAncillary(!playerContext.hasMainCharacter());
         plan.setIsAncillary(!playerContext.hasMainCharacter());
         enterTime.setIsAncillary(!playerContext.hasMainCharacter());
         
         if (mainMenuChoices.getCurrentChoice().isAncillary()) {
             mainMenuChoices.cycleDown();
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
        } else if (mainMenuChoices.getCurrentChoice().equals(enterTime)) {
            stepScreenForwards(new EnterTimeScreen(getBitmapContext(), getScreenContext()));
        } else if (mainMenuChoices.getCurrentChoice().equals(newPlayer)) {
            stepScreenForwards(new NewCharacterScreen(getBitmapContext(), getScreenContext(), 
                    playerContext));
        }
    }

    @Override
    public void handleFrameChange() {    
    }
}