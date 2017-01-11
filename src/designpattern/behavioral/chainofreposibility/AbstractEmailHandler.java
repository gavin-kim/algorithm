package designpattern.behavioral.chainofreposibility;

public abstract class AbstractEmailHandler implements EmailHandler {

    private EmailHandler nextHandler;

    protected abstract String[] getMatchingWords();
    protected abstract void handleHere(String email);

    @Override
    public EmailHandler setNextHandler(Class<? extends EmailHandler> c) {
        try {
            nextHandler = c.newInstance();
            return nextHandler;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void processHandler(String email) {
        boolean wordFound = false;

        // If no words to match against then this object can handle
        if (getMatchingWords().length == 0)
            wordFound = true;
        else {
            for (String word : getMatchingWords()) {
                if (email.contains(word)) {
                    wordFound = true;
                    break;
                }
            }
        }

        // Can we handle email in this object?
        if (wordFound)
            handleHere(email);
        else if (nextHandler != null)
            // forward to the next in chain
            nextHandler.processHandler(email);
    }
}
