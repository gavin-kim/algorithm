package designpattern.behavioral.observer;

public class StockView implements Observable {

    private Subject concreteSubject;

    public StockView(Subject concreteSubject) {
        this.concreteSubject = concreteSubject;
        concreteSubject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println(concreteSubject.getSymbol() + " is selling for "  +
            concreteSubject.getPrice());
    }
}
