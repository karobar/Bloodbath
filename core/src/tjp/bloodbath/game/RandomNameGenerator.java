package tjp.bloodbath.game;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class RandomNameGenerator {
    private static final String FIRST_NAME_FILE_NAME = "random/first-names.json";
    private static final String LAST_NAME_FILE_NAME = "random/last-names.json";
    
    private final Yaml parser;
    private final List<String> firstNames, lastNames;
    
    public RandomNameGenerator() {
        this.parser = new Yaml();
        firstNames = checkNotNull(loadFileAsString(FIRST_NAME_FILE_NAME));
        lastNames = checkNotNull(loadFileAsString(LAST_NAME_FILE_NAME));
    }
    
    @SuppressWarnings("unchecked")
    private List<String> loadFileAsString(String fileName) {
        FileHandle nameFile = Gdx.files.local(fileName);
        return (List<String>) parser.load(nameFile.readString());
    }
    
    public String getFirstName() {
        return getRandomElementFromList(firstNames); 
    }
    
    public String getLastName() {
        return getRandomElementFromList(lastNames); 
    }
    
    private String getRandomElementFromList(List<String> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size())); 
    }
}
