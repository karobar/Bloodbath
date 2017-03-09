package tjp.bloodbath.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

@RunWith(JukitoRunner.class)
public class VampireTreeTest {    
    private RandomNameGenerator rng;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        rng = mock(RandomNameGenerator.class);
        
        when(rng.getFirstName()).thenReturn("test first name");
        when(rng.getLastName()).thenReturn("test last name");
    }
    
    @Test
    public void testConstructorBasic() {
        VampireTree tree = new VampireTree(0,0, rng, null);
        assertNotNull(tree.getRoot());
        assertTrue(tree.getBranches().isEmpty());
    }
    
    @Test
    public void testConstructorOneLevelDeep() {
        VampireTree tree = new VampireTree(1,1, rng, null);
        assertNotNull(tree.getRoot());
        assertFalse(tree.getBranches().isEmpty());
        assertTrue(tree.getBranches().size() == 1);
        
        VampireTree anyDescendent = tree.getBranches().iterator().next();
        assertNotNull(anyDescendent.getRoot());
        assertTrue(anyDescendent.getBranches().isEmpty());
    }
    
    @Test
    public void testConstructorOneLevelDeepWithSibling() {
        VampireTree tree = new VampireTree(2,1, rng, null);
        assertNotNull(tree.getRoot());
        assertFalse(tree.getBranches().isEmpty());
        assertTrue(tree.getBranches().size() == 2);
        
        VampireTree anyDescendent = tree.getBranches().iterator().next();
        assertNotNull(anyDescendent.getRoot());
        assertTrue(anyDescendent.getBranches().isEmpty());
    }
    
    @Test
    public void testConstructorTwoLevelsDeep() {
        VampireTree tree = new VampireTree(1,2, rng, null);
        assertNotNull(tree.getRoot());
        assertFalse(tree.getBranches().isEmpty());
        assertTrue(tree.getBranches().size() == 1);
        
        VampireTree anyDescendent = tree.getBranches().iterator().next();
        assertNotNull(anyDescendent.getRoot());
        assertTrue(anyDescendent.getBranches().size() >= 1);
        assertTrue(anyDescendent.getBranches().size() <= 6);
        
        VampireTree anyGrandchild = anyDescendent.getBranches().iterator().next();
        assertNotNull(anyGrandchild.getRoot());
        assertTrue(anyGrandchild.getBranches().isEmpty());
    }
}
