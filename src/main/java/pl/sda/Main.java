package pl.sda;

import pl.sda.model.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
            Narzędzie do obsługi i śledzenia zadań/błędów.
            Twoim zadaniem jest przygotowanie narzędzia pozwalającego śledzić/modyfikować/tworzyć zadania lub błędy (np. w projekcie IT)
            Aplikacja powinna mieć funkcje:
                * Tworzenia zadania
                * "Przesuwanie" zadania do następnego lub poprzedniego stanu.
                    W przypadku zadań: do zrobienia, w trakcie, zrobione.
                    W przypadku błędów: do zrobienia, przyjęte, przetestowane, nie udane.                
                
            Uwaga, pomijam w opisie w większości przypadków tworzenie setterów, getterów i konstruktorów, ale są potrzebne (trzeba je utworzyć).    
            DONE 1. W pakiecie pl.sda.model trzymaj klasy reprezentujące model (te w których będziemy tylko trzymać dane).
               Dopóki nie zostanie opisane inaczej w zadaniu wszystkie klasy twórz tam.
               Na podstawie poniższych informacji utwórz klasy:
                a. Zadanie (Task), powinno mieć:
                  * tytuł
                  * priorytet (to jest enum - LOW, MEDIUM, HIGH)
                  * czas utworzenia  (datę i czas)
                  * estymowany czas trwania zadania, który powinien wystarczyć do wykonania zadania (pewnie sprawdzi się klasa Duration)
                b. Błąd (Bug), klasa powinna mieć:
                  * tytuł
                  * priorytet (to jest enum - LOW, MEDIUM, HIGH)
                  * czas utworzenia (datę i czas)
                  * datę w której powinnien zostać rozwiązany (uwaga, to jest coś innego niż estymowany czas!)
            DONE 2. Jak zauważyłaś/eś te dwie klasy mają wspólne dwa pola, może dałoby się coś z tym zrobić? Jakoś uprościć?
            Podpowiedź wraz z sugerowaną nazwą (pisane od tyłu): )dnarrEesaB( awominona asalk
            !zrobiłem klasę abstrakcyjną, a nie anonimową!
            DONE 3. Utwórz intefrejs ErrandState z metodami:
                * ErrandState nextState()
                * ErrandState prevState()
                * String getMessage();
            DONE 4. Wszystkie poniższe klasy powinny implementować ErrandState:
               * Klasy, będące statusami zadań (statusy błędów będą wspomniane później):
                    * wszystkie poniższe powinny mieć pole typu Task (nazwa z podpowiedzi) abyśmy mogli "powiedzieć"
                      statusowi jakiego zadania dotyczy oraz konstruktor za pomocą którego ustawimy to pole
                       a. Klasa ToDoTaskState:
                          DONE * Powinna zaimplementować metodę nextState():
                                tak aby zwracać nowy obiekt typu InProgressTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          DONE * Powinna zaimplementować metodę prevState():
                                tak aby zwracać ten sam status, bo nie da się z ToDo przejść do tyłu, a więc powinna zwracać "this"
                          DONE * getMessage() powinno zwracać "ToDo - <czas utworzenia zadania>"
                       b. Klasa InProgressTaskState:
                          DONE * Powinna mieć pole przechowujące czas utworzenia obiektu(created)
                          DONE * Powinna zaimplementować metodę nextState():
                                tak aby zwracać nowy obiekt typu DoneTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          DONE * Powinna zaimplementować metodę prevState():
                                tak aby zwracać nowy obiekt typu ToDoTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          DONE * getMessage() powinno zwracać "In progress - <czas teraz - czas utworzenia obiektu>"
                       c. Klasa DoneTaskState:
                          DONE * Powinna mieć pole przechowujące czas utworzenia obiektu(created)
                          DONE * Powinna zaimplementować metodę nextState():
                                tak aby zwracać ten sam status, bo nie da się z Done przejść do przodu, a więc powinna zwracać "this"
                          DONE * Powinna zaimplementować metodę prevState():
                                tak aby zwracać nowy obiekt typu InProgressTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          DONE * getMessage() powinno zwracać "Done - <estymowany czas trwania zadania> - <czas utworzenia zadania> - <czas utworzenia obiektu>"
                          
               Objaśnienie: w <> zawarłem informację, że należy wykonać operację, a nie wypisać ten tekst.
            DONE 5. Spraw, aby w klasie odpowiedzialnej za błąd i klasie odpowiedzialnej za zadanie znalazło się pole ErrandState o nazwie currentState.
            DONE 6. Testujemy. W mainie (tutaj):
               DONE a. Stwórz obiekt Zadania i przypisz do zmiennej
               DONE b. Nadaj mu tytuł, przypisz czas utworzenia i estymowany czas.
               DONE c. Do obiektu zadania przypisz status (w polu currentState) new ToDoTaskState()
               DONE d. Pobierz wiadomość task.getCurrentState().getMessage(), sprawdź czy pojawiła się oczekiwana wiadomość
               "ToDo - <czas utworzenia zadania>"
               DONE e. Z obiektu pobierz aktualny status i wywołaj na nim nextState(), a więc task.getCurrentState().nextState()
               DONE d. Pobierz wiadomość task.getCurrentState().getMessage(), sprawdź czy pojawiła się oczekiwana wiadomość
               "In progress - <czas teraz - czas utworzenia obiektu>"
               DONE f. Z obiektu pobierz aktualny status i wywołaj na nim nextState(), a więc task.getCurrentState().nextState()
               DONE g. Pobierz wiadomość task.getCurrentState().getMessage(), sprawdź czy pojawiła się oczekiwana wiadomość
               "Done - <estymowany czas trwania zadania> - <czas utworzenia zadania> - <czas utworzenia obiektu>"
               DONE f. Spróbuj przejść jeszcze dalej i sprawdź czy zostanie na tym samym statusie
               task.getCurrentState().nextState(), i walidacja czy task.getCurrentState().getMessage() nadal zwraca Done                                                                                     
            7. Zauważ, że moglibyśmy dodać kolejny status przez modyfikację np. InProgressTaskState tak, aby nextState() zwracało TestingTaskState
            Albo moglibyśmy utworzyć zupełnie inną niezależną ścieżkę postępu i jedne zadania miałyby statusy: 
            ToDo -> InProgress -> Done
            a inne np.: 
            Created -> Assigned -> Removed
            
            DONE 8. Utwórz listę (ewentualnie tablicę) typu BaseErrand
            9. Utwórz proste menu konsolowe z opcjami: 
                DONE a. Dodaj zadanie
                   DONE * Pytamy użytkownika o tytuł, priorytet i estymowany czas
                   DONE * Tworzymy nowe zadanie, ustawiamy tytuł, priorytet i estymowany czas. currentState ustaw na new TodoTaskState(task) - pierwszy status
                   DONE * Utworzone zadanie dodajemy do listy utworzonej w 8.
                DONE b. Przesuń zadanie do przodu
                   DONE * Zapytaj użytkownika o które z kolei zadanie chodzi (numer)
                   DONE * Pobierz z listy zadane przez użytkownika zadanie i wywołaj na nim .getCurrentState().nextState()
                DONE c. Przesuń zadanie do tyłu
                   DONE * Analogicznie jak w 9b, tylko zamiast wywoływać nextState() wywołaj prevState()
                DONE d. Wyświetl listę (wszystko co w niej jest)
                   DONE * Przeiteruj przez całą listę wyświetlając:
                       indeks zadania (i z pętli for)
                       jego tytuł
                       efekt wywołania getCurrentState().getMessage()
            DONE 10. Utwórz klasy będące statusami błędów podobnie jak było to opisane w 4.
                Jeśli przy którejkolwiek zmianie statusu został przekroczony czas na zrobienie zadania
                zmień status na FailedBugState (nie udało się go zrobić).
            DONE 11. Utwórz w menu opcję "Dodaj błąd" analogicznie jak w 9.a, zauważ, że nie musisz
                implementować 9b ani 9c osobno dla błędu, gdyż użyliśmy interfejsów :)                                                                                 

         */



        /*

        // ZADANIE 6

        Task testTask = new Task("Fix function2", Priority.HIGH, LocalDateTime.now(), Duration.ofSeconds(20));
        testTask.setCurrentState(new ToDoTaskState(testTask, LocalDateTime.now()));
        System.out.println(testTask.getCurrentState().getMessage());
        Thread.sleep(3000);
        testTask.getCurrentState().nextState();
        System.out.println(testTask.getCurrentState().getMessage());
        Thread.sleep(2000);
        testTask.getCurrentState().nextState();
        System.out.println(testTask.getCurrentState().getMessage());
        Thread.sleep(1000);
        testTask.getCurrentState().nextState();
        System.out.println(testTask.getCurrentState().getMessage());

         */

        // ZADANIE 8
        List<BaseErrand> baseErrandList = new ArrayList<>();

        // ZADANIE 9

        Scanner input = new Scanner(System.in);
        String option = "";

        do {
            System.out.println("MENU. Wybierz funkcję (1, 2, 3, 4, q): ");
            System.out.println("1. Dodaj zadanie");
            System.out.println("2. Przesuń zadanie do przodu");
            System.out.println("3. Przesuń zadanie do tyłu");
            System.out.println("4. Wyświetl listę zadań");
            System.out.println("5. Dodaj błąd");
            System.out.println("q. Wyjście");

            option = input.nextLine();


            switch (option) {
                case "1": {

                    System.out.println("Podaj tytuł zadania");
                    String title = input.nextLine();
                    System.out.println("Podaj priorytet zadania (niski, średni, wysoki)");
                    String priorityString = input.nextLine();
                    Priority priority = Priority.HIGH;
                    boolean parser = true;
                    if (priorityString.equalsIgnoreCase("niski")) {
                        priority = Priority.LOW;
                    } else if (priorityString.equalsIgnoreCase("średni")) {
                        priority = Priority.MEDIUM;
                    } else if (priorityString.equalsIgnoreCase("wysoki")) {
                        priority = Priority.HIGH;
                    } else {
                        System.out.println("Wprowadzono błędny priorytet zadania");
                        parser = false;
                    }
                    System.out.println("Podaj estymowany czas wykonania zadania (w sekundach)");
                    long duration = Long.parseLong(input.nextLine());
                    if (parser) {
                        Task task = new Task(title, priority, LocalDateTime.now(), Duration.ofSeconds(duration));
                        task.setCurrentState(new ToDoTaskState(task, LocalDateTime.now()));
                        baseErrandList.add(task);
                    }


                    break;
                }
                case "2": {

                    System.out.println("Podaj numer zadania, które chcesz przesunąć do przodu. ");
                    int errandNumber = Integer.parseInt(input.nextLine());
                    if (errandNumber < 0 || errandNumber > baseErrandList.size()) {
                        System.out.println("Nie ma zadania o podanych numerze");
                    } else {
                        baseErrandList.get(errandNumber).getCurrentState().nextState();
                    }

                    break;
                }
                case "3": {

                    System.out.println("Podaj numer zadania, które chcesz przesunąć do tyłu. ");
                    int errandNumber = Integer.parseInt(input.nextLine());
                    if (errandNumber < 0 || errandNumber > baseErrandList.size()) {
                        System.out.println("Nie ma zadania o podanych numerze");
                    } else {
                        baseErrandList.get(errandNumber).getCurrentState().prevState();
                    }

                    break;
                }
                case "4": {

                    for (int i = 0; i < baseErrandList.size(); i++) {
                        System.out.println(i + ". " + baseErrandList.get(i).getTitle() + " " + baseErrandList.get(i).getCurrentState().getMessage());
                    }

                    break;
                }
                case "5": {

                    System.out.println("Podaj tytuł błędu");
                    String title = input.nextLine();
                    System.out.println("Podaj priorytet błędu (niski, średni, wysoki)");
                    String priorityString = input.nextLine();
                    Priority priority = Priority.HIGH;
                    boolean parser = true;
                    if (priorityString.equalsIgnoreCase("niski")) {
                        priority = Priority.LOW;
                    } else if (priorityString.equalsIgnoreCase("średni")) {
                        priority = Priority.MEDIUM;
                    } else if (priorityString.equalsIgnoreCase("wysoki")) {
                        priority = Priority.HIGH;
                    } else {
                        System.out.println("Wprowadzono nieprawidłowy priorytet błędu");
                        parser = false;
                    }
                    System.out.println("Podaj ostateczny czas rozwiązania błędu (rok, miesiąc, dzień, godzina, minuta)");
                    String terminationTimeString = input.nextLine();
                    String[] terminationTimeArray = terminationTimeString.split(",");
                    int[] terminationTimeInts = new int[terminationTimeArray.length];
                    for (int i = 0; i < terminationTimeArray.length; i++) {
                        terminationTimeInts[i] = Integer.parseInt(terminationTimeArray[i]);
                    }
                    if (parser) {
                        Bug bug = new Bug(title, priority, LocalDateTime.now(), LocalDateTime.of(terminationTimeInts[0], terminationTimeInts[1], terminationTimeInts[2], terminationTimeInts[3], terminationTimeInts[4]));
                        bug.setCurrentState(new ToDoBugState(bug, LocalDateTime.now()));
                        baseErrandList.add(bug);
                    }


                    break;
                }
                case "q": {

                    break;
                }

            }
        } while (!option.equalsIgnoreCase("q"));


    }
}
