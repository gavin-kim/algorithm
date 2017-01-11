package designpattern.behavioral.observer;

public class StockTrader implements Observable {
    private Subject concreteSubject;
    private int sellPrice;

    public StockTrader(Subject concreteSubject, int sellPrice) {
        this.concreteSubject = concreteSubject;
        this.sellPrice = sellPrice;
        concreteSubject.addObserver(this);
    }

    @Override
    public void update() {
        if (concreteSubject.getPrice() >= sellPrice) {
            System.out.println("Sell" + concreteSubject.getSymbol() + "!");
        }
    }
}
