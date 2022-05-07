package models;

import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IDashboard {
    DefaultTableModel serviceRepairedTable(String data);
    List<Service> serviceRepairedList();
    DefaultTableModel serviceArrivedTable(String data);
    List<Service> serviceArrivedList();
}
