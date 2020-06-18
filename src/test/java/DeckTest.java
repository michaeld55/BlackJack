import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    private Deck deck;
    private Card card;

    @Before
    public void before(){
        card = new Card(Suit.HEARTS, Rank.ACELOW);
        deck = new Deck();
    }

    @Test
    public void startsEmpty(){
        assertEquals(0, deck.cardCount());
    }

    @Test
    public void canAddCard(){
        deck.addCard(card);
        assertEquals(1, deck.cardCount());
    }

    @Test
    public void canCreateDeck(){
        deck.fillDeck();
        assertEquals(52, deck.cardCount());
    }

    @Test
    public void canShuffleDeck(){
        deck.fillDeck();
//        System.out.println(deck.getCards().get(0).getSuit());
//        System.out.println(deck.getCards().get(0).getRank());
        ArrayList<Card> shuffledCards = deck.shuffle();
//        System.out.println(shuffledCards.get(0).getSuit());
//        System.out.println(shuffledCards.get(0).getRank());
//        System.out.println(deck.getCards().get(0).getSuit());
//        System.out.println(deck.getCards().get(0).getRank());
        assertEquals( shuffledCards, deck.getCards());
    }

    @Test
    public void canDeal(){
        deck.fillDeck();
        ArrayList<Card> shuffledCards = deck.shuffle();
        assertEquals(shuffledCards.get(0), deck.dealCard());
        assertEquals(51, deck.cardCount());
    }

}
