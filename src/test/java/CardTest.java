import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    private Card card;

    @Before
    public void before(){
        card = new Card(Suit.HEARTS, Rank.ACELOW);
    }

    @Test
    public void canGetSuit(){
        assertEquals(Suit.HEARTS, card.getSuit());
    }

    @Test
    public void canGetRank(){
        assertEquals(Rank.ACELOW, card.getRank());
    }

    @Test
    public void canGetCardValue(){
        assertEquals(1, card.getValueFromEnum());
    }
}
