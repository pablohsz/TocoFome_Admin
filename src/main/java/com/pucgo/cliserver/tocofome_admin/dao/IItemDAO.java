package com.pucgo.cliserver.tocofome_admin.dao;

import com.pucgo.cliserver.tocofome_admin.model.Item;

public interface IItemDAO {

    Item crateItem(Item item);
    Item findById(Long id);
    Item findAll();
    boolean deleteItem(Long id);


}
