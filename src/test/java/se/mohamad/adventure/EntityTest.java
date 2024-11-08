package se.mohamad.adventure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.mohamad.adventure.model.Burglar;
import se.mohamad.adventure.model.Resident;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private Resident resident;
    private Burglar burglar;


    // Skapar nya Resident och Burglar innan varje test
    @BeforeEach
    public void setUp() {
        resident = new Resident();
        burglar = new Burglar();
    }

    // Testar att punch() minskar Burglar's hälsa
    @Test
    public void testPunchReducesHealth() {
        int initialHealth = burglar.getHealth();
        resident.punch(burglar); // Resident attacker Burglar
        assertEquals(initialHealth - resident.getDamage(), burglar.getHealth(),
                "Burglar's health should decrease after being punched.");
    }

    // Testar att takeHit() minskar Resident's hälsa
    @Test
    public void testTakeHitReducesHealth() {
        int initialHealth = resident.getHealth();
        burglar.punch(resident); // Burglar attacker Resident
        assertEquals(initialHealth - burglar.getDamage(), resident.getHealth(),
                "Resident's health should decrease after taking a hit.");
    }


    // Testar att Resident och Burglar är medvetna från start
    @Test
    public void testIsConsciousReturnsTrue() {
        assertTrue(resident.isConscious(), "Resident should be conscious initially.");
        assertTrue(burglar.isConscious(), "Burglar should be conscious initially.");
    }

    // Testar att isConscious() blir false när hälsan går till 0
    @Test
    public void testIsConsciousReturnsFalseAfterTakingDamage() {
        // Se till att Resident tar tillräckligt med skada för att bli medvetslös
        resident.takeHit(resident.getHealth()); // Sänk Resident's hälsa till 0
        assertFalse(resident.isConscious(), "Resident should not be conscious after taking damage that reduces health to 0.");

        // Se till att Burglar tar tillräckligt med skada för att bli medvetslös
        burglar.takeHit(burglar.getHealth()); // Sänk Burglar's hälsa till 0
        assertFalse(burglar.isConscious(), "Burglar should not be conscious after taking damage that reduces health to 0.");
    }

    // Testar att en enhet blir medvetslös efter flera attacker
    @Test
    public void testIsConsciousAfterMultipleAttacks() {
        // Simulera att Resident attackerar Burglar flera gånger
        while (burglar.isConscious()) {
            resident.punch(burglar);
        }
        assertFalse(burglar.isConscious(), "Burglar should not be conscious after taking enough damage to reach 0 health.");

        // Simulera att Burglar attackerar Resident flera gånger
        while (resident.isConscious()) {
            burglar.punch(resident);
        }
        assertFalse(resident.isConscious(), "Resident should not be conscious after taking enough damage to reach 0 health.");
    }
}
