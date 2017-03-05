package tjp.bloodbath.screens;

import static com.google.common.base.Preconditions.checkNotNull;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.PlayerCharacter;
import tjp.bloodbath.game.PlayerContext;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.InputField;
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
public class NewCharacterScreen extends Screen { 
    private final ScreenTextList choices;
    private final InputField nameInput;
    private PlayerContext playerContext;
    
    public NewCharacterScreen(BitmapContext graphicsContext, ScreenContext screenContext, 
            PlayerContext playerContext) {
        
        super(graphicsContext, screenContext);
        this.playerContext = checkNotNull(playerContext);
        
        addGUIelement(ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .initialItem("NAME:")
                .centered().y(8)
                .build());
        
        choices = ScreenTextList.newBuilder()
            .bitmapContext(graphicsContext)
            .inactiveColor(Color.GRAY)
            .activeColor(Color.RED)
            .centered().y(10)
            .build();
        nameInput = new InputField(Color.RED, Color.GRAY, 7, 25);
        //GUItext done = new GUItext("DONE ", Color.RED, Color.GRAY);
        choices.add(nameInput);
        addGUIelement(choices);
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        if (choices.getCurrentChoice() == nameInput && event.isNormalTypingKey()) {
            nameInput.push(event.getCharCode());
        }
        
        switch(event.getIntCode()) {
            case Keys.BACKSPACE:
                nameInput.pop();
                break;
            case Keys.UP:
                choices.cycleUp();
                break;
            case Keys.DOWN:
                choices.cycleDown();
                break;
            case Keys.ESCAPE:
                stepScreenBackwards();
                break;
            case Keys.ENTER:
                possiblyAccept();
                break;
        } 
    }
    
    private void possiblyAccept() {
        if (!nameInput.isEmpty()) {
            playerContext.setMainCharacter(new PlayerCharacter(nameInput.toString()));
            stepScreenBackwards();
        }
    }

    @Override
    protected void handleFrameChange() { }

    @Override
    public void stepToScreenTrigger() {
 
    }
}