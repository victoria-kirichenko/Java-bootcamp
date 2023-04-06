package edu.school21.app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Program {


    public static void main(String[] args) {
        String TEXT_DELIMITER = "---------------------";
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Classes:\n- " + "edu.school21.classes.User" + "\n- " + "edu.school21.classes.Car"
            + "\n---------------------\nEnter class name:\n-> ");

            Class myClass = Class.forName(sc.nextLine());
            Object goodClass = myClass.newInstance();

            System.out.println(TEXT_DELIMITER);

            System.out.println("fields:");
            for (Field field : myClass.getDeclaredFields()){
                System.out.println(field.getName());
            }

            System.out.println("methods:");
            for (Method method : myClass.getDeclaredMethods()){
                System.out.println(method.getName() + "(" + Arrays.toString(method.getGenericParameterTypes()) + ")");
            }

            System.out.println("Let's create an object.");
            for (Field field : goodClass.getClass().getDeclaredFields()){
                System.out.print(field.getName() + ":\n-> ");
                field.setAccessible(true);
                field.set(goodClass, getInput(field.getGenericType().toString()));
                field.setAccessible(false);
            }

            System.out.print("Object created: " + goodClass.getClass().getName() + "[");
            for (Field field : goodClass.getClass().getDeclaredFields()){
                field.setAccessible(true);
                System.out.print(field.getName() + "='" + field.get(goodClass) + "', ");
                field.setAccessible(false);
            }
            System.out.println("]");

            System.out.println(TEXT_DELIMITER);

            System.out.print("Enter name of the field changing:\n-> ");

            Field fieldChange = goodClass.getClass().getDeclaredField(getInput("String").toString());
            fieldChange.setAccessible(true);
            System.out.print("Enter " + fieldChange.getGenericType() + " value:\n-> ");
            fieldChange.set(goodClass, getInput(fieldChange.getGenericType().toString()));
            fieldChange.setAccessible(false);

            System.out.print("Object updated: " + goodClass.getClass().getName() + "[");
            for (Field field : goodClass.getClass().getDeclaredFields()){
                field.setAccessible(true);
                System.out.print(field.getName() + "='" + field.get(goodClass) + "', ");
                field.setAccessible(false);
            }
            System.out.println("]");

            System.out.println(TEXT_DELIMITER);

            System.out.print("Enter name of the method for call:\n-> ");

            String[] splitInput = getInput("String").toString().split("\\(|\\)|,");

            Method method = goodClass.getClass().getDeclaredMethod(splitInput[0], getType(splitInput[1]));

            System.out.print("Enter " + method.getGenericParameterTypes()[0].getTypeName() + " value:\n-> ");

            Object res = method.invoke(goodClass, getInput(method.getGenericParameterTypes()[0].getTypeName()));

            System.out.println("Method returned:\n" + res);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getInput(String input) {
        Scanner sc = new Scanner(System.in);
        if (input.contains("String")) {
            return sc.nextLine();
        } else if (input.contains("int")) {
            return sc.nextInt();
        }
        return "none";
    }

    public static Class<?> getType(String input) {
        if (input.contains("String")) {
            return String.class;
        } else if (input.contains("int")) {
            return int.class;
        }
        return double.class;
    }
}