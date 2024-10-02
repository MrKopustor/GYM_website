
package com.teachmeskills.gym_website.request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Connect {

    @Value("${spring.datasource.url}")
    private static String URL;
    @Value("${spring.datasource.username}")
    private static String USERNAME;
    @Value("${spring.datasource.password}")
    private static String PASSWORD;


    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}