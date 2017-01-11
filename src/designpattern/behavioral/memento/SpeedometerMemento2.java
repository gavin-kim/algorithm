package designpattern.behavioral.memento;

import java.io.*;

public class SpeedometerMemento2 {

    private static final String FILE_NAME = "speedometer.ser";

    public SpeedometerMemento2(Speedometer2 speedometer2) {

        // Serialize...
        File speedometerFile = new File(FILE_NAME);
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream(speedometerFile)))) {
            oos.writeObject(speedometer2);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Speedometer2 restoreState() {

        // Deserialize...
        File speedometerFile = new File(FILE_NAME);
        try (ObjectInputStream ois = new ObjectInputStream(
            new BufferedInputStream(new FileInputStream(speedometerFile)))) {
            Speedometer2 speedometer2 = (Speedometer2) ois.readObject();
            ois.close();
            return speedometer2;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
