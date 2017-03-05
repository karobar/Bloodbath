package tjp.bloodbath.game;

import tjp.bloodbath.screens.TitleScreen;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.MainFrame;
import tjp.wiji.gui.ScreenContext;

public class Bloodbath extends MainFrame {
    public Bloodbath(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots) {

        super(bitmapContext, widthInSlots, heightInSlots, 
                new TitleScreen(bitmapContext, new ScreenContext(), new PlayerContext()));
    }

    @Override
    protected void createHook() { }
}
