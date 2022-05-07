package models;

import Utils.DB;
import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashboardImpl implements IDashboard{
    DB db =new DB();
    List<Service> serviceRepairedList=new ArrayList<>();
    List<Service> serviceRepairedListCopy=new ArrayList<>();

    List<Service> serviceArrivedList=new ArrayList<>();
    List<Service> serviceArrivedListCopy=new ArrayList<>();

    public DashboardImpl() {
        serviceRepairedList=serviceRepairedList();
        serviceRepairedListCopy=serviceRepairedList;

        serviceArrivedList=serviceArrivedList();
        serviceArrivedListCopy=serviceArrivedList;

    }

    @Override
    public DefaultTableModel serviceRepairedTable(String data) {
        serviceRepairedList=serviceRepairedListCopy;

        DefaultTableModel tableModel= new DefaultTableModel();

        tableModel.addColumn("sid");
        tableModel.addColumn("Name");  //kolon ekledik
        tableModel.addColumn("Surname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");



        tableModel.addColumn("cid");
        tableModel.addColumn("Title");
        tableModel.addColumn("Info");
        tableModel.addColumn("Days");
        tableModel.addColumn("Date");
        tableModel.addColumn("Status");
        tableModel.addColumn("Price");

        if (data !=null && !data.equals("")){//data null olmadığında ve boş olmadığında
            data=data.toLowerCase(Locale.ROOT); //küçüğe dönüştür txtfieldden adlığımızı
            //arama soruçlarını gönder.
            List<Service> searchedServiceList = new ArrayList<>();
            for(Service item:serviceRepairedList){
                if (item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data)  //root hangi yerde açılırsa oranın dilini alacak
                        ||item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getEmail().toLowerCase(Locale.ROOT).contains(data)
                        ||item.getCustomer().getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getTitle().toLowerCase(Locale.ROOT).contains(data)
                        || item.getInfo().toLowerCase(Locale.ROOT).contains(data)
                        || item.getDate().toLowerCase(Locale.ROOT).contains(data))

                {
                    searchedServiceList.add(item); //arama sonucunu ekle listeye.
                }
            }



            serviceRepairedList=searchedServiceList; //listeyi güncelle


        }



        for(Service item:serviceRepairedList){ //car türünde bir nesne getiriyor.
            String state="";
            if (item.getStatus()==0){
                state= "Product Just Arrived";
            }else if (item.getStatus()==1){
                state="Product In Repair";
            }else if (item.getStatus()==2){
                state="Product Has Been Repaired";
            }else {
                state="Product Delivered";
            }
            Object[] row={item.getSid(),item.getCustomer().getName(),item.getCustomer().getSurname(),item.getCustomer().getPhone(),
                    item.getCustomer().getEmail(),item.getCid(),item.getTitle(),item.getInfo(),item.getDays(),
                    item.getDate(),state,item.getPrice()};//

            tableModel.addRow(row);
        }

        return  tableModel;
    }

    @Override
    public List<Service> serviceRepairedList() {
        List<Service> repaired=new ArrayList<>();

        try
        {

            String sql = "select name,surname,phone,email," +
                    "s.sid,s.cid,s.title,s.info,s.days,s.date,s.status,s.price " +
                    "from service s\n" +
                    "join customer c on c.cid=s.cid\n" +
                    "where s.status=2\n" +
                    "order by s.sid desc";//where s.cid=?
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            repaired.clear(); //buna dikkat et
            while(rs.next())
            {

                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String phone=rs.getString("phone");
                String email=rs.getString("email");

                int sid=rs.getInt("sid");
                int cid=rs.getInt("cid");
                String title = rs.getString("title");
                String info = rs.getString("info");
                int days = rs.getInt("days");
                String date = rs.getString("date");
                int status = rs.getInt("status");
                int price = rs.getInt("price");

                Customer customer=new Customer(name,surname,phone,email);
                Service service = new Service(customer,sid,cid,title,info,days,date,status,price);

                repaired.add(service);




            }
        }
        catch (Exception ex)
        {
            System.err.println("serviceRepairedList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return repaired;
    }

    @Override
    public DefaultTableModel serviceArrivedTable(String data) {
        serviceArrivedList=serviceArrivedListCopy;

        DefaultTableModel tableModel= new DefaultTableModel();

        tableModel.addColumn("sid");
        tableModel.addColumn("Name");  //kolon ekledik
        tableModel.addColumn("Surname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");



        tableModel.addColumn("cid");
        tableModel.addColumn("Title");
        tableModel.addColumn("Info");
        tableModel.addColumn("Days");
        tableModel.addColumn("Date");
        tableModel.addColumn("Status");
        tableModel.addColumn("Price");

        if (data !=null && !data.equals("")){//data null olmadığında ve boş olmadığında
            data=data.toLowerCase(Locale.ROOT); //küçüğe dönüştür txtfieldden adlığımızı
            //arama soruçlarını gönder.
            List<Service> searchedServiceList = new ArrayList<>();
            for(Service item:serviceArrivedList){
                if (item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data)  //root hangi yerde açılırsa oranın dilini alacak
                        ||item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getEmail().toLowerCase(Locale.ROOT).contains(data)
                        ||item.getCustomer().getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getTitle().toLowerCase(Locale.ROOT).contains(data)
                        || item.getInfo().toLowerCase(Locale.ROOT).contains(data)
                        || item.getDate().toLowerCase(Locale.ROOT).contains(data))

                {
                    searchedServiceList.add(item); //arama sonucunu ekle listeye.
                }
            }



            serviceArrivedList=searchedServiceList; //listeyi güncelle


        }



        for(Service item:serviceArrivedList){ //car türünde bir nesne getiriyor.
            String state="";
            if (item.getStatus()==0){
                state= "Product Just Arrived";
            }else if (item.getStatus()==1){
                state="Product In Repair";
            }else if (item.getStatus()==2){
                state="Product Has Been Repaired";
            }else {
                state="Product Delivered";
            }
            Object[] row={item.getSid(),item.getCustomer().getName(),item.getCustomer().getSurname(),item.getCustomer().getPhone(),
                    item.getCustomer().getEmail(),item.getCid(),item.getTitle(),item.getInfo(),item.getDays(),
                    item.getDate(),state,item.getPrice()};//

            tableModel.addRow(row);
        }

        return  tableModel;
    }

    @Override
    public List<Service> serviceArrivedList() {
        List<Service> delivered=new ArrayList<>();

        try
        {

            String sql = "select name,surname,phone,email," +
                    "s.sid,s.cid,s.title,s.info,s.days,s.date,s.status,s.price " +
                    "from service s\n" +
                    "join customer c on c.cid=s.cid\n" +
                    "where s.status=0\n" +
                    "order by s.sid desc";//where s.cid=?
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            delivered.clear(); //buna dikkat et
            while(rs.next())
            {

                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String phone=rs.getString("phone");
                String email=rs.getString("email");

                int sid=rs.getInt("sid");
                int cid=rs.getInt("cid");
                String title = rs.getString("title");
                String info = rs.getString("info");
                int days = rs.getInt("days");
                String date = rs.getString("date");
                int status = rs.getInt("status");
                int price = rs.getInt("price");

                Customer customer=new Customer(name,surname,phone,email);
                Service service = new Service(customer,sid,cid,title,info,days,date,status,price);

                delivered.add(service);




            }
        }
        catch (Exception ex)
        {
            System.err.println("serviceRepairedList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return delivered;
    }
}
