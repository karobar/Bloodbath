package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlayerCharacter {
    private String name;
    
    public PlayerCharacter(String name) {
        this.name = checkNotNull(name);
    }
}
