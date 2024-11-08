package se.mohamad.adventure.model;

public class Resident extends Entity {
    // Konstruktor för klassen Resident, som representerar spelaren eller den boende.
    public Resident() {
        // Anropar basklassens konstruktor (Entity) och sätter:
        // - Rollen som "Resident" (vilket beskriver att detta är en boende/spelarkaraktär)
        // - Hälsa till 12 (startvärdet för spelarens hälsa)
        // - Skada till 3 (startvärdet för spelarens skada)
        super("Resident", 12, 3);
    }

    // Metod för att uppdatera spelarens skada (damage) när de t.ex. hittar stekpannan
    public void setDamage(int damage) {
        // Här använder vi basklassens skyddade variabel 'damage' och uppdaterar dess värde direkt
        super.damage = damage; // Ändrar spelarens skada till det nya värdet som skickas in
    }
}
