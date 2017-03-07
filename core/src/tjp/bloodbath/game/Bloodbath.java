package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import tjp.bloodbath.screens.TitleScreen;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.MainFrame;
import tjp.wiji.gui.ScreenContext;

public class Bloodbath extends MainFrame {
    private TitleScreen titleScreen;
    
    private final static Yaml YAML = new Yaml();
    

    
    public Bloodbath(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots) {

        super(bitmapContext, widthInSlots, heightInSlots);
    }

    @Override
    protected ScreenContext createStartingScreenContext() {
        Save save;
        if (Save.fileExists()) {
            save = Save.getSave();
        } else {
            save = new Save();
        }
        
        titleScreen = new TitleScreen(getBitmapContext(), new ScreenContext(), this, save);

        titleScreen.getScreenContext().init(titleScreen);
        return titleScreen.getScreenContext();
    }

    @Override
    protected void disposeHook() {
        Save save = titleScreen.getSave();
        
        if (save.hasMainCharacter()) {
            String charDump = YAML.dump(save);
            save.getSaveFile().writeString(charDump, false);
        } 
    }
}
