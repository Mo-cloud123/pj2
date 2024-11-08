package se.mohamad.adventure.model;

public class Burglar extends Entity {
    // Konstruktor för klassen Burglar, som representerar en inbrottstjuv.
    public Burglar() {
        // Anropar konstruktorn för den abstrakta basklassen Entity med specificerade värden:
        // - Rollen "Burglar"
        // - Hälsa satt till 12
        // - Skada satt till 4 (hur mycket skada tjuven kan orsaka per slag)
        super("Burglar", 12, 4);
    }
}


