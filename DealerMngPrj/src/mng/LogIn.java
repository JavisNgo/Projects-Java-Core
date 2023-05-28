package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.MyTool;

public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String accName = MyTool.readNonBlank("Enter your name ");
        String password = MyTool.readNonBlank("Enter your pass word ");
        String role = MyTool.readNonBlank("Enter your role ");
        Account acc = new Account(accName, password, role);
        return acc;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont = false;
        boolean valid = false;
        do {
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account- Try again?");
            }
            else if (!valid && !cont) {
                System.exit(0);
            } else {
                cont = false;
            }
        } while (cont);
        LogIn loginObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {
                "1-Add new dealer", "2-Search a dealer", "3-Remove a dealer", "4-Update a dealer", "5-Print all dealers",
                "6-Print continuing dealers", "7-Print UN-continuing dealers", "8-Write to file"
            };
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(loginObj);
            dList.initWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice("====Managing dealers====");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealertoFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealertoFile();
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size()+1);
            System.out.println("Bye.");
        }

    }
}
