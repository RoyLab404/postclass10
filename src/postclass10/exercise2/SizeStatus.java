package postclass10.exercise2;

public class SizeStatus implements DeckObserver {
    @Override
    public void update(DeckView view) {
        System.out.println("Số lá hiện tại: " + view.size());
    }
}
