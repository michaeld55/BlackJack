import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.parseInt;

public class Runner {

    public static void main(String[] args) {

        boolean dealerCanWin = false;
        boolean playerCanWin = false;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<Player>();
        Dealer dealer = new Dealer("Dealer");
        players.add(dealer);

        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players would you like play?");

        String input = scanner.next();
        int playerNumber = parseInt(input);
        int i = 1;

        while (playerNumber > 0){

            System.out.println("Player " + i + ": Please enter your name");
            String name = scanner.next();

            Player newPlayer = nameCheck(name);
            players.add(newPlayer);
            i++;
            playerNumber --;
        }

        Game game = new Game(players);
        game.start();

        for(Player player : players){
            int humanPlayers = players.size() - 1;

            if(player == dealer){
                Card dealerCard = player.getFirstCard();
                System.out.println("The Dealer shows his " + dealerCard.getRank() + " of " + dealerCard.getSuit());
            }else{
                playersTurn(player, game);

            }
        }
        playerCanWin = remainingPlayers(players);
        dealerCanWin = dealersTurn(dealer, game);



        if(playerCanWin || dealerCanWin){

            Player winner = game.checkWinner();

        }

        System.out.println("");

    }

    private static boolean dealersTurn(Dealer dealer, Game game) {

        boolean dealerCanWin = true;

        ArrayList<Card> dealerCards = dealer.getHand();
        Card card1 = dealerCards.get(0);
        Card card2 = dealerCards.get(1);

        System.out.println("The deal shows his starting to cards they are a " + card1.getRank() + " of " + card1.getSuit() + " and a "
                + card2.getRank() + " of " + card2.getSuit() + " totaling " + dealer.getHandValue());
        System.out.println("The Dealer can't stick on less than 16 and will stick at anything higher");
        
        while (dealer.getHandValue() < 16 && remainingPlayers(game.getPlayers()) ){
            game.drawExtraCard(dealer);

            int lastIndex = dealer.getHand().size() - 1;
            Card newCard = dealer.getHand().get(lastIndex);

            System.out.println("The dealer drew a " + newCard.getRank() + " of " + newCard.getSuit());

            if(dealer.getHandValue() > 21 && dealer.getHand().contains(Rank.ACEHIGH)){
                while (dealer.getHandValue() > 21){
                    int index = dealer.getHand().indexOf(Rank.ACEHIGH);
                    Card ace = dealer.getHand().get(index);

                    ace.setRank(Rank.ACELOW);

                    dealer.addCard(ace);
                }
            }else if(dealer.getHandValue() > 21 ){
                System.out.println("The Dealer has gone Bust every remaining player will win");
                dealerCanWin = false;
            }
        }

        if(dealerCanWin){
            dealer.setCanWin(true);
        }
        System.out.println("The Dealer ends on " + dealer.getHandValue() );

        return dealerCanWin;
    }

    private static boolean remainingPlayers(ArrayList<Player> players) {
        players.remove(0);

        boolean playersRemain = false;

        for(Player player : players){
            if(player.getHandValue() <= 21 ){
                playersRemain = true;
            }
        }

        return playersRemain;
    }

    private static void playersTurn(Player player, Game game) {

        ArrayList<Card> playerCards = player.getHand();

        Card card1 = playerCards.get(0);
        Card card2 = playerCards.get(1);
        System.out.println(player.getName() + ": You have a " + card1.getRank() + " of " + card1.getSuit() + " and a "
                + card2.getRank() + " of " + card2.getSuit());

        System.out.println("Your Current hand Value is " + player.getHandValue() + ". It is advised to stick if you have over 16");
        System.out.println("Would you like to Hit (draw a card) or Stick (stop drawing cards)");

        Scanner scanner = new Scanner(System.in);

        Boolean valid = false;
        String response = scanner.next();

        while (valid == false) {


            response.toLowerCase();

            if (response.equals("hit")) {
                game.drawExtraCard(player);

                if(player.getHandValue() > 21){

                    int lastIndex = player.getHand().size() - 1;
                    Card newCard = player.getHand().get(lastIndex);
                    System.out.println("You got a " + newCard.getRank() + " of " + newCard.getSuit());

                    if (player.getHand().contains(Rank.ACEHIGH)) {
                        System.out.println("As you have at least one Ace in your hand it's value is now 1 to prevent you from going bust");
                        while (player.getHandValue() > 21){
                            int index = player.getHand().indexOf(Rank.ACEHIGH);
                            Card ace = player.getHand().get(index);

                            ace.setRank(Rank.ACELOW);

                            player.addCard(ace);
                        }
                        System.out.println("Your hand value is now " + player.getHandValue());
                    }else {

                        System.out.println("This bring your total to " + player.getHandValue() + " as this is over 21 you have gone bust");


                    }
                    valid = true;

                }else{

                    int lastIndex = player.getHand().size() - 1;
                    Card newCard = player.getHand().get(lastIndex);
                    System.out.println("You got a " + newCard.getRank() + " of " + newCard.getSuit());
                    System.out.println("This bring your total to " + player.getHandValue() + " as this is under or 21 you can hit or stick again. It is advised to stick if you have over 16");
                    System.out.println("Would you like to Hit (draw a card) or Stick (stop drawing cards)");

                    response = scanner.next();
                    response.toLowerCase();

                }
            } else if(response.equals("stick")){
                System.out.println("You have Stuck on " + player.getHandValue());
                player.setCanWin(true);
                valid = true;
            }else{
                System.out.println(response + " is a not a valid response please only enter Hit or Stick");
            }
        }

    }

    private static Player nameCheck(String name) {
        Scanner scanner = new Scanner(System.in);
        Player newPlayer = null;
        Boolean valid = false;


        while (valid == false) {

            System.out.println("Your name is " + name + " if this is correct enter Yes otherwise enter No");

            String response = scanner.next();
            response.toLowerCase();

            if (response.equals("yes")) {
                newPlayer = new Player(name);
                valid = true;
            }else if(response.equals("no")){

                System.out.println("Please enter a new your name");
                name = scanner.next();
                name.toLowerCase();


            }else{
                System.out.println(response + " is a not a valid response please only enter Yes or No");
            }
        }

        return newPlayer;
    }
}