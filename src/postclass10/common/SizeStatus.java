package postclass10.common;

public class SizeStatus implements DeckObserver {
    @Override
    public void update(DeckEvent event) {
        System.out.println("Số lá hiện tại: " + event.size());
    }
}
