import java.util.ArrayList;

public class Game {

    private Deck deck;
    private ArrayList<Player> players;

    public Game(ArrayList players){
        this.players = players;
        this.deck = new Deck();
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void start() {
        deck.fillDeck();
        deck.shuffle();
        for(Player player: players){
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }
    }

    public Player checkWinner() {
        int value = 0;
        Player winner = new Player("");

        for (Player player: players) {

            if(player.getHandValue() > value ){
                value = player.getHandValue();
                if(value <= 21) {

                    winner = new Player(player.getName());
                    winner.setCanWin(true);
                }else{

                    value = 0;
                }
            }

        }
        return winner;
    }

    public Player drawExtraCard(Player player){
        Card newCard = deck.dealCard();
        player.addCard(newCard);
        return player;
    }
}
