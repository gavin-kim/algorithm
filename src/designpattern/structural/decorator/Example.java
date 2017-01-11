package designpattern.structural.decorator;

/**
 * Java I/O classes stand for Decorator design patterns
 *
 * FileInputStream f = new FileInputStream("input.txt");
 *
 * BufferedInputStream b = new BufferedInputStream(f);
 *
 * PushbackInputStream p = new PushbackInputStream(b);
 *
 * Decorator VS State:
 *
 * Same feature: both extends the base concept from the parent
 *
 * Decorator: A new decorator constructor takes the root interface type.
 *            The abstract class always keep the root interface type.
 *            Decorators override or include methods.
 *
 * State:     The abstract class has all default behavior.
 *            Other children classes implement the abstract class and
 *            change behaviors. Some of them can be not supported.
 *
 *            The main class a filed to store the state and
 *            all method the the state abstract class has
 * *            public class Main {
 *                private State state = new DefaultState();
 *
 *                public turnOn() {
 *                    state.turnOn(this);
 *                }
 *                ...
 *
 *                void changeState(Main main);
 *            }
 *            The main class supports changeState method to the group of state.
 *
 *
 *
 *
 */
public class Example {

    public static void main(String[] args) {

        StandardEBook baseModel = new StandardEBook();

        // create new object and decorate it, baseModel -> discreet -> WiFi
        EBook decoratedEBook = new WiFi(new DiscreetAdvertisements(baseModel));

        decoratedEBook.powerOn();
        decoratedEBook.nextPage();
        decoratedEBook.powerOff();
    }
}
