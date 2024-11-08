package se.mohamad.adventure.model;

public class Room {
    // Deklarerar privata instansvariabler för rummet:
    private String name; // Rummets namn, t.ex. "Kök" eller "Vardagsrum".
    private String description; // En beskrivning av rummet.
    private boolean containsFryingPan; // Spårar om rummet innehåller en stekpanna som spelaren kan plocka upp.

    // Konstruktor som initierar ett nytt rum med namn, beskrivning och om det innehåller en stekpanna
    public Room(String name, String description, boolean containsFryingPan) {
        this.name = name; // Sätter rummets namn
        this.description = description; // Sätter rummets beskrivning
        this.containsFryingPan = containsFryingPan; // Anger om stekpannan finns i rummet från början
    }

    // Getter-metod som returnerar rummets namn
    public String getName() {
        return name;
    }

    // Metod som låter spelaren gå in i rummet och interagera med dess innehåll
    public String enterRoom(Resident resident) {
        if (containsFryingPan) { // Kontroll om rummet innehåller en stekpanna
            resident.setDamage(resident.getDamage() + 3); // Ökar spelarens skada eftersom de nu har stekpannan
            containsFryingPan = false; // Markerar att stekpannan nu har plockats upp och är inte längre i rummet
            return "Du hittar en stekpanna och känner dig starkare!"; // Meddelande till spelaren
        }
        return description; // Returnerar rummets beskrivning om ingen stekpanna finns
    }

    // Getter-metod som returnerar beskrivningen av rummet
    public String getDescription() {
        return description;
    }

    // Setter-metod för att uppdatera beskrivningen av rummet (just nu tom, ingen funktionalitet implementerad)
    public void setDescription(String s) {
        // Denna metod har ingen implementation, vilket innebär att rumsbeskrivningen inte kan ändras i nuläget.
    }
}
