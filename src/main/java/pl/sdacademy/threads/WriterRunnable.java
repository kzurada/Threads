package pl.sdacademy.threads;
/**
 * Implementacja interfejsu Runnable, której można użyć do wypisania wartości od 1 do zadanej, co określoną
 * liczbę milisekund, z zadanym prefiksem.
 */
public class WriterRunnable implements Runnable {
    private String prefix;
    private int n;
    private int sleepTime;

    public WriterRunnable(String prefix, int n, int sleepTime) {
        this.prefix = prefix;
        this.n = n;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        for (int i = 1; i <= n; i++) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(prefix + i);
        }
    }
}
