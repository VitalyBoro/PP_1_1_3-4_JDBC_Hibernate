package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService us = new UserServiceImpl();
        // создание таблицы
        us.createUsersTable();
        // добавление строк
        us.saveUser("name_1", "last_name_1", (byte) 15);
        us.saveUser("name_2", "last_name_2", (byte) 25);
        us.saveUser("name_3", "last_name_3", (byte) 35);
        us.saveUser("name_4", "last_name_4", (byte) 45);
         //получаем все строки
        us.getAllUsers();
        //очистка таблицы
        us.cleanUsersTable();
        // удаление таблицы
        us.dropUsersTable();
   }
}
