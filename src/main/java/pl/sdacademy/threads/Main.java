package pl.sdacademy.threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Jako że pracujemy na wątkach, aby poprawnie wyświetlać rezultat w konsoli, najlepiej na raz odkomentuj
        // wywołanie tylko jednej z poniższych metod.
        mainThreadExample();

        //writerThreadExample();

        //benchSitterExample();

        //synchronizedBenchSitterExample();
    }

    private static void mainThreadExample() {
        System.out.println("--------------mainThreadExample--------------");
        // Jeśli chcemy w tym samym czasie wykonywać więcej niż jedną operację, możemy użyć wielowątkowości.
        // Dotychczas pracowaliśmy na aplikacji bazującej na jednym wątku (głównym).
        System.out.println("A");
        // Wątek możemy uśpić za pomocą metody statycznej sleep z klasy Thread, gdzie jako parametr przekazujemy
        // liczbę milisekund, na którą chcemy uśpić wątek. Metoda ta może wyrzucić wyjątek InterruptedException
        // (wtedy gdy działanie wątku zostanie przerwane "z zewnątrz").
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Po pięciu sekundach uśpienia zostanie wypisane w konsoli B.
        System.out.println("B");
        System.out.println("----------------------------");
    }

    private static void writerThreadExample() {
        System.out.println("--------------writerThreadExample--------------");
        // Nowe wątki możemy utworzyć tworząc obiekty klasy Thread. Jednym ze sposobów na utworzenie takiego obiektu
        // jest użycie konstruktora, w którym za parametr przekażemy implementację interfejsu Runnable.
        // Klasa WriterRunnable implementuje interfejs Runnable, więc instancja klasy WriterRunnable jest dobrym
        // kandydatem na taki parametr.
        Thread writerThread1 = new Thread(new WriterRunnable("A: ", 10, 1000));
        // Wątek startujemy za pomocą metody start. Utworzony zostanie nowy wątek, w ramach którego zostaną wykonane
        // instrukcje z zaimplementowanej metody run.
        writerThread1.start();
        // Utworzymy drugi wątek. Zwracamy uwagę na to, że kolejność instrukcji wywoływanych przez wątki może się zmieniać
        // (wątki pracują niezależnie i zostały wystartowane w praktycznie tym samym czasie).
        Thread writerThread2 = new Thread(new WriterRunnable("B: ", 10, 1000));
        writerThread2.start();
        System.out.println("----------------------------");
    }

    private static void benchSitterExample() {
        System.out.println("--------------benchSitterExample--------------");
        // Utworzymy obiekt klasy Bench reprezentujący ławkę. Jako parametr konstruktora przekazujemy 1 (ławka
        // będzie miała jedno miejsce).
        Bench bench = new Bench(1);

        // Tworzymy obiekty klasy Thread, reprezentujące siadanie na ławce. Użyjemy lambdy jako parametru konstruktora
        // (bo w interfejsie Runnable jest deklaracja jednej metody (run), więc lambda będzie implementacją
        // tej jedynej metody).
        Thread benchSitter1 = new Thread(() -> bench.takeASeat("Zenon"));
        Thread benchSitter2 = new Thread(() -> bench.takeASeat("Stefan"));
        // Startujemy wątki. Widzimy, że kod mógł nie zadziałać tak, jak planowaliśmy. Dzieje się tak dlatego,
        // że dwa wątki jednocześnie uruchamiają metodę takeASeat tego samego obiektu i jednocześnie siadają na ławce,
        // bo w tym samym czasie sprawdziły, że na ławce jest jeszcze miejsce.
        benchSitter1.start();
        benchSitter2.start();
        System.out.println("----------------------------");
    }

    private static void synchronizedBenchSitterExample() {
        System.out.println("--------------synchronizedBenchSitterExample--------------");
        // Przykład będzie analogiczny do poprzedniego, z tymże teraz użyjemy klasy z synchronizowaną metodą
        // takeASeat.
        SynchronizedBench bench = new SynchronizedBench(1);

        Thread benchSitter1 = new Thread(() -> bench.takeASeat("Zenon"));
        Thread benchSitter2 = new Thread(() -> bench.takeASeat("Stefan"));

        // Po wystartowaniu wątków widzimy, że kod zadziała poprawnie - w jednym czasie tylko jeden wątek ma dostęp
        // do wywołania metody takeASeat obiektu bench.
        benchSitter1.start();
        benchSitter2.start();
        System.out.println("----------------------------");
    }
}
