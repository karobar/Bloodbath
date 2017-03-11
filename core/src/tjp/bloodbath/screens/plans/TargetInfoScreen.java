package tjp.bloodbath.screens.plans;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.GameCharacter;
import tjp.bloodbath.game.Save;
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

public class TargetInfoScreen extends AbstractPlanScreen {    
    public TargetInfoScreen(BitmapContext graphicsContext, ScreenContext screenContext, Save save,
            GameCharacter target) {

        super(graphicsContext, screenContext, save);
        
        ScreenTextList tutText = ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .centered()
                .y(11)
                .build();
        tutText.add(new GUItext(target.getFullName()));
        tutText.add(new GUItext(target.getTitle()));
        tutText.add(new GUItext("age: " + target.getAge()));
        tutText.add(new GUItext("deck: " + target.getDeck()));
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
}