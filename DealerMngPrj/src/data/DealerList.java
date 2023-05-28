package data;

import java.util.List;
import java.util.ArrayList;
import tools.MyTool;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        super();
        this.loginObj = loginObj;
    }

    private void loadDealerFromFile() {
        List<String> lines = MyTool.readLinesFormFile(dataFile);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Dealer d = new Dealer(line);
            DealerList.this.add(d);
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList result = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == true) {
                result.add(d);
            }
        }
        return result;
    }

    public DealerList getUnContinuingList() {
        DealerList result = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == false) {
                result.add(d);
            }
        }
        return result;
    }

    private int searchDealer(String ID) {
        ID.toUpperCase();
        int N = DealerList.this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        int pos;
        String ID = MyTool.readPattern("ID for search ", Dealer.ID_FORMAT);
        pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("ID is not founded!!");
        } else {
            System.out.println("Dealer of this ID: " + this.get(pos).toString());
        }

    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readPattern("ID of new dealer", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer");
        phone = MyTool.readPattern("Phone number", Dealer.PHONE_FORMAT);
        continuing = true;
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public void removeDealer() {
        int pos;
        String ID = MyTool.readPattern("ID for remove: ", Dealer.ID_FORMAT);
        pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("ID is not founded!!");
        } else {
            this.get(pos).setContinuing(false);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTool.SC.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found");
        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.println("new name, ENTER for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            String newAddr = "";
            System.out.println("new address, Enter for omitting: ");
            newAddr = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }
            String newPhone = "";
            System.out.println("new phone, Enter for omitting: ");
            newPhone = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newPhone.isEmpty()) {
                d.setPhone(newPhone);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            for (int i=0;i<this.size();i++) {
                System.out.println(this.get(i).toString());
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealertoFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
        System.out.println("Save successfull !");
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
