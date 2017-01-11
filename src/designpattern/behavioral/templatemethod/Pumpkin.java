package designpattern.behavioral.templatemethod;

public class Pumpkin extends Vegetable {

    protected void plantSeeds() {
        System.out.println("Plant pumpkin seeds");
    }

    protected void prune() {
        System.out.println("Selectively prune new side shoots");
    }
}
