package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;
import tjp.bloodbath.game.Bloodbath;
import tjp.bloodbath.game.Save;
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

public class TitleScreen extends Screen {    
    ScreenTextList mainMenuChoices;
    private final Save save;
    
    private final GUItext newPlayer = new GUItext("NEW HUNTER");
    private final GUItext hunt = new GUItext("HUNT", true);
    private final GUItext plan = new GUItext("PLAN", true);
    private final GUItext enterTime = new GUItext("REPORT", true);
    private static final Timer TIMER = new Timer("0:00", true);
    private static final GUItext NEWLINE = new GUItext("", true);
    
    private static final GUItext TUTORIAL = new GUItext("TUTORIAL");
    private static final GUItext EXIT_GAME = new GUItext("EXIT");
    
    private Bloodbath mainFrame;
    
    public TitleScreen(BitmapContext graphicsContext, ScreenContext screenContext, 
            Bloodbath controllingFrame, Save save) {

        super(graphicsContext, screenContext);
        this.mainFrame = controllingFrame;
        this.save = save;

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
        mainMenuChoices.add(EXIT_GAME);
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
    
    public Save getSave() {
        return save;
    }
    
    @Override
    public void stepToScreenTrigger() {
         newPlayer.setIsAncillary(save.hasMainCharacter());
         hunt.setIsAncillary(!save.hasMainCharacter());
         plan.setIsAncillary(!save.hasLoggedTime());
         enterTime.setIsAncillary(!save.hasMainCharacter());
         TIMER.setTime(save.getLoggedTime());
         
         if (mainMenuChoices.getCurrentChoice().isAncillary()) {
             mainMenuChoices.cycleDown();
         }
    }
    
    // on enter
    private void handleSelection() {     
        if (mainMenuChoices.getCurrentChoice().equals(TUTORIAL)) {
            stepScreenForwards(new TutorialScreen(getBitmapContext(), getScreenContext()));
        } else if (mainMenuChoices.getCurrentChoice().equals(EXIT_GAME)) {
            mainFrame.dispose(); 
        } else if (mainMenuChoices.getCurrentChoice().equals(enterTime)) {
            stepScreenForwards(new EnterTimeScreen(getBitmapContext(), getScreenContext(), save));
        } else if (mainMenuChoices.getCurrentChoice().equals(newPlayer)) {
            stepScreenForwards(new NewCharacterScreen(getBitmapContext(), getScreenContext(), 
                    save));
        } else if (mainMenuChoices.getCurrentChoice().equals(hunt)) {
            stepScreenForwards(new HuntScreen(getBitmapContext(), getScreenContext(), save));
        } else if (mainMenuChoices.getCurrentChoice().equals(plan)) {
            stepScreenForwards(new PlanScreen(getBitmapContext(), getScreenContext(), save));
        }
    }

    @Override
    public void handleFrameChange() {    
    }
}