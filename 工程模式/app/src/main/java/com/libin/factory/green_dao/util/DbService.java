package com.libin.factory.green_dao.util;

import android.text.TextUtils;

import com.libin.factory.MainApplication;
import com.libin.factory.green_dao.bean.ShopBean;
import com.libin.factory.green_dao.dao.DaoSession;
import com.libin.factory.green_dao.dao.ProcutedBeanDao;
import com.libin.factory.green_dao.dao.SaleBeanDao;
import com.libin.factory.green_dao.dao.ShopBeanDao;

import java.util.List;

/**
 * Created by doudou on 2017/3/14.
 */

public class DbService {
    private static final String TAG = DbService.class.getSimpleName();
    private static DbService instance;
    private DaoSession mDaoSession;
    private ShopBeanDao shopBeanDao;
    private ProcutedBeanDao procutedBeanDao;
    private SaleBeanDao saleBeanDao;

    private DbService() {

    }

    public static DbService getInstance() {
        if (instance == null) {

            instance = new DbService();
        }

        instance.mDaoSession = MainApplication.getDaoSession();
        instance.shopBeanDao = MainApplication.getDaoSession().getShopBeanDao();
        instance.procutedBeanDao = MainApplication.getDaoSession().getProcutedBeanDao();
        instance.saleBeanDao = MainApplication.getDaoSession().getSaleBeanDao();

        return instance;
    }

    /**
     * 根据id获取商品信息
     * @param id
     */
    public ShopBean loadNote(long id) {
        if(!TextUtils.isEmpty(id + "")) {
            return shopBeanDao.load(id);
        }
        return  null;
    }

    /**
     * 取出所有数据
     */
    public List<ShopBean> loadAllNote(){
        return shopBeanDao.loadAll();
    }

    /**
     * 根据id倒序排列
     * @return
     */
    public List<ShopBean> loadAllNoteByOrder()
    {
        return shopBeanDao.queryBuilder().orderDesc(ShopBeanDao.Properties.Id).list();
    }

    /**
     * 根据查询条件,返回数据列表
     * @param where        条件
     * @param params       参数
     * @return             数据列表
     */
    public List<ShopBean> queryNote(String where, String... params){
        return shopBeanDao.queryRaw(where, params);
    }

    /**
     * 根据用户信息,插件或修改信息
     * @param shopBean
     * @return 插件或修改的用户id
     */
    public long saveNote(ShopBean shopBean){
        return shopBeanDao.insertOrReplace(shopBean);
    }

    /**
     * 批量插入或修改用户信息
     * @param list      用户信息列表
     */
    public void saveNoteLists(final List<ShopBean> list){
        if(list == null || list.isEmpty()){
            return;
        }
        shopBeanDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++){
                    ShopBean user = list.get(i);
                    shopBeanDao.insertOrReplace(user);
                }
            }
        });

    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote(){
        shopBeanDao.deleteAll();
    }

    /**`
     * 根据id,删除数据
     * @param id      用户id
     */
    public void deleteNote(long id){
        shopBeanDao.deleteByKey(id);
    }

    /**
     * 根据用户类,删除信息
     * @param shopBean    用户信息类
     */
    public void deleteNote(ShopBean shopBean){
        shopBeanDao.delete(shopBean);
    }
}
