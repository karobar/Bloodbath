package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    private Collection<Card> savedHand;
    
    private Set<String> slainChars;

    private GameCharacter target;

    private VampireTree vampireTree;

    // for bean
    public Save() { 
        this.slainChars = new HashSet<String>();
    }
    
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
    
    public Collection<Card> getSavedHand() {
        return savedHand;
    }
    
    public FileHandle getSaveFile() {
        return Gdx.files.local(SAVE_FILE_NAME);
    }
    
    public Set<String> getSlainChars() {
        return slainChars;
    }
    
    public GameCharacter getTarget() {
        return target;
    }
    
    public VampireTree getVampireTree() {
        return vampireTree;
    }
    
    public boolean hasLoggedTime() {
        return this.loggedTime > 0;
    }
    
    public boolean hasMainCharacter() {
        return this.mainCharacter != null;
    }
    
    public void killCharacter(boolean removeFromTree, GameCharacter target) {
        target.setAlive(false);
        if (slainChars == null) {
            slainChars = new HashSet<String>();
        }
        slainChars.add(target.getFullName());
    }
    
    public void permadeath() {
        slainChars.add(mainCharacter.getFirstName());
        mainCharacter = null;
        vampireTree = null;
        target = null;
    }
    
    // for bean
    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }
    
    public void setMainCharacter(GameCharacter player) {
        this.mainCharacter = checkNotNull(player);
    }

    public void setSavedHand(Collection<Card> savedHand) {
        this.savedHand = savedHand;
    }

    public void setSlainChars(Set<String> slainChars) {
        this.slainChars = slainChars;
    }

    public void setTarget(GameCharacter target) {
        this.target = target;
    }

    public void setVampireTree(VampireTree tree) {
        this.vampireTree = tree;
    }

    public String toString() {
        if (mainCharacter != null) {
            return mainCharacter.toString();
        }
        else return "";
    }
}
