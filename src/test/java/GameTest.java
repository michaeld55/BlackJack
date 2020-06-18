import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Deck deck;
    private Player player1;
    private Player player2;
    private Game game;

    @Before
    public void before(){
        deck = new Deck();
        player1 = new Player("Adam");
        player2 = new Player("Michael");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        game = new Game(players);

    }

    @Test
    public void canGivePlayersCards(){
        game.start();
//        System.out.println(player1.getHand().get(0).getRank());
//        System.out.println(player1.getHand().get(0).getSuit());
//        System.out.println(player2.getHand().get(0).getRank());
//        System.out.println(player2.getHand().get(0).getSuit());
        assertEquals(2, player1.getHandCount());
        assertEquals(2, player2.getHandCount());
    }

    @Test
    public void player1Wins(){

        player1.addCard(new Card(Suit.HEARTS, Rank.KING));
        player1.addCard(new Card(Suit.HEARTS, Rank.KING));
        player2.addCard(new Card(Suit.HEARTS, Rank.NINE));
        player2.addCard(new Card(Suit.HEARTS, Rank.NINE));
        Player winner = game.checkWinner();
        assertEquals("Adam", winner.getName());
    }

    @Test
    public void player2Wins(){

        player2.addCard(new Card(Suit.HEARTS, Rank.KING));
        player2.addCard(new Card(Suit.HEARTS, Rank.KING));
        player1.addCard(new Card(Suit.HEARTS, Rank.NINE));
        player1.addCard(new Card(Suit.HEARTS, Rank.NINE));
        Player winner = game.checkWinner();
        assertEquals("Michael", winner.getName());
    }

    @Test
    public void canDrawMoreCards(){
        game.start();
        game.drawExtraCard(player1);

        assertEquals(3, player1.getHandCount());

    }

    @Test
    public void canGoBust(){
        game.start();
        player1.addCard(new Card(Suit.HEARTS, Rank.ACEHIGH));
        player1.addCard(new Card(Suit.HEARTS, Rank.ACEHIGH));
        Player winner = game.checkWinner();
        assertEquals("Michael", winner.getName());
    }

}
