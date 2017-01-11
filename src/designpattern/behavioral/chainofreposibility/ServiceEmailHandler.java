package designpattern.behavioral.chainofreposibility;

public class ServiceEmailHandler extends AbstractEmailHandler {



    @Override
    protected String[] getMatchingWords() {
        return new String[]{"service", "repair"};
    }

    @Override
    protected void handleHere(String email) {
        System.out.println("Service: " + email);
    }
}
