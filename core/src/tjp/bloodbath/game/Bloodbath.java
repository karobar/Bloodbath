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
    
    private static final String SAVE_FILE_NAME = "save.yml";
    
    public Bloodbath(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots) {

        super(bitmapContext, widthInSlots, heightInSlots);
    }

    @Override
    protected ScreenContext createStartingScreenContext() {
        titleScreen = new TitleScreen(getBitmapContext(), new ScreenContext(), this);
        
        FileHandle saveFile = Gdx.files.local(SAVE_FILE_NAME);
        if (saveFile.exists()) {
            Save save = (Save) YAML.load(saveFile.readString());
            titleScreen.setSave(save);
        } else {
            titleScreen.setSave(new Save());
        }

        titleScreen.getScreenContext().init(titleScreen);
        return titleScreen.getScreenContext();
    }

    @Override
    protected void disposeHook() {
        Save save = titleScreen.getSave();
        
        if (save.hasMainCharacter()) {
            String charDump = YAML.dump(save);
            FileHandle saveFile = Gdx.files.local(SAVE_FILE_NAME);
            saveFile.writeString(charDump, false);
        } 
    }
}
