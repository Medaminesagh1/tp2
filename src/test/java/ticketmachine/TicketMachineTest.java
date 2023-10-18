package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}
	@Test
	void cannotPrintTicketInsufficientBalance() {
		assertFalse(machine.printTicket(), "Le ticket ne devrait pas être imprimé avec une balance insuffisante");
	}
	@Test
	void canPrintTicketSufficientBalance() {
		machine.insertMoney(PRICE); // Montant suffisant pour le ticket
		assertTrue(machine.printTicket(), "Le ticket devrait être imprimé avec une balance suffisante");
	}
	@Test
	void balanceDecreasesAfterPrinting() {
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(0, machine.getBalance(), "La balance devrait être réduite à zéro après l'impression");
	}
	@Test
	void totalAmountIsUpdatedAfterPrinting() {
		machine.insertMoney(PRICE);
		assertEquals(0, machine.getTotal(), "Le montant total ne devrait pas être mis à jour avant l'impression");
		machine.printTicket();
		assertEquals(PRICE, machine.getTotal(), "Le montant total devrait être mis à jour après l'impression");
	}
	@Test
	void refundReturnsCorrectAmount() {
		machine.insertMoney(30);
		assertEquals(0, machine.refund(), "Le montant rendu devrait être égal à la balance");
	}
	@Test
	void refundResetsBalanceToZero() {
		machine.insertMoney(30);
		machine.refund();
		assertEquals(0, machine.getBalance(), "La balance devrait être remise à zéro après le remboursement");
	}
	@Test
	void cannotInsertNegativeAmount() {
		assertThrows(IllegalArgumentException.class, () -> machine.insertMoney(-10), "Une exception devrait être levée pour un montant négatif");
	}
	@Test
	void cannotInitializeMachineWithNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> new TicketMachine(-10), "Une exception devrait être levée pour un prix négatif");
	}
}


