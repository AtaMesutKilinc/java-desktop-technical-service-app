package models;

import Utils.DB;
import props.Customer;
import props.Service;
import views.Base;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceImpl implements IService{
    DB db=new DB();

    List<Customer> cList=new ArrayList<>();
    List<Customer> cListCopy=new ArrayList<>();

    List<Service> serviceList=new ArrayList<>();

//    Customer customer=new Customer();

    public ServiceImpl() {
        cList=serviceCustomerList();

        cListCopy=cList;

    }




    @Override
    public List<Customer> serviceCustomerList() {
        List<Customer> customerList=new ArrayList<>();
        try
        {
            String sql = "select * from customer order by  cid desc ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            while(rs.next())
            {
                int cid=rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                Customer customer = new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);


            }
        }
        catch (Exception ex)
        {
            System.err.println("customerList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel serviceCustomerTable(String data) {

        cList=cListCopy;

        DefaultTableModel tableModel= new DefaultTableModel();
        tableModel.addColumn("Cid");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Address");

        if (data !=null && !data.equals("")){
            data=data.toLowerCase(Locale.ROOT);

            List<Customer> searchedCustomerList = new ArrayList<>();
            for(Customer item:cList){
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data))
                {
                    searchedCustomerList.add(item);
                }
            }



            cList=searchedCustomerList;


        }


        for(Customer item:cList){
            Object[] row={item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};
            tableModel.addRow(row);
        }

        return  tableModel;
    }
    @Override
    public int serviceInsert(Service service) {
        int status=0;
        try {

            String sql="INSERT INTO service VALUES (null,?,?,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);

            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());
            pre.setInt(7,service.getPrice());
            status= pre.executeUpdate(); //statuse

        }catch (Exception ex){
            System.err.println("serviceInsert Error: "+ex);
            ex.printStackTrace();
//            if (ex.toString().contains("UNIQUE")){ //hatada unique değeri varsa statusu -1 yap.
//                status=-1;  gerek yok
//            }
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceDelete(int sid) {
        int status=0;
        try{
            String sql="delete from service where sid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,sid);

            status= pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("serviceDelete Error: "+ex);
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceUpdate(Service service) {
        int status=0;

        try {
            String sql = "UPDATE service SET cid=?,title=?,info=?,days=?,date=?,status=?,price=? where sid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());
            pre.setInt(7,service.getPrice());
            pre.setInt(8,service.getSid());
            status= pre.executeUpdate();

            status= pre.executeUpdate();



        } catch (Exception ex) {
            System.err.println("serviceUpdate Error: "+ex);
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Service> serviceList() {

        try
        {

            String sql = "select name,surname,phone,email," +
                    "s.sid,s.cid,s.title,s.info,s.days,s.date,s.status,s.price " +
                    "from service s\n" +
                    "join customer c on c.cid=s.cid\n" +
                    "where s.status between 0 and 2 \n" +
                    "order by s.sid desc";//where s.cid=?
            PreparedStatement pre=db.connect().prepareStatement(sql);
//            pre.setInt(1,s.getCid());

            ResultSet rs=pre.executeQuery();
            serviceList.clear();
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
                serviceList.add(service);




            }
        }
        catch (Exception ex)
        {
            System.err.println("serviceList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return serviceList;
    }

    @Override
    public DefaultTableModel serviceTable(int cid) {

        List<Service> list= new ArrayList<>();
        DefaultTableModel tableModel= new DefaultTableModel();

        tableModel.addColumn("sid");
        tableModel.addColumn("Name");
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
        list=serviceList();
        for(Service item:list){
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


}
