package designpattern.behavioral.chainofreposibility;

public class SpamEmailHandler extends AbstractEmailHandler {



    @Override
    protected String[] getMatchingWords() {
        return new String[]{"viagra", "pills", "medicines"};
    }

    @Override
    protected void handleHere(String email) {
        System.out.println("Spam: " + email);
    }
}
