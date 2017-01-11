package designpattern.creational.prototype;

public interface Vehicle extends Cloneable {
    enum Color {
        UNPAINTED, BLUE, BLACK, GREEN, RED, SILVER, WHITE, YELLOW
    };

    Engine getEngine();
    Color getColor();
    void paint(Color color);

    Object clone();
}
