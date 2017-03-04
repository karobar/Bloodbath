package tjp.bloodbath.game;

import com.badlogic.gdx.Gdx;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.MainFrame;

public class Bloodbath extends MainFrame {
    public Bloodbath(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots) {

        super(bitmapContext, widthInSlots, heightInSlots, new TitleScreen(bitmapContext));
    }

    @Override
    protected void createHook() { }
}
