package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlayerContext {
    PlayerCharacter mainCharacter;
    
    public void setMainCharacter(PlayerCharacter player) {
        this.mainCharacter = checkNotNull(player);
    }
    
    public boolean hasMainCharacter() {
        return this.mainCharacter != null;
    }
}
