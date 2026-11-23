package postclass10.exercise2;

public class Exercise2Demo {
    public static void main(String[] args) {
        ObservableDeck deck = new ObservableDeck();
        deck.addObserver(new DrawLogger());
        deck.addObserver(new SizeStatus());
        deck.shuffle();
        deck.draw();
        deck.push(deck.draw());
    }
}
