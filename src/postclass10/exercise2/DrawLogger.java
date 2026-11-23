package postclass10.exercise2;

public class DrawLogger implements DeckObserver {
    @Override
    public void update(DeckView view) {
        if (view.lastChange() == DeckView.ChangeType.DRAWN) {
            System.out.println("Đã rút: " + view.lastDrawn());
        }
    }
}
