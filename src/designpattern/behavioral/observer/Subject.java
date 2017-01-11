package designpattern.behavioral.observer;

public class Subject extends ObserverController {
    private String tickerSymbol;
    private int price;

    public Subject(String tickerSymbol, int price) {
        this.tickerSymbol = tickerSymbol;
        this.price = price;
    }

    public String getSymbol() {
        return tickerSymbol;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        notifyObservers();
    }
}
