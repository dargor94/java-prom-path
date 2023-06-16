package org.dargor;

import org.dargor.model.Computer;
import org.dargor.model.Customer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class ReflectionMain {
    public static void main(String[] args) {

        var computerList = List.of(new Computer("1", "A model", "This is a description"),
                new Computer("2", "Another model", "Another description"));
        var customersList = List.of(new Customer("1", "test@test.com"));

        System.out.println("------------Print Lists------------");
        printList(computerList);
        printList(customersList);

        //Otras funcionalidades
        System.out.println();
        System.out.println("------------Names------------");
        System.out.println(ReflectionMain.class.getSimpleName());
        System.out.println(ReflectionMain.class.getName());
        System.out.println();
        System.out.println("------------Fields------------");
        Arrays.stream(customersList.get(0).getClass().getDeclaredFields()).forEach(field -> System.out.println(field.getName()));
        System.out.println(customersList.get(0).getClass().getDeclaredFields()[0].getName().equals("id"));
        System.out.println(customersList.get(0).getClass().getDeclaredFields()[0].getType().getSimpleName());

        try {
            customersList.get(0).getClass().getDeclaredFields()[0].setAccessible(true);
            System.out.println(customersList.get(0).getClass().getDeclaredField("id").getModifiers());

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("------------Classes------------");
        try {
            var clazz = Class.forName("org.dargor.model.Computer"); // Same as Computer.class or computerInstance.getClass()
            System.out.println(clazz.getPackage().getName());
            System.out.println(clazz.getSimpleName());
            System.out.println(Modifier.isPublic(clazz.getModifiers()));
            System.out.println(Modifier.isStatic(clazz.getModifiers())
                    && Modifier.isFinal(clazz.getModifiers())
                    && Modifier.isVolatile(clazz.getModifiers()));
            System.out.println(clazz.getConstructor(String.class, String.class, String.class));
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printList(List list) {

        for (Object c : list) {

            Method[] methods = c.getClass().getMethods();
            for (Method m : methods) {

                if (m.getName().equals("getModel") || m.getName().equals("getDescription")) {

                    try {
                        String s = (String) m.invoke(c);
                        System.out.println(s);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}