package org.itacademy.homework9selenide.utils;

import org.itacademy.homework9selenide.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Config {

    private static String pageSearch;

    public static void getProperties() {
        Properties properties = new Properties();
        URL resource = Main.class.getClassLoader().getResource("config.properties");
        File file = new File(Objects.requireNonNull(resource).getFile());
        try {
            FileInputStream in = new FileInputStream(file);
            properties.load(in);
            in.close();
            pageSearch = properties.getProperty("page-main-search");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPageSearch() {
        return pageSearch;
    }
}
