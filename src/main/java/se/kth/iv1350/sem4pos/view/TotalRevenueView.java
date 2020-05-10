package se.kth.iv1350.sem4pos.view;

import se.kth.iv1350.sem4pos.model.CashRegisterObserver;

/**
 * This class implements the {@link CashRegisterObserver} interface,
 *              and it can be used to observe a {@link se.kth.iv1350.sem4pos.model.CashRegister} instance.
 */
public class TotalRevenueView implements CashRegisterObserver {
    /**
     * This method is to be called by an observed class, once called,
     *              it prints out the total available cash in the
     *              observed class to the "standard" output stream.
     * @param newBalance The new amount of cash available in the {@link se.kth.iv1350.sem4pos.model.CashRegister}.
     */
    @Override
    public void balanceChanged(double newBalance) {
        String newRevenue = String.format("[TotalRevenueView] New total revenue: %.2f", newBalance);
        System.out.println();
        System.out.println(newRevenue);
        System.out.println();
    }
}
