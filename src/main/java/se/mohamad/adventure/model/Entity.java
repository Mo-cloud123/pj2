package se.mohamad.adventure.model;

public abstract class Entity {
    // Privat variabel som beskriver entitetens roll (t.ex. "boende" eller "tjuv").
    private String role;

    // Privat variabel som spårar entitetens hälsa.
    private int health;

    // Skyddad variabel som anger hur mycket skada entiteten kan ge (kan ärvas och modifieras av subklasser).
    protected int damage;

    // Konstruktor som initierar en ny instans av Entity med en roll, hälsa och skada
    public Entity(String role, int health, int damage) {
        this.role = role;       // Sätter entitetens roll
        this.health = health;   // Sätter entitetens start-hälsa
        this.damage = damage;   // Sätter entitetens start-skada
    }

    // Getter-metod som returnerar entitetens roll
    public String getRole() {
        return role;
    }

    // Getter-metod som returnerar entitetens nuvarande hälsa
    public int getHealth() {
        return health;
    }

    // Getter-metod som returnerar entitetens nuvarande skada
    public int getDamage() {
        return damage;
    }

    // Metod som gör att denna entitet slår en annan entitet (toPunch) och orsakar skada
    public void punch(Entity toPunch) {
        toPunch.takeHit(this.damage); // Anropar takeHit på den andra entiteten och applicerar denna entitets skada
    }

    // Metod som reducerar hälsan på denna entitet med en given skademängd
    public void takeHit(int damage) {
        this.health -= damage; // Drar av skademängden från entitetens nuvarande hälsa
    }

    // Metod som kontrollerar om entiteten är vid medvetande (hälsan är över 0)
    public boolean isConscious() {
        return this.health > 0; // Returnerar true om hälsan är över 0, annars false
    }
}
