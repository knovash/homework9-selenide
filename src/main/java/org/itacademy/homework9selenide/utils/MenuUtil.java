package org.itacademy.homework9selenide.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.models.MenuItem;
import org.itacademy.homework9selenide.models.MenuList;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MenuUtil {

    public static void createJsonFile() {
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("бургер"));
        menuList.add(new MenuItem("донер"));
        menuList.add(new MenuItem("пончик"));
        MenuList menuItems = new MenuList();
        menuItems.setItems(menuList);
        String fileName = "menuitems.json";
        JsonUtil.setObjectToFile(menuItems, fileName);
        log.info("Created JSON file\n" + menuItems);
    }

}