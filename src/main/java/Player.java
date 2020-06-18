import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean canWin;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.canWin = false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public int getHandCount() {
        return hand.size();
    }

    public Card getFirstCard() {
        return hand.get(0);
    }

    public int getHandValue(){
        int totalValue = 0;

        for (Card card : hand) {
            totalValue += card.getValueFromEnum();
        }

        return totalValue;
    }

    public boolean isCanWin() {
        return canWin;
    }

    public void setCanWin(boolean canWin) {
        this.canWin = canWin;
    }
}
