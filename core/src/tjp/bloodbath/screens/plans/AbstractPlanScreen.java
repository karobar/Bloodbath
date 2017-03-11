package tjp.bloodbath.screens.plans;

import tjp.bloodbath.game.Save;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.gui.ScreenTextList;
import tjp.wiji.gui.Timer;

public abstract class AbstractPlanScreen extends Screen {
    private Save save;
    
    private Timer timer = new Timer("00:00", true);

    protected AbstractPlanScreen(BitmapContext bitmapContext, ScreenContext screenContext, 
            Save save) {

        super(bitmapContext, screenContext);
        this.save = save;
        
        ScreenTextList timerList = ScreenTextList.newBuilder()
                .bitmapContext(bitmapContext)
                .centered()
                .y(0)
                .build();
        timerList.add(timer);
        addGUIelement(timerList);
    }
    
    public Save getSave() {
        return save;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    protected void handleFrameChange() {
        save.decrementTime();
        timer.setTime(save.getLoggedTime());
    }
    
    @Override
    public void stepToScreenTrigger() {
        timer.setTime(getSave().getLoggedTime());
    }
}
