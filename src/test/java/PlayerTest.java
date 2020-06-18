import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private Player player;
    private Card card;

    @Before
    public void before(){
        player = new Player("Tod");
        card = new Card(Suit.HEARTS, Rank.ACELOW);
    }

    @Test
    public void handStartsEmpty(){
        assertEquals(0, player.getHandCount());
    }

    @Test
    public void canAddCard(){
        player.addCard(card);
        assertEquals(1, player.getHandCount());
    }

    @Test
    public void canAddAllCard(){
        player.addCard(card);
        player.addCard(card);
        assertEquals(2, player.getHandValue());
    }
}
