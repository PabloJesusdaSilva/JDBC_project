package com.firstproject;

import java.util.List;

public class RegistrationSystem {
    
    public static void main(String[] args) {
        Connections.toConnect();
        RegisterRepository repository = new RegisterRepository();

        Register register = repository.toPickUp(1);
        if(register != null) {
            System.out.println(register.getId() + " " + register.getName() + " " + register.getAge());
        } else {
            System.out.println("Client not found for ID.");
        }
    }
}
