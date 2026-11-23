package postclass10.common;

public class DrawLogger implements DeckObserver {
    @Override
    public void update(DeckEvent event) {
        if (event.type() == DeckEvent.Type.DRAWN) {
            System.out.println("Đã rút: " + event.card());
        }
    }
}
