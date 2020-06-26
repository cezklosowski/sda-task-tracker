package pl.sda;

public class Main {
    public static void main(String[] args) {
        /*
            Narzędzie do obsługi i śledzenia zadań/błędów.
            Twoim zadaniem jest przygotowanie narzędzia pozwalającego śledzić/modyfikować/tworzyć zadania lub błędy (np. w projekcie IT)
            Aplikacja powinna mieć funkcje:
                * Tworzenia zadania
                * "Przesuwanie" zadania do następnego lub poprzedniego stanu.
                    W przypadku zadań: do zrobienia, w trakcie, zrobione.
                    W przypadku błędów: do zrobienia, przyjęte, przetestowane, nie udane.                
                
            Uwaga, pomijam w opisie w większości przypadków tworzenie setterów, getterów i konstruktorów, ale są potrzebne (trzeba je utworzyć).    
            1. W pakiecie pl.sda.model trzymaj klasy reprezentujące model (te w których będziemy tylko trzymać dane).
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
            2. Jak zauważyłaś/eś te dwie klasy mają wspólne dwa pola, może dałoby się coś z tym zrobić? Jakoś uprościć?
            Podpowiedź wraz z sugerowaną nazwą (pisane od tyłu): )dnarrEesaB( awominona asalk
            3. Utwórz intefrejs ErrandState z metodami:
                * ErrandState nextState()
                * ErrandState prevState()
                * String getMessage();
            4. Wszystkie poniższe klasy powinny implementować ErrandState:
               * Klasy, będące statusami zadań (statusy błędów będą wspomniane później):
                    * wszystkie poniższe powinny mieć pole typu Task (nazwa z podpowiedzi) abyśmy mogli "powiedzieć"
                      statusowi jakiego zadania dotyczy oraz konstruktor za pomocą którego ustawimy to pole
                       a. Klasa ToDoTaskState:
                          * Powinna zaimplementować metodę nextState():
                                tak aby zwracać nowy obiekt typu InProgressTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          * Powinna zaimplementować metodę prevState():
                                tak aby zwracać ten sam status, bo nie da się z ToDo przejść do tyłu, a więc powinna zwracać "this"
                          * getMessage() powinno zwracać "ToDo - <czas utworzenia zadania>"
                       b. Klasa InProgressTaskState:
                          * Powinna mieć pole przechowujące czas utworzenia obiektu(created)
                          * Powinna zaimplementować metodę nextState():
                                tak aby zwracać nowy obiekt typu DoneTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          * Powinna zaimplementować metodę prevState():
                                tak aby zwracać nowy obiekt typu ToDoTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          * getMessage() powinno zwracać "In progress - <czas teraz - czas utworzenia obiektu>"
                       c. Klasa DoneTaskState:
                          * Powinna mieć pole przechowujące czas utworzenia obiektu(created)
                          * Powinna zaimplementować metodę nextState():
                                tak aby zwracać ten sam status, bo nie da się z Done przejść do przodu, a więc powinna zwracać "this"
                          * Powinna zaimplementować metodę prevState():
                                tak aby zwracać nowy obiekt typu InProgressTaskState (do konstruktora przekaż zadanie z tego statusu) i ustawić task.setCurrentState na nowo utworzony obiekt.
                          * getMessage() powinno zwracać "Done - <estymowany czas trwania zadania> - <czas utworzenia zadania> - <czas utworzenia obiektu>"
                          
               Objaśnienie: w <> zawarłem informację, że należy wykonać operację, a nie wypisać ten tekst.
            5. Spraw, aby w klasie odpowiedzialnej za błąd i klasie odpowiedzialnej za zadanie znalazło się pole ErrandState o nazwie currentState.
            6. Testujemy. W mainie (tutaj):
               a. Stwórz obiekt Zadania i przypisz do zmiennej
               b. Nadaj mu tytuł, przypisz czas utworzenia i estymowany czas.
               c. Do obiektu zadania przypisz status (w polu currentState) new ToDoTaskState()
               d. Pobierz wiadomość task.getCurrentState().getMessage(), sprawdź czy pojawiła się oczekiwana wiadomość 
               "ToDo - <czas utworzenia zadania>"
               e. Z obiektu pobierz aktualny status i wywołaj na nim nextState(), a więc task.getCurrentState().nextState()
               d. Pobierz wiadomość task.getCurrentState().getMessage(), sprawdź czy pojawiła się oczekiwana wiadomość 
               "In progress - <czas teraz - czas utworzenia obiektu>"
               f. Z obiektu pobierz aktualny status i wywołaj na nim nextState(), a więc task.getCurrentState().nextState()  
               g. Pobierz wiadomość task.getCurrentState().getMessage(), sprawdź czy pojawiła się oczekiwana wiadomość 
               "Done - <estymowany czas trwania zadania> - <czas utworzenia zadania> - <czas utworzenia obiektu>"
               f. Spróbuj przejść jeszcze dalej i sprawdź czy zostanie na tym samym statusie
               task.getCurrentState().nextState(), i walidacja czy task.getCurrentState().getMessage() nadal zwraca Done                                                                                     
            7. Zauważ, że moglibyśmy dodać kolejny status przez modyfikację np. InProgressTaskState tak, aby nextState() zwracało TestingTaskState
            Albo moglibyśmy utworzyć zupełnie inną niezależną ścieżkę postępu i jedne zadania miałyby statusy: 
            ToDo -> InProgress -> Done
            a inne np.: 
            Created -> Assigned -> Removed
            
            8. Utwórz listę (ewentualnie tablicę) typu BaseErrand
            9. Utwórz proste menu konsolowe z opcjami: 
                a. Dodaj zadanie
                   * Pytamy użytkownika o tytuł, priorytet i estymowany czas
                   * Tworzymy nowe zadanie, ustawiamy tytuł, priorytet i estymowany czas. currentState ustaw na new TodoTaskState(task) - pierwszy status
                   * Utworzone zadanie dodajemy do listy utworzonej w 8.
                b. Przesuń zadanie do przodu
                   * Zapytaj użytkownika o które z kolei zadanie chodzi (numer) 
                   * Pobierz z listy zadane przez użytkownika zadanie i wywołaj na nim .getCurrentState().nextState()
                c. Przesuń zadanie do tyłu
                   * Analogicznie jak w 9b, tylko zamiast wywoływać nextState() wywołaj prevState()
                d. Wyświetl listę (wszystko co w niej jest)
                   * Przeiteruj przez całą listę wyświetlając:
                       indeks zadania (i z pętli for)
                       jego tytuł
                       efekt wywołania getCurrentState().getMessage()
            10. Utwórz klasy będące statusami błędów podobnie jak było to opisane w 4.
                Jeśli przy którejkolwiek zmianie statusu został przekroczony czas na zrobienie zadania
                zmień status na FailedBugState (nie udało się go zrobić).
            11. Utwórz w menu opcję "Dodaj błąd" analogicznie jak w 9.a, zauważ, że nie musisz
                implementować 9b ani 9c osobno dla błędu, gdyż użyliśmy interfejsów :)                                                                                 




         */
    }
}
