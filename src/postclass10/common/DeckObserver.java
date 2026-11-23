package postclass10.common;

@FunctionalInterface
public interface DeckObserver {
    void update(DeckEvent event);
}
