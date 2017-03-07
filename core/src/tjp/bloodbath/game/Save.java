package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Save {
    private PlayerCharacter mainCharacter;
    private Duration loggedTime;
    
    // for bean
    public Save() {
    }
    
    public Duration getLoggedTime() {
        return loggedTime;
    }
    
    public void setLoggedTime(Duration loggedTime) {
        this.loggedTime = loggedTime;
    }
    
    public void setMainCharacter(PlayerCharacter player) {
        this.mainCharacter = checkNotNull(player);
    }
    
    public boolean hasMainCharacter() {
        return this.mainCharacter != null;
    }
    
    public boolean hasLoggedTime() {
        return this.loggedTime != null && this.loggedTime.compareTo(Duration.ZERO) > 1;
    }
    
    public PlayerCharacter getMainCharacter() {
        return mainCharacter;
    }
    
    public String toString() {
        if (mainCharacter != null) {
            return mainCharacter.toString();
        }
        else return "";
    }
}
