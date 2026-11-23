package postclass10.exercise3;

import postclass10.common.DrawLogger;
import postclass10.common.SizeStatus;

public class Exercise3Demo {
    public static void main(String[] args) {
        ObservableDeck deck = new ObservableDeck();
        DrawLogger logger = new DrawLogger();
        SizeStatus status = new SizeStatus();
        deck.addObserver(logger::update);
        deck.addObserver(status::update);
        deck.shuffle();
        deck.draw();
        deck.push(deck.draw());
    }
}
