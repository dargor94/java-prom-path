package org.dargor;

import org.dargor.model.Computer;
import org.dargor.model.Customer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        var computerList = List.of(new Computer("1", "A model", "This is a description"),
                new Computer("2", "Another model", "Another description"));
        var customersList = List.of(new Customer("1", "test@test.com"));

        printList(computerList);
        printList(customersList);


        System.out.println(Main.class.getSimpleName());
        System.out.println(Main.class.getName());
        System.out.println(Main.class.getCanonicalName());

    }

    public static void printList(List list) {

        for (Object c : list) {

            Method[] metodos = c.getClass().getMethods();
            for (Method m : metodos) {

                if (m.getName().equals("getModel") || m.getName().equals("getDescription")) {

                    try {
                        String cadena = (String) m.invoke(c);
                        System.out.println(cadena);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}