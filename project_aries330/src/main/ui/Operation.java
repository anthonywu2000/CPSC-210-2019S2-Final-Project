package ui;

public enum Operation {

    SKIP_SERVICE("0", Operations::skipService), TRANSACTION_SERVICE("1", Operations::transactionService),
    DEPOSIT_WITHDRAW_SERVICE("2", Operations::depositOrWithdrawService),
    PAYMENT_SERVICE("3", Operations::payService),

    VIEW_ACCOUNT_SERVICE("5", Operations::viewingAccountDetailService),
    CONTACT_SERVICE("6", Operations::contactUs),
    CURRENCY_EXCHANGE_SERVICE("7", Operations::callCurrencyService), EXIT_SERVICE("8", Operations::exit);

    private String input;
    private Runnable method;

    Operation(String input, Runnable method) {
        this.input = input;
        this.method = method;
    }

    public String getInput() {
        return input;
    }

    public void run() {
        method.run();
    }

    public static Operation parse(String input) {
        for (Operation operation : Operation.values()) {
            if (operation.getInput().equals(input)) {
                return operation;
            }
        }
        throw new IllegalArgumentException();
    }
}
