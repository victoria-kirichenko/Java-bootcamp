package ex05;

import java.util.Scanner;
import java.util.UUID;

enum Modes {
    STANDARD,
    DEV
}

public class Menu {
    TransactionsService service;
    Modes mod;
    int limit;

    Menu() {
        service = new TransactionsService();
    }

    public void run(Modes mod_) {
        mod = mod_;
        if (mod == Modes.DEV) {
            limit = 6;
        } else {
            limit = 4;
        }
        menu();
    }

    public void menu() {
        boolean exit = false;
        int item = 0;
        while (!exit) {
            printMenu();
            Scanner sc = new Scanner(System.in);
            item = sc.nextInt();
            if (item == limit + 1) {
                System.exit(0);
            }
            if (item > 0 && item <= limit) {
                switch (item) {
                    case 1:
                        try {
                            addUser();
                            printLine();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            addUser();
                        }
                        break;
                    case 2:
                        try {
                            viewUserBalance();
                            printLine();
                        } catch (UserNotFoundException e) {
                            System.out.println(e.getMessage());
                            viewUserBalance();
                        }
                        break;
                    case 3:
                        try {
                            performTransfer();
                            printLine();
                        } catch (IllegalTransactionException e) {
                            System.out.println(e.getMessage());
                            performTransfer();
                        }
                        catch (UserNotFoundException e) {
                            System.out.println(e.getMessage());
                            performTransfer();
                        }
                        catch (TransactionNotFoundException e) {
                            System.out.println(e.getMessage());
                            performTransfer();
                        }
                        break;
                    case 4:
                        try {
                            viewTranscationsById();
                            printLine();
                        } catch (IllegalTransactionException e) {
                            System.out.println(e.getMessage());
                            viewTranscationsById();
                        } catch (TransactionNotFoundException e) {
                            System.out.println(e.getMessage());
                            viewTranscationsById();
                        }
                        catch (UserNotFoundException e) {
                            System.out.println(e.getMessage());
                            viewTranscationsById();
                        }
                        break;
                    case 5:
                        try {
                            removeTransfer();
                            printLine();
                        } catch (TransactionNotFoundException e) {
                            System.out.println(e.getMessage());
                            removeTransfer();
                        } catch (IllegalTransactionException e) {
                            System.out.println(e.getMessage());
                            removeTransfer();
                        } catch (UserNotFoundException e) {
                            System.out.println(e.getMessage());
                            removeTransfer();
                        }
                        break;
                    case 6:
                        transferCheckValidity();
                        printLine();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void addUser() {
        System.out.println("Enter a user name and a balance");
        System.out.print("->");
        Scanner sc = new Scanner(System.in);
        User user = new User(sc.next(), sc.nextInt());
        service.userAdd(user);
        System.out.println("User with id = " + (user.getId() + 1) + " is added");
    }

    public void viewUserBalance() {
        System.out.println("Enter a user ID");
        System.out.print("->");
        Scanner sc = new Scanner(System.in);
        User user = service.list.getUser(sc.nextInt() - 1);
        System.out.println(user.getName() + " - " + user.getBalance());
    }

    public void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        System.out.print("->");
        Scanner sc = new Scanner(System.in);
        int sender = sc.nextInt() - 1;
        int recipient = sc.nextInt() - 1;
        double amount = sc.nextDouble();
        service.transact(sender, recipient, amount);
        System.out.println("The transfer is completed");
    }

    public void viewTranscationsById() {
        System.out.println("Enter a user ID");
        System.out.print("->");
        Scanner sc = new Scanner(System.in);
        Transaction[] transactions = service.list.getUser(sc.nextInt() - 1).getTransactions().toArray();
        for (int i = 0; i < transactions.length; i++) {
            transactions[i].print();
        }
    }

    public void removeTransfer() {
        System.out.println("Enter a user ID and a transfer ID");
        System.out.print("->");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt() - 1;
        String transfer_id = sc.next();
        Transaction transaction = service.list.getUser(id).transactions.getTransactionByid(UUID.fromString(transfer_id));
        service.RemoveTransaction(id, UUID.fromString(transfer_id));
        System.out.println("Transfer To " + transaction.getRecipient().getName() + "(id = " + transaction.getId() + ") " + transaction.getAmount() + " removed");
    }

    public void transferCheckValidity() {
        System.out.println("Check results:");
        Transaction[] transactions = service.ValidTransactionsCheck();
        for (int i = 0; i < transactions.length; i++) {
            User recipient = transactions[i].getRecipient();
            User sender = transactions[i].getSender();
            UUID transfer_id = transactions[i].getId();
            double amount = transactions[i].getAmount();
            System.out.println(recipient.getName() + "(id = " + (recipient.getId() + 1) + ") has an unacknowledged transfer id = " +
                    transfer_id + "from " + sender.getName() + "(id = " + (sender.getId() + 1) + ") for " + amount);
        }

    }

    public void printMenu() {
        if (mod == Modes.DEV) {
            System.out.println("1. Add a user\n" +
                    "2. View user balances\n" +
                    "3. Perform a transfer\n" +
                    "4. View all transactions for a specific user\n" +
                    "5. DEV – remove a transfer by ID\n" +
                    "6. DEV – check transfer validity\n" +
                    "7. Finish execution\n");
        } else {
            System.out.println("1. Add a user\n" +
                    "2. View user balances\n" +
                    "3. Perform a transfer\n" +
                    "4. View all transactions for a specific user\n" +
                    "7. Finish execution\n");
        }

    }

    public void printLine() {
        System.out.println("---------------------------------------------------------");
    }
}