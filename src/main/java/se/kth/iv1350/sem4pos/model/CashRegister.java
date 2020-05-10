package se.kth.iv1350.sem4pos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The cash register responsible on keeping track of the available balance in the register
 *              and logging completed sales.
 */
public class CashRegister {
    private List<CashRegisterObserver> cashRegisterObservers =
            new ArrayList<CashRegisterObserver>();
    private double currentBalance;

    /**
     * Creates an instance of the {@link CashRegister}.
     */
    public CashRegister() {
        this.currentBalance = 0;
    }

    /**
     * Increases the balance available in the register.
     * @param amount The amount of cash to increase the register balance with.
     */
    public void increaseBalance(double amount)
    {
        this.currentBalance += amount;
        notifyObservers();
    }

    /**
     * Returns the amount of cash present in the cash register.
     * @return A <code>float</code> that holds the amount of cash present in the cash register.
     */
    public double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Logs a completed sale.
     * @param saleInfo A sale data transfer object {@link SaleDTO} containing all available information about a sale.
     */
    public void logCompletedSale(Sale saleInfo) {
    }

    /**
     * Registers a new <code>CashRegister</code> observer.
     * @param cashRegisterObserver An instance of a class implementing the {@link CashRegisterObserver} interface.
     */
    public void addCashRegisterObserver(CashRegisterObserver cashRegisterObserver) {
        cashRegisterObservers.add(cashRegisterObserver);
    }

    private void notifyObservers() {
        for (CashRegisterObserver cashRegisterObserver : cashRegisterObservers)
        {
            cashRegisterObserver.balanceChanged(this.currentBalance);
        }
    }

}
