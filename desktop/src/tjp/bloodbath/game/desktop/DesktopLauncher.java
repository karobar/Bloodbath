package tjp.bloodbath.game.desktop;

import java.net.URISyntaxException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;

import tjp.bloodbath.game.Bloodbath;
import tjp.bloodbath.game.Save;
import tjp.bloodbath.screens.TitleScreen;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.gui.ScreenContext;

public class DesktopLauncher {
    private int widthInSlots, heightInSlots, frameWidth, frameHeight;
    
    private final static int DEFAULT_HEIGHT_IN_SLOTS = 25;
    private final static int DEFAULT_WIDTH_IN_SLOTS = 80;
    
    private BitmapContext bitmapContext;
    
    public DesktopLauncher() throws URISyntaxException {
        widthInSlots = DEFAULT_WIDTH_IN_SLOTS;
        heightInSlots = DEFAULT_HEIGHT_IN_SLOTS;
        
        bitmapContext = new BitmapContext();

        frameWidth = bitmapContext.getCharPixelWidth() * widthInSlots;
        frameHeight = bitmapContext.getCharPixelHeight() * heightInSlots;
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        DesktopLauncher launcher = null;
        try {
            launcher = new DesktopLauncher();
        } catch (URISyntaxException e) {
            Gdx.app.error("ERROR", "my error message", e);
            System.exit(0);
        }
        
        config.title = "Pomo Test";
        config.width = launcher.frameWidth;
        config.height = launcher.frameHeight;
        config.resizable = false;
        //config.addIcon("AppIcon.png", FileType.Internal);
        
        new LwjglApplication(new Bloodbath(launcher.bitmapContext, 
                        launcher.widthInSlots,
                        launcher.heightInSlots),
                config);
    }
}
