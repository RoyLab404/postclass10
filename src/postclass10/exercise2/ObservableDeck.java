package postclass10.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import postclass10.common.Card;
import postclass10.common.Deck;

public class ObservableDeck implements DeckView {
    private final Deck deck = new Deck();
    private final List<DeckObserver> observers = new ArrayList<>();
    private ChangeType lastChange;
    private Card lastDrawn;

    public void addObserver(DeckObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    private void notifyObservers() {
        for (DeckObserver observer : List.copyOf(observers)) {
            observer.update(this);
        }
    }

    public void shuffle() {
        deck.shuffle();
        lastChange = ChangeType.SHUFFLED;
        lastDrawn = null;
        notifyObservers();
    }

    public void push(Card card) {
        deck.push(card);
        lastChange = ChangeType.PUSHED;
        notifyObservers();
    }

    public Card draw() {
        Card card = deck.draw();
        lastDrawn = card;
        lastChange = ChangeType.DRAWN;
        notifyObservers();
        return card;
    }

    @Override
    public int size() {
        return deck.size();
    }

    @Override
    public ChangeType lastChange() {
        return lastChange;
    }

    @Override
    public Card lastDrawn() {
        return lastDrawn;
    }
}
