package designpattern.structural.proxy;

public class EngineDiagnosticTool implements DiagnosticTool {
    @Override
    public void runDiagnosis(Object obj) {
        System.out.println("Starting engine diagnostic tool for " + obj);

        try {
            Thread.sleep(5000);
            System.out.println("Engine diagnosis complete");
        } catch (InterruptedException ex) {
            System.out.println("Engine diagnosis interrupted");
        }
    }
}
