package pl.sdacademy.threads;

public class SynchronizedBench {
    // Liczba dostępnych miejsc na ławce.
    private int seats;
    // Liczba zajętych miejsc.
    private int takenSeats;

    public SynchronizedBench(int seats) {
        // Tworząc obiekt klasy bench określamy liczbę dostępnych miejsc.
        this.seats = seats;
        // Na starcie nie będzie miejsc zajętych.
        this.takenSeats = 0;
    }

    // Metoda służąca do zajęcia miejsca na łąwce.
    public synchronized void takeASeat(String name) {
        // Jeśli na ławce mamy wolne miejsce
        if (takenSeats < seats) {
            // Piszemy informację o tym, że możemy zająć miejsce.
            System.out.println(name + " siada na ławce");
            // Zwiększamy liczbę zajętych miejsc
            takenSeats++;
            // Wypisujemy ile zostało wolnych miejsc.
            System.out.println(name + " mówi, że na ławce zostało "
                    + (seats - takenSeats) + " miejsce");
        } else {
            // Jeśli na ławce nie ma wonlych miejsc, wypisujemy informację.
            System.out.println(name + " mówi, że brak wolnych miejsc");
        }
    }

}
