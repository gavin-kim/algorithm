package designpattern.behavioral.observer;

/**
 * Observable design pattern defines  1:M relationship
 *
 * ObserverController is responsible for:
 *
 *      Attaching and detaching observers
 *      Notifying attached observers when the event happens
 *
 *      Observable -> StockView    implement update()
 *                 -> StockTrader  implement update()
 *
 *      ObserverController(Subject) -> observers.forEach(do something);
 *
 *
 *      Subject can add, remove and notify observable objects
 *
 */


public class Example {
    public static void main(String[] args) {


        Subject concreteSubject = new Subject("IBM", 250);

        /**
         * when creating a new observable object
         * StockView and StockTrader addObserver(subject) in the constructor
         * */
        StockView sv = new StockView(concreteSubject);
        StockTrader st = new StockTrader(concreteSubject, 253);

        concreteSubject.setPrice(251);
        concreteSubject.setPrice(252);
        concreteSubject.setPrice(253);
    }
}
