module lab05 {
    requires java.desktop;
    /**
     *Konrad Pempera
     * Celem projektu było oprogramowanie symulacji laboratorium, w którym laboranci (worker) karmią zwierzęta (animal)
     * W  przypadku gdy zwierzęta nie mają jedzenia, tracą zdrowie, gdy całkowicie je stracą umierają
     * Zdrowie zwierząt się regeneruje gdy stan ich pożywienia jest większy niż 80
     * Gdy zwierze starci całe zdrowie umiera, pracownicy nie mogą już go nakarmić
     * Aplikacja została zwizualzowana z użyciem bilbioteki SWING
     * Za pomocą przycisków w górej części ekranu można dodać lub usunąc pracownika oraz dodać lub usunąc zwięrzę
     * w dolnej części ekranu znajdują się przyciski sterujące 1) szybkością spożywania pożywienia przez zwierzęta 2) szybkością poruszania się pracowników, 3) szybkoscią uzupełniania zbiorniczków przez pracowników
     * Aktualizacja widoku realizowana jest poprzez przechwytywanie zdarzeń generowanych przez timer zewnętrzny co 10 ms.
     * każdy pracownik oraz każde zwierze jest osobnym wątkiem
     * Z założenia pracownicy mogą uzupełniać swoje zbiorniczki rozłącznie tzn. pożywienie może pobierać tylko jeden pracownik, w celu implementacji takiego rozwiązania zastosowano synchronizacje na bloku kodu
     * Podczas napełniania zbiorniczka z jedzeniem przez pracownika, zwierze nie może z zbiorniczka korzystać, w celu implementacji tego rozwiązania zastosowano synchronizacje na metodzie
     * Jako ze niedokarmiane zwierze traci zdrowie, w ostatniej fazie jego życia, zwierze ustawia status martwego. Niemniej, w jednoczesnej chwili zwierze może zostać nakarmione, jednakże mimo tego umrze. Aby uniknąć takiej sytuacji została zastosowana odpowiednia synchronizacja
     * Ostatnim synchronizowanym elementem jest metoda odpowiadająca za przemieszczanie się pracowników, ma ona na celu wykluczyć sytuacje gdy pracownicy jednocześnie sprawdzą czy miejsce jest wolne i z racji że żaden jeszcze nie zajął obaj je zajmą
     * Po lewej stronie znajduje się animacja ukazująca status napełniania zbiorniczka pracownika
     * aplikacja została skompliwona za pomocą wywołania z katalogu gdzie znajuje się aplikacja następujących poleceń: javac -d bin src\controller\Controller.java src\controller\Movement.java src\controller\Parameters.java src\controller\Sensors.java
     src\controller\Storage.java src\main\Application.java src\threads\AnimalThread.java src\threads\WorkerThread.java src\view\AppFrame.java src\view\DrawPanel.java src\view\DrawSpeeds.java
     * Plik jar został wygenerowany z następującego polecenia
     *jar -cfv lab05.jar -C bin .
     *Plik jar jest możliwy do uruchomienia z następującego polecenia
     * java -p lab05.jar -m lab05/main.Application
     */
}