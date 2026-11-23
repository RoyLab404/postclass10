package postclass10.exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class ObservableSupport<T> {
    private final List<Consumer<T>> observers = new ArrayList<>();

    public void addObserver(Consumer<T> observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    protected void notifyObservers(T event) {
        for (Consumer<T> observer : List.copyOf(observers)) {
            observer.accept(event);
        }
    }
}
