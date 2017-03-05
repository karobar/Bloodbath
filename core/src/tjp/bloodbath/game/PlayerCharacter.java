package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlayerCharacter {
    private String name;
    
    //for javaBeans
    public PlayerCharacter() { }
    
    public PlayerCharacter(String name) {
        this.name = checkNotNull(name);
    }
    
    public String getName() {
        return name;
    }
    
    //for javaBeans
    public void setName(String name) {
        this.name = name;
    }
}
