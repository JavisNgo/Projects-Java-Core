
package dao;

import java.util.List;
import model.CD;

public interface CDListDao {
    List<CD> getNewCD();
    List<CD> getAllCD();
    CD searchCDByID(String cdid);
    boolean searchCDByTitle(String cdname);
    CD getCDByCDID(String cdid);
    List<CD> getCDByTitle(String cdname);
    void createCD();
    void update(CD CDs);
    void delete(CD CDs);
    void saveFile();
}
