package postclass10.common;

public record DeckEvent(Type type, Card card, int size) {
    public enum Type { SHUFFLED, DRAWN, PUSHED }
}
