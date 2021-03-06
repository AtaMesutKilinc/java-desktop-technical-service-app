package models;

import Utils.DB;
import Utils.Util;
import props.Customer;
import props.User;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements ICustomer{
    DB db=new DB();
    private List<Customer> customerList=new ArrayList<Customer>();
    Customer customer=new Customer();
    //trycach finally

    @Override
    public int customerInsert(Customer customer) {
        int status=0;
        try {
            String sql="INSERT INTO customer VALUES (null,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);  //bağlantı yaptık connectten türettik. ayrıştır ve hazırla manasında

            pre.setString(1,customer.getName());  //string ise setstring int ise setınteger.
            pre.setString(2,customer.getSurname());  //1 den başlar.soru işareti sırası.
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status= pre.executeUpdate(); //statuse

        }catch (Exception ex){
            System.err.println("customerInsert Error: "+ex);
            ex.printStackTrace();
            if (ex.toString().contains("UNIQUE")){ //hatada unique değeri varsa statusu -1 yap.
                status=-1;
            }
        }finally {
            db.close();
        }
        return status;
    }
    @Override
    public int customerUpdate(Customer customer) {
        int status=0;

        try {
            String sql = "UPDATE customer SET name=?,surname=?,email=?,phone=?,address=? where cid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());  //string ise setstring int ise setınteger.
            pre.setString(2,customer.getSurname());  //1 den başlar.soru işareti sırası.
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone()); //md5 e çevir hashcode a
            pre.setString(5,customer.getAddress());
            pre.setInt(6,customer.getCid());


            //soru işaretlerine gönderilecek olan datanın gönderim yönteminin bir adıda bind yöntemi olarak geçer.
            status= pre.executeUpdate();



        } catch (Exception ex) {
            System.err.println("customerUpdate Error: "+ex); //err kırmızı gösteriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //açık olan
        }
        return status;
    }
    @Override
    public int customerDelete(int cid) {
        int status=0;
        try{
            String sql="delete from customer where cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,cid);

            status= pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("customerDelete Error: "+ex); //err kırmızı gösteriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //açık olan
        }
        return status;

    }
    @Override
    public List<Customer> customerList() {
        try
        {
            String sql = "select * from customer order by cid desc ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            customerList.clear();
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


    //todo : search
    @Override
    public List<Customer> customerSearch(String data) {
        try
        {
            String sql = "select * from customer where like ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            customerList.clear();
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

    public DefaultTableModel customerModel(){
        customerList();

        DefaultTableModel tableModel= new DefaultTableModel();

        tableModel.addColumn("cid");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Address");

        for(Customer item:customerList){
            Object[] row={item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};

            tableModel.addRow(row);
        }

        return  tableModel;
    }
}
