package designpattern.behavioral.observer;

import java.util.ArrayList;

public class ObserverController {
    private ArrayList<Observable> observables = new ArrayList<>();

    public void addObserver(Observable observable) {
        observables.add(observable);
    }

    public void removeObserver(Observable observable) {
        observables.remove(observable);
    }

    public void notifyObservers() {
        observables.forEach(Observable::update);
    }
}
