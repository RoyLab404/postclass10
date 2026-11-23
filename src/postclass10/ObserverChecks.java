package postclass10;

import java.util.ArrayList;
import java.util.List;

import postclass10.common.Card;
import postclass10.common.DeckEvent;

public class ObserverChecks {
    public static void main(String[] args) {
        checkPush();
        checkPull();
        checkSuperclass();
        checkSubclass();
        System.out.println("OK: 4 bài Observer");
    }

    private static void checkPush() {
        postclass10.exercise1.ObservableDeck deck = new postclass10.exercise1.ObservableDeck();
        List<DeckEvent> events = new ArrayList<>();
        deck.addObserver(events::add);
        Card card = deck.draw();
        deck.push(card);
        deck.shuffle();
        check(events.size() == 3, "8.1: số thông báo");
        check(events.get(0).type() == DeckEvent.Type.DRAWN && events.get(0).card().equals(card) && events.get(0).size() == 51, "8.1: draw push data");
        check(events.get(1).type() == DeckEvent.Type.PUSHED && events.get(1).size() == 52, "8.1: push");
        check(events.get(2).type() == DeckEvent.Type.SHUFFLED && events.get(2).size() == 52, "8.1: shuffle");
    }

    private static void checkPull() {
        postclass10.exercise2.ObservableDeck deck = new postclass10.exercise2.ObservableDeck();
        List<String> observations = new ArrayList<>();
        deck.addObserver(view -> observations.add(view.lastChange() + ":" + view.size() + ":" + view.lastDrawn()));
        Card card = deck.draw();
        deck.push(card);
        deck.shuffle();
        check(observations.get(0).equals("DRAWN:51:" + card), "8.2: pull draw");
        check(observations.get(1).startsWith("PUSHED:52:"), "8.2: pull push");
        check(observations.get(2).equals("SHUFFLED:52:null"), "8.2: pull shuffle");
    }

    private static void checkSuperclass() {
        postclass10.exercise3.ObservableDeck deck = new postclass10.exercise3.ObservableDeck();
        List<DeckEvent> events = new ArrayList<>();
        deck.addObserver(events::add);
        deck.draw();
        check(events.size() == 1 && events.get(0).size() == 51, "8.3: lớp cha thông báo");
    }

    private static void checkSubclass() {
        postclass10.exercise4.ObservableDeck deck = new postclass10.exercise4.ObservableDeck();
        List<DeckEvent> events = new ArrayList<>();
        deck.addObserver(events::add);
        deck.draw();
        deck.shuffle();
        check(events.size() == 2 && events.get(0).size() == 51 && events.get(1).size() == 52, "8.4: lớp con thông báo");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
