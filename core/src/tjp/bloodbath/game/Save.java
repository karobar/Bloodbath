package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Save {
    private static final String SAVE_FILE_NAME = "save.yml";
    
    public static boolean fileExists() {
        FileHandle saveFile = Gdx.files.local(SAVE_FILE_NAME);
        return saveFile.exists();
    }
    
    public static Save getSave() {
        if (fileExists()) {
            FileHandle saveFile = Gdx.files.local(SAVE_FILE_NAME);
            return (Save) new Yaml().load(saveFile.readString());
        } else {
            throw new RuntimeException();
        }
    }
    
    private int loggedTime;
    
    private GameCharacter mainCharacter;
    
    private VampireTree vampireTree;
    
    // for bean
    public Save() { }
    
    public void addTime(int numberOfSeconds) {
        this.loggedTime += numberOfSeconds;
    }
    
    public void decrementTime() {
        this.loggedTime--;
    }
    
    public int getLoggedTime() {
        return loggedTime;
    }
    
    public GameCharacter getMainCharacter() {
        return mainCharacter;
    }
    
    public FileHandle getSaveFile() {
        return Gdx.files.local(SAVE_FILE_NAME);
    }
    
    public boolean hasLoggedTime() {
        return this.loggedTime > 0;
    }
    
    public boolean hasMainCharacter() {
        return this.mainCharacter != null;
    }
    
    // for bean
    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }
    
    public void setMainCharacter(GameCharacter player) {
        this.mainCharacter = checkNotNull(player);
    }

    public String toString() {
        if (mainCharacter != null) {
            return mainCharacter.toString();
        }
        else return "";
    }

    public VampireTree getVampireTree() {
        return vampireTree;
    }

    public void setVampireTree(VampireTree tree) {
        this.vampireTree = tree;
    }
}
