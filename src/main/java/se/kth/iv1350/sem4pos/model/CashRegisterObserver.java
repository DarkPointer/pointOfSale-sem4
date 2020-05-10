package se.kth.iv1350.sem4pos.model;

/**
 * This interface specifies the requirements of an object that observes a {@link CashRegister} instance.
 */
public interface CashRegisterObserver {
    /**
     * A method that gets called by the observed class when the balance available
     *              in the {@link CashRegister} gets changed.
     * @param newBalance The new amount of cash available in the {@link CashRegister}.
     */
    void balanceChanged(double newBalance);
}
