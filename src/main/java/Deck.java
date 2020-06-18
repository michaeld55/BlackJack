import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int cardCount() {
        return cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void fillDeck() {


        for(Suit face : Suit.values()){
            for(Rank number : Rank.values()) {
                if(number != Rank.ACELOW) {
                    Card card = new Card(face, number);
                    addCard(card);
                }

            }
        }

    }

    public ArrayList<Card> shuffle() {
        Collections.shuffle(cards);
        return cards;
    }

    public Card dealCard() {
        Card dealtCard = cards.get(0);
        cards.remove(0);
        return dealtCard;
    }
}
