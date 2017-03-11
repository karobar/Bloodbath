package tjp.bloodbath.screens;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.Save;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.gui.TimeInputField;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class EnterTimeScreen extends Screen { 
    final TimeInputField input;
    final Save save;
    
    public EnterTimeScreen(BitmapContext graphicsContext, ScreenContext screenContext, Save save) {
        super(graphicsContext, screenContext);
        this.save = save;
        
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
        
        addGUIelement(ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .color(Color.WHITE)
                .centered()
                .initialItem("You discuss what you've learned for some time:")
                .y(12)
                .build());
        
        ScreenTextList inputList = ScreenTextList.newBuilder()
                .bitmapContext(graphicsContext)
                .centered()
                .y(14)
                .build();
        
        input = new TimeInputField(Color.RED, Color.WHITE, 8, "HH:MM:SS", Color.DARK_GRAY);
        inputList.add(input);
        addGUIelement(inputList);
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        if (event.isNumber()) {
            input.push(event.getCharCode());
        }
        
        switch(event.getIntCode()) {
            case Keys.BACKSPACE:
                input.pop();
                break;
            case Keys.ESCAPE:
                stepScreenBackwards();
                break;
            case Keys.ENTER:
                save.addTime(input.getNumberOfSeconds() / 6);
                stepScreenBackwards();
                break;
        } 
    }

    @Override
    protected void handleFrameChange() { }

    @Override
    public void stepToScreenTrigger() {     
    }
}