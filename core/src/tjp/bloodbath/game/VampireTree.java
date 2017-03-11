package tjp.bloodbath.game;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class VampireTree {
    public static final int DEFAULT_LEVELS = 5;
    public static final int DEFAULT_SIBLINGS = 4;
    
    private static final int MIN_RANDO_AGE = 35;
    private static final int MAX_RANDO_AGE = 1600;
    
    private static final int MIN_TOP_LEVEL_AGE = 2700;
    private static final int MAX_TOP_LEVEL_AGE = 3000;

    private static final int MIN_GENERATION_DIFFERENCE = 16;
    private static final int MAX_GENERATION_DIFFERENCE = 500;

    private static final int MIN_SIBLINGS = 1;
    private static final int MAX_SIBLINGS = 6;

    private static final int NEW_NAME_DIVISIONS = 16;
    
    private static final double USE_NAME_FACTOR = 0.25;
    
    private Set<VampireTree> branches = new HashSet<VampireTree>();
    
    private GameCharacter root;
    
    private int getRandomGenerationDiff(int parentAge) {
        return ThreadLocalRandom.current().nextInt(MIN_GENERATION_DIFFERENCE,
                Math.min(parentAge, MAX_GENERATION_DIFFERENCE) + 1);
    }
    
    // for bean
    VampireTree() { }
    
    public VampireTree(int siblings, int remainingLevels, RandomNameGenerator rng,
            GameCharacter parent) {
        
        final String firstName = rng.getFirstName();
        final String lastName;
        String title = "Unknown";
        int age = 100;

        if (parent == null) {
            lastName = rng.getLastName();
            title = "Baron of House " + lastName;
            age = ThreadLocalRandom.current().nextInt(MIN_TOP_LEVEL_AGE, MAX_TOP_LEVEL_AGE);
        } else {
            double roll = ThreadLocalRandom.current().nextDouble(1);
            if (roll < USE_NAME_FACTOR) {
                lastName = parent.getLastName();
                title = "Descendant of " + parent.getFullName();
                int generationDifference = getRandomGenerationDiff(parent.getAge());
                age = Math.max(16, parent.getAge() - generationDifference);
            } else {
                lastName = rng.getLastName();
                int divisions = ThreadLocalRandom.current().nextInt(1, NEW_NAME_DIVISIONS);
                switch (divisions) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        title = "Spawn of " + parent.getFullName();
                        int generationDifference = getRandomGenerationDiff(parent.getAge());

                        age = Math.max(16, parent.getAge() - generationDifference);
                        break;
                    
                    case 9:
                        title = "Bodyguard of " + parent.getFullName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break;
                    
                    case 10:
                        title = "Lawyer of " + parent.getFullName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break;
                    
                    case 11:
                        title = "Gofer to " + parent.getFullName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break;
                        
                    case 12:
                    case 13:
                        title = "Affiliate of " + parent.getFullName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break;
                    
                    case 14:
                        title = "Pledged assassin for House " + parent.getLastName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break;    

                    case 15:
                        title = "Security chief for House " + parent.getLastName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break; 
                    
                    case 16:
                        title = "Advisor to " + parent.getFullName();
                        age = ThreadLocalRandom.current().nextInt(MIN_RANDO_AGE, MAX_RANDO_AGE);
                        break;
                }
            }
        }
        
        
        this.root = new GameCharacter(remainingLevels, firstName, lastName, title, age);

        if (remainingLevels > 0) {
            for(int i = 0; i < siblings; i++) {
                int randomSiblings =
                        ThreadLocalRandom.current().nextInt(MIN_SIBLINGS,MAX_SIBLINGS  + 1);

                branches.add(new VampireTree(randomSiblings, remainingLevels - 1, rng, root));
            }
        }
    }

    public Set<VampireTree> getBranches() {
        return branches;
    }
    
    public GameCharacter getRoot() {
        return this.root;
    }
    
    public void setBranches(Set<VampireTree> branches) {
        this.branches = branches;
    }
    
    public void setRoot(GameCharacter root) {
        this.root = root;
    }
}
