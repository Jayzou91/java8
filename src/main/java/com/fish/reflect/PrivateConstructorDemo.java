package com.fish.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class PrivateConstructorDemo {

    //Find the private constructor for given number of arguments and types and instantiate the class.
    public void createObject(int id, String name) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Constructor<?>[] constructors = Car.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (Modifier.isPrivate(constructor.getModifiers())) {
                constructor.setAccessible(true);
                Class<?>[] clazzs = constructor.getParameterTypes();
                if (constructor.getParameterCount() == 2 && clazzs[0] == Integer.class &&
                        clazzs[1] == String.class) {
                    Object ob = constructor.newInstance(id, name);
                    if (ob instanceof Car) {
                        Car car = (Car) ob;
                        System.out.println("Car Id:" + car.getCarId());
                        System.out.println("Car Name:" + car.getCarName());
                    }
                }
            }
        }
    }

    //Find the private constructor using given constructor name and instantiate the class.
    public void craeteObjectByConstructorName(int id, String name) throws NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Constructor<Car> constructor = Car.class.getDeclaredConstructor(Integer.class, String.class);
        if (Modifier.isPrivate(constructor.getModifiers())) {
            constructor.setAccessible(true);
            Car car = (Car) constructor.newInstance(id, name);
            System.out.println("Car Id:" + car.getCarId());
            System.out.println("Car Name:" + car.getCarName());
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        PrivateConstructorDemo ob = new PrivateConstructorDemo();
        ob.createObject(10, "Alto");
        System.out.println("-------------------------");
        ob.craeteObjectByConstructorName(20, "Santro");
    }
}
