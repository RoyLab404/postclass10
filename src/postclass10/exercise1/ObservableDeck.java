package postclass10.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import postclass10.common.Card;
import postclass10.common.Deck;
import postclass10.common.DeckEvent;
import postclass10.common.DeckObserver;

public class ObservableDeck {
    private final Deck deck = new Deck();
    private final List<DeckObserver> observers = new ArrayList<>();

    public void addObserver(DeckObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    private void notifyObservers(DeckEvent event) {
        for (DeckObserver observer : List.copyOf(observers)) {
            observer.update(event);
        }
    }

    public void shuffle() {
        deck.shuffle();
        notifyObservers(new DeckEvent(DeckEvent.Type.SHUFFLED, null, deck.size()));
    }

    public void push(Card card) {
        deck.push(card);
        notifyObservers(new DeckEvent(DeckEvent.Type.PUSHED, card, deck.size()));
    }

    public Card draw() {
        Card card = deck.draw();
        notifyObservers(new DeckEvent(DeckEvent.Type.DRAWN, card, deck.size()));
        return card;
    }

    public int size() {
        return deck.size();
    }
}
