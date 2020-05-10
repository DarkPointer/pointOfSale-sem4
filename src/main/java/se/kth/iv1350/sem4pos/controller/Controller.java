package se.kth.iv1350.sem4pos.controller;

import se.kth.iv1350.sem4pos.loghandlers.ExceptionLogger;
import se.kth.iv1350.sem4pos.integration.*;
import se.kth.iv1350.sem4pos.model.*;

/**
 * Manages data and interactions between the view and the model.
 */
public class Controller {
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private CashRegister cashRegister;
    private PaymentHandler paymentHandler;
    private Sale currentSale;
    private Printer printer;
    private ExceptionLogger devLogger;
    private ExceptionLogger userLogger;

    /**
     * Creates a new instance of the {@link Controller} class.
     * @param systemCreator An instance of the external {@link SystemCreator}
     *              which will provide the controller with access to external systems.
     * @param devLogger An instance of a class that implements an {@link ExceptionLogger} that will handle notifying the developer
     *              when an exception occurs.
     * @param userLogger An instance of a class that implements an {@link ExceptionLogger} that will handle notifying the user
     *              when an exception occurs.
     * @param printer An instance of the external {@link Printer}
     *              which will provide the controller with access to the printer.
     */
    public Controller(SystemCreator systemCreator, ExceptionLogger devLogger, ExceptionLogger userLogger, Printer printer) {
        this.accountingSystem = systemCreator.getAccountingSystem();
        this.inventorySystem = systemCreator.getInventorySystem();
        this.cashRegister = new CashRegister();
        this.paymentHandler = new PaymentHandler();
        this.devLogger = devLogger;
        this.userLogger = userLogger;
        this.printer = printer;
    }

    /**
     * Starts a new sale process.
     */
    public void startSale() {
        this.currentSale = new Sale();
    }

    /**
     * Registers an item in the current sale.
     * @param itemId The identifier of the item to be registered.
     * @param quantity The quantity of the item to be registered
     * @return The registration information including item description, price,
     *               and running total (including VAT).
     */
    public RegistrationInfoDTO registerItem(String itemId, int quantity) {
        RegistrationInfoDTO registrationInfo = null;
        try
        {
            ItemInfoDTO itemInfo = this.inventorySystem.getItemInfo(itemId);
            registrationInfo = currentSale.registerItem(itemInfo, quantity);

        }
        catch (ItemNotFoundException | DatabaseUnreachableException exception)
        {
            devLogger.log(exception);
            userLogger.log(exception);
        }
        return registrationInfo;
    }

    /**
     * Ends the current sale.
     * @return The total price for the current sale including VAT.
     */
    public double endSale() {
        double totalPriceInfo = this.currentSale.endSale();
        return totalPriceInfo;
    }


    /**
     * Passes a payment process to the {@link PaymentHandler} and sends sale information
     *               to external systems.
     * and issues a command to print the receipt.
     * @param amountPaid The amount of cash paid by the customer.
     * @return The amount of change to return to the customer.
     */
    public double makePayment(double amountPaid) {
        double changeAmount = this.paymentHandler.handlePayment(this.cashRegister, this.currentSale, amountPaid);
        this.currentSale.updatePaymentDetails(amountPaid, changeAmount);
        this.saveSaleLog();
        this.printReceipt();
        return changeAmount;
    }

    /**
     * Registers a new <code>CashRegister</code> observer.
     * @param cashRegisterObserver An instance of a class implementing the {@link CashRegisterObserver} interface.
     */
    public void addCashRegisterObserver(CashRegisterObserver cashRegisterObserver)
    {
        this.cashRegister.addCashRegisterObserver(cashRegisterObserver);
    }


    private void saveSaleLog() {
            SaleDTO saleDTO =  this.currentSale.getSaleInfo();
            this.cashRegister.logCompletedSale(this.currentSale);
            this.accountingSystem.saveSaleLog(saleDTO);
            this.inventorySystem.updateInventory(saleDTO);
    }

    private void printReceipt() {
        this.currentSale.printReceipt(this.printer);
    }
}
