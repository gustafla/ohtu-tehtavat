package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class StatisticsTest {

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void topScorersGetsFirstRight() {
        assertTrue(stats.topScorers(1).get(0).toString().contains("Gretzky"));
    }

    @Test
    public void topScorersGetsAllRight() {
        List<Player> top = stats.topScorers(readerStub.getPlayers().size());
        assertTrue(top.get(0).toString().contains("Gretzky"));
        assertTrue(top.get(1).toString().contains("Lemieux"));
        assertTrue(top.get(2).toString().contains("Yzerman"));
        assertTrue(top.get(3).toString().contains("Kurri"));
        assertTrue(top.get(4).toString().contains("Semenko"));
    }

    @Test
    public void topScorersGetsZeroRight() {
        assertTrue(stats.topScorers(0).isEmpty());
    }

    @Test
    public void teamEDMIsFound() {
        String team = stats.team("EDM").toString();
        assertTrue(team.contains("Semenko"));
        assertTrue(team.contains("Kurri"));
        assertTrue(team.contains("Gretzky"));
        assertFalse(team.contains("Lemieux"));
        assertFalse(team.contains("Yzerman"));
    }

    @Test
    public void teamTestIsNotFound() {
        assertTrue(stats.team("Test").isEmpty());
    }

    @Test
    public void searchFindsExistingPlayer() {
        assertEquals(stats.search("urr").getName(), "Kurri");
    }

    @Test
    public void searchReturnsNullForNonExistingPlayer() {
        assertEquals(stats.search("Koivu"), null);
    }
}
