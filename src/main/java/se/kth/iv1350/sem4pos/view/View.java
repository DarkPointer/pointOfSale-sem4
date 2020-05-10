package se.kth.iv1350.sem4pos.view;

import se.kth.iv1350.sem4pos.controller.Controller;
import se.kth.iv1350.sem4pos.model.RegistrationInfoDTO;

/**
 * A view class capable of performing hardcoded calls to the controller,
 *              simulating a sale process and printing the results out to the console.
 */
public class View {
    private Controller controller;
    /**
     * Creates a new instance of the {@link View}.
     * @param cont An initialized instance of the {@link Controller}.
     */
    public View(Controller cont) {
        this.controller = cont;
        cont.addCashRegisterObserver(new TotalRevenueView());
    }

    /**
     * Simulates a sale process and prints out the results to the console.
     */
    public void simulateSaleProcess() {
        this.controller.startSale();

        System.out.println("==================================================");

        System.out.println();

        RegistrationInfoDTO regInfo = this.controller.registerItem("item1", 1);
        String regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item4", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item1", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item1", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item1", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item4", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item2", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item3", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item4", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item11037", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item1", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);

        regInfo = this.controller.registerItem("item123456789", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);


        regInfo = this.controller.registerItem("item4", 1);
        regViewInfo = this.processRegistrationInformation(regInfo);
        System.out.println(regViewInfo);
        System.out.println();

        double totalInclVAT = this.controller.endSale();
        String totalPriceInfo = String.format("TotalPrice((incl. VAT)): %.2f", totalInclVAT);
        System.out.println(totalPriceInfo);

        double changeAmount = this.controller.makePayment(1000.0d);
        String changeViewAmount = String.format("Change to return to customer: %.2f", changeAmount);
        System.out.println();

        System.out.println(changeViewAmount);

        System.out.println();

    }

    private String processRegistrationInformation(RegistrationInfoDTO regInfo) {
        if(regInfo == null) return "";
        String itemDescription = regInfo.getItemDescription();
        double itemPriceInclVAT = regInfo.getItemPriceInclVAT();
        double runningTotalInclVAT = regInfo.getRunningTotalInclVAT();
        String result = String.format("ItemDescription: %s, Price: %.2f, RunningTotal(Incl.VAT): %.2f",
                itemDescription, itemPriceInclVAT, runningTotalInclVAT);
        return result;
    }
}
