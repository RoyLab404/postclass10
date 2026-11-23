package postclass10.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        reset(); // Avoid calling an overridable method before subclass fields are initialized.
    }

    private void reset() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public void shuffle() {
        reset();
    }

    public void push(Card card) {
        cards.add(Objects.requireNonNull(card));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("Deck is empty");
        }
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
