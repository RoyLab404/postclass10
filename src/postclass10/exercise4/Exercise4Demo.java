package postclass10.exercise4;

import postclass10.common.DrawLogger;
import postclass10.common.SizeStatus;

public class Exercise4Demo {
    public static void main(String[] args) {
        ObservableDeck deck = new ObservableDeck();
        deck.addObserver(new DrawLogger());
        deck.addObserver(new SizeStatus());
        deck.shuffle();
        deck.draw();
        deck.push(deck.draw());
    }
}
