package tjp.bloodbath.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class RandomNameGeneratorTest {
    @Test
    public void testGetFirstName() {
        RandomNameGenerator dut = new RandomNameGenerator();
        String firstName = dut.getFirstName();
        assertNotNull(firstName);
        assertFalse(firstName.isEmpty());
        System.out.println(firstName);
    }
    
    @Test
    public void testGetLastName() {
        RandomNameGenerator dut = new RandomNameGenerator();
        String lastName = dut.getLastName();
        assertNotNull(lastName);
        assertFalse(lastName.isEmpty());
        System.out.println(lastName);
    }
}
