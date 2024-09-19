package com.firstproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterRepository {
    public Connection connection;

    public RegisterRepository() {
        connection = Connections.getConnection();
    }

    public void create(Register register) {
        try {
            String structionSQL = "INSERT INTO public.clients (name, age) VALUES(?, ?);";
            PreparedStatement pst = connection.prepareStatement(structionSQL);
            pst.setString(1, register.getName());
            pst.setInt(2, register.getAge());

            pst.execute();
            System.out.println("Register success;");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void put(Register register) {    
        try {
            String structionSQL = "UPDATE public.clients SET name=?, age=? WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(structionSQL);
            pst.setString(1, register.getName());
            pst.setInt(2, register.getAge());
            pst.setInt(3, register.getId());

            pst.execute();
            System.out.println("Update success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            String structionSQL = "DELETE FROM public.clients WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(structionSQL);
            pst.setInt(1, id);

            pst.execute();
            System.out.println("Deleted success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Register> getAllClients() {
        List<Register> listClients = new ArrayList<>();

        try {
            String structionSQL = "SELECT id, name, age FROM public.clients";
            PreparedStatement statement = connection.prepareStatement(structionSQL);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                Integer age = result.getInt("age");

                Register register = new Register();
                register.setId(id);
                register.setName(name);
                register.setAge(age);

                listClients.add(register);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listClients;
    }

    public Register toPickUp(Integer id) {
        Register  register = null;

        try {
            String structionSQL = "SELECT id, name, age FROM public.clients WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(structionSQL);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            if(result.next()) {
                String name = result.getString("name");
                Integer age = result.getInt("age");

                register = new Register();
                register.setId(id);
                register.setName(name);
                register.setAge(age);

                return register;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return register;
    }
}
