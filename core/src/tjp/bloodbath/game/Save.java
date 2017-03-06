package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

public class Save {
    private PlayerCharacter mainCharacter;
    
    // for bean
    public Save() { }
    
    public void setMainCharacter(PlayerCharacter player) {
        this.mainCharacter = checkNotNull(player);
    }
    
    public boolean hasMainCharacter() {
        return this.mainCharacter != null;
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
