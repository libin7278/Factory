package com.libin.factory.green_dao.dao;

import com.libin.factory.MainApplication;
import com.libin.factory.green_dao.bean.ShopBean;

import java.util.List;

/**
 * Created by doudou on 2017/3/14.
 */

public class LoveDao {
    /**
     * 添加数据
     *
     * @param shop
     */
    public static void insertLove(ShopBean shop) {
        MainApplication.getDaoInstant().getShopBeanDao().insert(shop);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteLove(long id) {
        MainApplication.getDaoInstant().getShopBeanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLove(ShopBean shop) {
        MainApplication.getDaoInstant().getShopBeanDao().update(shop);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<ShopBean> queryLove() {
        return MainApplication.getDaoInstant().getShopBeanDao().queryBuilder().where(ShopBeanDao.Properties.Type.eq(ShopBean.TYPE_LOVE)).list();
    }
}
