package tjp.bloodbath.screens;

import static com.google.common.base.Preconditions.checkNotNull;

import com.badlogic.gdx.Input.Keys;

import tjp.bloodbath.game.GameCharacter;
import tjp.bloodbath.game.Save;
import tjp.bloodbath.game.VampireTree;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.GUIelement;
import tjp.wiji.gui.GUItext;
import tjp.wiji.gui.LongList;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.gui.Timer;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class ViewIntelScreen extends Screen { 
    private final Save save;
    private final Timer timer;
    private final ScreenTextList choiceList;
    private final LongList<GameCharacter> targetList;
    
    private GUItext info = new GUItext("INFO");
    private GUItext pickAsTarget = new GUItext("PICK AS NEXT TARGET");
    
    public ViewIntelScreen(BitmapContext bitmapContext, ScreenContext screenContext, Timer timer,
            Save save) {
        
        super(bitmapContext, screenContext);
        this.save = checkNotNull(save);
        this.timer = checkNotNull(timer);
        
        ScreenTextList timerList = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .y(0)
                .build();
        timerList.add(timer);
        addGUIelement(timerList);
        
        targetList = LongList.<GameCharacter>newLongListBuilder()
                .bitmapContext(bitmapContext)
                .activeColor(Color.YELLOW)
                .inactiveColor(Color.WHITE)
                .tertiaryColor(Color.RED)
                .y(1)
                .endY(19)
                .build();
        visit(save.getVampireTree(), targetList, 0);
        addGUIelement(targetList);
        
        choiceList = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .activeColor(Color.RED)
                .y(20)
                .build();
        choiceList.add(info);
        choiceList.add(pickAsTarget);
        addGUIelement(choiceList);
        
        ScreenTextList infoList = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .y(23)
                .build();
        infoList.add(new GUItext("Use - and + to cycle targets", true));
        addGUIelement(infoList);
    }
    
    private void visit(VampireTree tree, LongList<GameCharacter> targets, int level) {
        targets.add(new GUItext(getIndent(level) + tree.getRoot().getFullName()), tree.getRoot());
        if (level <= 2) {
            for (VampireTree branch : tree.getBranches()) {
                visit(branch, targets, level + 1);
            }
        }
    }
    
    private String getIndent(int level) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < level; i++) {
            builder.append("    ");
        }
        return builder.toString();
    }
    
    @Override
    protected ImageRepresentation getCurrentCell(int i, int j) {
        return new GraphicRepresentation(Color.BLACK, Color.BLACK, Graphic.EMPTY_CELL,
                getBitmapContext().getCharPixelWidth(), getBitmapContext().getCharPixelHeight());
    }
    
    @Override
    public void handleEvent(GameEvent event) {
        switch(event.getIntCode()) {
            case Keys.PLUS:
                targetList.cycleDown();
                break;
            case Keys.MINUS:
                targetList.cycleUp();
                break;
            case Keys.UP:
                choiceList.cycleUp();
                break;
            case Keys.DOWN:
                choiceList.cycleDown();
                break;
            case Keys.ENTER:
                if (choiceList.getCurrentChoice().equals(info)) {
                    
                    stepScreenForwards(new TargetInfoScreen(
                            getBitmapContext(),
                            getScreenContext(),
                            checkNotNull(targetList.getCurrentElement())));
 
                } else if (choiceList.getCurrentChoice().equals(pickAsTarget)) {
                    chooseAsNextTarget();
                }
                
                break;
            case Keys.ESCAPE:
                stepScreenBackwards();
                break;
        } 
    }
    
    private void chooseAsNextTarget() {
        GUIelement targetText = targetList.getCurrentChoice();
        GameCharacter target = targetList.getFromKey(targetText);
        targetList.setTertiaryElement(targetText);
        save.setTarget(target);
    }

    @Override
    protected void handleFrameChange() {
        save.decrementTime();
        timer.setTime(save.getLoggedTime());
    }

    @Override
    public void stepToScreenTrigger() {

    }
}
