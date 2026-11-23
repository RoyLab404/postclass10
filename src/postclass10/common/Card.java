package postclass10.common;

import java.util.Objects;

public record Card(Rank rank, Suit suit) {
    public enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    public Card {
        Objects.requireNonNull(rank);
        Objects.requireNonNull(suit);
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
