package postclass10.exercise3;

import postclass10.common.Card;
import postclass10.common.Deck;
import postclass10.common.DeckEvent;

public class ObservableDeck extends ObservableSupport<DeckEvent> {
    private final Deck deck = new Deck();

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
