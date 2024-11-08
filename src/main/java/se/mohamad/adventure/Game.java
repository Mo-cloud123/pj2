package se.mohamad.adventure;

import se.mohamad.adventure.model.Resident;
import se.mohamad.adventure.model.Burglar;
import se.mohamad.adventure.model.Room;

import java.util.Scanner;

public class Game {
    // Deklarerar privata instansvariabler för spelets huvudkomponenter:
    private Resident resident; // Representerar spelarens karaktär (en boende).
    private Burglar burglar; // Representerar en tjuv som spelaren kan möta.
    private boolean running; // Spårar om spelet är aktivt eller avslutat.

    // Deklarerar rum som spelaren kan gå in i:
    private Room livingRoom;
    private Room kitchen;
    private Room bedroom;
    private Room hall;
    private Room office;

    // Deklarerar variabler som spårar specifika händelser i spelet:
    private boolean fryingPanFound = false; // Spårar om spelaren har hittat en stekpanna i köket.
    private boolean burglarDefeated = false; // Spårar om spelaren har besegrat tjuven.

    private Scanner scanner; // Används för att läsa in spelarens input.

    // Konstruktor som initierar spelet
    public Game() {
        resident = new Resident(); // Skapar en ny Resident-instans.
        burglar = new Burglar(); // Skapar en ny Burglar-instans.
        running = true; // Sätter igång spelet.
        scanner = new Scanner(System.in); // Initierar Scanner för inmatning.

        // Skapar och initierar rummen med namn, beskrivningar och om det finns något objekt att hämta.
        livingRoom = new Room("Vardagsrummet", "Ett centralt rum där spelet börjar.", false);
        kitchen = new Room("Köket", "Ett rum med doft av mat där en stekpanna ligger synlig på bordet.", true);
        bedroom = new Room("Sovrummet", "Ett tyst sovrum där inget särskilt händer.", false);
        hall = new Room("Hallen", "Här möter du inbrottstjuven!", false);
        office = new Room("Kontoret", "Kontoret med telefon.", false);
    }

    // Metod för att starta spelet
    public void startGame() {
        System.out.println("Du har somnat på soffan och vaknar upp i vardagsrummet efter att ha hört ett ovanligt ljud...");

        // Huvudloopen som kör spelet så länge spelet är igång och spelaren är vid medvetande.
        while (running && resident.isConscious()) {
            System.out.println("\nVar vill du gå? (kök, sovrum, hall, kontor, vardagsrum)");
            String command = scanner.nextLine(); // Läser spelarens kommando

            // Hanterar spelarens kommando och skickar dem till olika metoder beroende på valet
            switch (command.toLowerCase()) {
                case "kök":
                    enterKitchen(); // Gå in i köket
                    break;
                case "sovrum":
                    System.out.println(bedroom.enterRoom(resident)); // Gå in i sovrummet
                    break;
                case "hall":
                    enterHall(); // Gå in i hallen och möt tjuven
                    break;
                case "kontor":
                    enterOffice(); // Gå in i kontoret
                    break;
                case "vardagsrum":
                    System.out.println(livingRoom.enterRoom(resident)); // Gå tillbaka till vardagsrummet
                    break;
                default:
                    System.out.println("Ogiltigt kommando."); // Felmeddelande för ogiltigt kommando
            }
        }
        scanner.close(); // Stänger scanner när spelet är avslutat
    }

    // Metod för att hantera vad som händer när spelaren går in i köket
    private void enterKitchen() {
        if (!fryingPanFound) { // Kollar om stekpannan har hittats
            System.out.println("Du hittar en stekpanna i köket! Vill du plocka upp den? [ja/nej]");
            String choice = scanner.nextLine().toLowerCase(); // Läser spelarens val
            if (choice.equals("ja")) { // Om spelaren väljer att ta stekpannan
                System.out.println("Du plockar upp stekpannan! Din styrka ökar.");
                resident.setDamage(resident.getDamage() + 3); // Ökar spelarens styrka
                fryingPanFound = true; // Markerar att stekpannan nu har hittats
            } else {
                System.out.println("Du valde att inte plocka upp stekpannan.");
            }
        } else {
            System.out.println("Ett rum med doft av mat. Du har redan plockat upp stekpannan och köket är tomt.");
        }
    }

    // Metod för att hantera vad som händer när spelaren går in i hallen
    private void enterHall() {
        System.out.println(hall.enterRoom(resident)); // Skriver ut beskrivningen av hallen
        fightBurglar(); // Startar en strid med tjuven
    }

    // Metod som hanterar striden mellan spelaren och tjuven
    private void fightBurglar() {
        if (!fryingPanFound) { // Kontroll om spelaren har ett vapen (stektpanna)
            System.out.println("Du stöter på inbrottstjuven, men utan ett vapen är du chanslös. Tjuven övermannar dig.");
            running = false; // Avslutar spelet om spelaren inte har något vapen
            return;
        }

        System.out.println("Du stöter på inbrottstjuven! Ett slagsmål börjar!");
        // Loop som fortsätter tills antingen spelaren eller tjuven är besegrad
        while (resident.isConscious() && burglar.isConscious()) {
            resident.punch(burglar); // Spelaren slår tjuven
            if (burglar.isConscious()) {
                burglar.punch(resident); // Tjuven slår tillbaka om hen är vid medvetande
            }
        }

        // Kontroll om resultatet av striden
        if (!resident.isConscious()) {
            System.out.println("Du har förlorat."); // Meddelar att spelaren har förlorat
            running = false; // Avslutar spelet
        } else {
            System.out.println("Du besegrade inbrottstjuven! ");
            System.out.println("Du kommer på att du bör ringa polisen nu när tjuven är besegrad. Gå till kontoret.");
            burglarDefeated = true; // Markerar att tjuven är besegrad
        }
    }

    // Metod för att hantera vad som händer när spelaren går in i kontoret
    private void enterOffice() {
        if (!burglar.isConscious()) { // Om tjuven är besegrad kan spelaren ringa polisen
            System.out.println("Du går in på kontoret och inser att du kan ringa polisen nu när tjuven är besegrad. Vill du ringa polisen? [ja/nej]");
            String choice = scanner.nextLine().toLowerCase(); // Läser spelarens val
            if (choice.equals("ja")) { // Om spelaren väljer att ringa polisen
                System.out.println("Du ringer polisen.");
                System.out.println("Grattis! Du har ringt polisen och spelet är slut! Du har vunnit!");
                running = false; // Avslutar spelet efter att spelaren har vunnit
            } else {
                System.out.println("Du valde att inte ringa polisen.");
            }
        } else {
            System.out.println("Du hör ett ljud i hallen,  det är för stressigt för att kunna ringa polisen just nu.  ");
        }
    }
}
