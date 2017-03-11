package tjp.bloodbath.game;

public enum Card {
    SERIOUS_WOUND("serious wound"),
    WOUND("wound"),
    GOOD("good");
    
    private String name;
    
    private Card(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}

