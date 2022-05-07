package models;

import Utils.DB;
import Utils.Util;
import props.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser {
    DB db= new DB();
    public static String name="";
    public static int uid=0;


    @Override
    public int userUpdate(User user) {
        int status=0;

        try {
            String sql = "UPDATE user SET name=?,surname=?,email=?,password=? where uid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,user.getName());
            pre.setString(2,user.getSurname());
            pre.setString(3,user.getEmail());
            pre.setString(4,Util.MD5(user.getPassword()));
            pre.setInt(5,user.getUid());

            status= pre.executeUpdate();



        } catch (Exception ex) {
            System.err.println("userUpdate Error: "+ex);
            ex.printStackTrace();
        } finally {
            db.close(); //açık olan
        }
        return status;
    }

    @Override
    public boolean userLogin(String email, String password) {
        boolean status=false;

        try {
            String sql="select * from user where email=? and password=?";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            pre.setString(1,email);
            pre.setString(2,Util.MD5(password));

            ResultSet rs=pre.executeQuery();

            status=rs.next();

            if (status){
               name = rs.getString("name")+" "+rs.getString("surname");
               uid=rs.getInt("uid");
            }




        }catch (Exception ex){
            System.err.println("userLogin Error: "+ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }


        return status;
    }



}
