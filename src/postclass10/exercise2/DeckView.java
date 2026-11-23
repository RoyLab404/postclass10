package postclass10.exercise2;

import postclass10.common.Card;

public interface DeckView {
    int size();
    ChangeType lastChange();
    Card lastDrawn();

    enum ChangeType { SHUFFLED, DRAWN, PUSHED }
}
