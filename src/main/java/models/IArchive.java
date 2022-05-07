package models;


import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IArchive {

    DefaultTableModel serviceArchiveTable(String data);
    List<Service> serviceArchiveList();
}
