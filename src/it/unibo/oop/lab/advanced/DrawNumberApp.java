package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    /**
     * 
     */
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * @throws IOException 
     * 
     */
    public DrawNumberApp() throws IOException {
        this.model = new DrawNumberImpl(getConfigYaml("minimum"), getConfigYaml("maximum"), getConfigYaml("attempts"));
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    private int getConfigYaml(final String str) throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream("config.yml");
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            line = br.readLine();
            while (line != null) {
                final StringTokenizer tokenizer = new StringTokenizer(line);
                if (tokenizer.nextToken(":").equals(str)) {
                    tokenizer.nextToken(" ");
                    return Integer.parseInt(tokenizer.nextToken());
                } else {
                    line = br.readLine();
                }
            }
        }
        return -1;
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws IOException 
     */
    public static void main(final String... args) throws IOException {
        new DrawNumberApp();
    }

}
