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
    
    public Bloodbath(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots, 
            TitleScreen titleScreen) {

        super(bitmapContext, widthInSlots, heightInSlots, titleScreen);

        this.titleScreen = checkNotNull(titleScreen);
    }

    @Override
    protected void createHook() {
        titleScreen.init(this);
    }

    @Override
    protected void disposeHook() {
        PlayerContext playerContext = titleScreen.getPlayerContext();
        
        if (playerContext.hasMainCharacter()) {
            Yaml yaml = new Yaml();
            String charDump = yaml.dump(playerContext.getMainCharacter());
            FileHandle file = Gdx.files.local("save.yml");
            file.writeString(charDump, false);
        } 
    }
}
