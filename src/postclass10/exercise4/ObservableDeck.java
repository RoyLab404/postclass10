package postclass10.exercise4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import postclass10.common.Card;
import postclass10.common.Deck;
import postclass10.common.DeckEvent;
import postclass10.common.DeckObserver;

public class ObservableDeck extends Deck {
    private final List<DeckObserver> observers = new ArrayList<>();

    public void addObserver(DeckObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    private void notifyObservers(DeckEvent event) {
        for (DeckObserver observer : List.copyOf(observers)) {
            observer.update(event);
        }
    }

    @Override
    public void shuffle() {
        super.shuffle();
        notifyObservers(new DeckEvent(DeckEvent.Type.SHUFFLED, null, size()));
    }

    @Override
    public void push(Card card) {
        super.push(card);
        notifyObservers(new DeckEvent(DeckEvent.Type.PUSHED, card, size()));
    }

    @Override
    public Card draw() {
        Card card = super.draw();
        notifyObservers(new DeckEvent(DeckEvent.Type.DRAWN, card, size()));
        return card;
    }
}
