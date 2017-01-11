package designpattern.behavioral.templatemethod;

public class Tomato extends Vegetable {

    protected void plantSeeds() {
        System.out.println("Plant tomato seeds");
    }

    protected void mulch() {
        if (isWarmClimate())
            System.out.println("Mulch around plant");
    }
}
