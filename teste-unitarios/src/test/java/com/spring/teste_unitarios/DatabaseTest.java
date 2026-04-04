package com.spring.teste_unitarios;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {

    private static Connection connection;

    @BeforeAll
    static void setUpDataBase() throws Exception{
        connection = DriverManager.getConnection("jdbc:h2:mem:db" , "sa" , "");
        connection.createStatement().execute("create table users (id int , nome varchar)");
    }

    @BeforeEach
    void insertUserThat() throws Exception{
        connection.createStatement().execute("insert into users(id,nome) values (1 , 'João')");
    }

    @Test
    @Disabled
    void testUserExists() throws Exception{
        var result = connection
                        .createStatement()
                        .execute("select * from users where id = 1");
        Assertions.assertTrue(result);
    }

    @AfterAll
    static void closeDataBase() throws Exception{
        connection.close();
    }
}
