package org.itacademy.homework9selenide.utils;

import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.models.MenuItem;
import org.itacademy.homework9selenide.models.MenuList;
import org.testng.annotations.DataProvider;

import java.util.List;

@Log4j2
public class DataProviderSearch {

    @DataProvider(name = "menuItems")
    public Object[][] menuItems() {
        MenuList menuList = JsonUtil.getObjectFromFile("menuitems.json", MenuList.class);
        List<MenuItem> items = menuList.getItems();
        int size = items.size();
        Object[][] data = new Object[size][1];
        for (int i = 0; i < size; i++) {
            data[i][0] = items.get(i);
            log.info("DATAPROVIDER [" + i + "] = " + data[i][0]);
        }
        return data;
    }
}
