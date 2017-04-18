package com.libin.factory.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.libin.core.base.BaseActivity;
import com.libin.factory.MainApplication;
import com.libin.factory.R;
import com.libin.factory.green_dao.adapter.ShopAdapter;
import com.libin.factory.green_dao.bean.ProcutedBean;
import com.libin.factory.green_dao.bean.SaleBean;
import com.libin.factory.green_dao.bean.ShopBean;
import com.libin.factory.green_dao.dao.ProcutedBeanDao;
import com.libin.factory.green_dao.dao.SaleBeanDao;
import com.libin.factory.green_dao.dao.ShopBeanDao;
import com.libin.factory.green_dao.util.DbService;
import com.libin.factory.widget.dialog.InfoDialogFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

public class GreenDaoAvtivity extends BaseActivity implements InfoDialogFragment.AddOnclickListener {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;

    private RecyclerView rv_green_dao;

    private List<ShopBean> shops;

    private ShopAdapter shopAdapter;

    private InfoDialogFragment infoDialogFragment= new InfoDialogFragment("", "");;

    DbService dbService;

    private int positions;

    String TAG = GreenDaoAvtivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_avtivity);

        findViewId();

        setOnClick();

        dbService = DbService.getInstance();

        if (dbService.loadAllNote() != null) {
            shops = DbService.getInstance().loadAllNote();
            if (shops != null) {
                shopAdapter = new ShopAdapter(shops);
            }
            Logger.e("数据库数据长度 ： " + DbService.getInstance().loadAllNote().size());
            rv_green_dao.setHasFixedSize(true);
            rv_green_dao.setLayoutManager(new LinearLayoutManager(MainApplication.getContext()));
            rv_green_dao.setAdapter(shopAdapter);

            shopAdapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
                @Override
                public void onClick(View View, int position) {

                }
            });

            shopAdapter.setOnItemLongClickListener(new ShopAdapter.OnItemLongClickListener() {
                @Override
                public void onClick(View View, final int position) {
                    positions = position;
                    new AlertDialog.Builder(GreenDaoAvtivity.this)
                            .setItems(new String[]{"修改", "删除"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if( which == 0){
                                        infoDialogFragment.show(getFragmentManager(), "修改商品");
                                    }else if(which == 1){
                                        dbService.deleteNote(shops.get(position));
                                        shops.remove(position);
                                        shopAdapter.notifyDataSetChanged();
                                    }

                                }
                            })
                            .show();
                }
            });
        }

    }

    private void findViewId() {
        rv_green_dao = (RecyclerView) findViewById(R.id.rv_green_dao);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_query = (Button) findViewById(R.id.btn_query);
    }

    private void setOnClick() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDateFromUser();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1:1
                ProcutedBean procutedBean = new ProcutedBean();
                procutedBean.setId(12l); //这里的id和shopBean中的p_id保持一致
                procutedBean.setCity("杭州");
                procutedBean.setCompany("阿里");

                ProcutedBean procutedBean1 = new ProcutedBean(); //1:1会更新 从而关联最后一个
                procutedBean1.setId(11l); //这里的id和shopBean中的p_id保持一致
                procutedBean1.setCity("杭州1");
                procutedBean1.setCompany("阿里1");

                ShopBean shopBean = new ShopBean();
                shopBean.setP_id(12l);
                shopBean.setId(null);//Long,我们设置了id是自增长,我们可以setId(null),便是自增长。如果是long类型，你设置setId(null)，就报空指针。
                shopBean.setType(ShopBean.TYPE_LOVE);
                shopBean.setAddress("广东深圳");
                shopBean.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
                shopBean.setPrice("20");
                shopBean.setSell_num(15263);
                shopBean.setName("那大叔空间大卡司");

                ProcutedBeanDao procutedBeanDao = MainApplication.getDaoSession().getProcutedBeanDao();
                ShopBeanDao shopBeanDao = MainApplication.getDaoSession().getShopBeanDao();
                procutedBeanDao.insertOrReplace(procutedBean);
                procutedBeanDao.insertOrReplace(procutedBean1);
                shopBeanDao.insertOrReplace(shopBean);

                List<ShopBean> list = shopBeanDao.queryBuilder().build().list();
                for(ShopBean shopBean1 : list){
                    Logger.e(shopBean1.toString());
                }
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1:n
                SaleBean saleBean = new SaleBean();
                saleBean.setId(null);
                saleBean.setS_id(10l);
                saleBean.setSName("特级1");

                SaleBean saleBean1 = new SaleBean();
                saleBean1.setId(null);
                saleBean1.setS_id(10l);
                saleBean1.setSName("特级2");

                ShopBean shopBean = new ShopBean();
                shopBean.setS_id(10l);
                shopBean.setId(null);//Long,我们设置了id是自增长,我们可以setId(null),便是自增长。如果是long类型，你设置setId(null)，就报空指针。
                shopBean.setType(ShopBean.TYPE_LOVE);
                shopBean.setAddress("广东深圳");
                shopBean.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
                shopBean.setPrice("20");
                shopBean.setSell_num(15263);
                shopBean.setName("那大叔空间大卡司111111");

                SaleBeanDao saleBeanDao = MainApplication.getDaoSession().getSaleBeanDao();
                saleBeanDao.insertOrReplace(saleBean);
                saleBeanDao.insertOrReplace(saleBean1);
                ShopBeanDao shopBeanDao = MainApplication.getDaoSession().getShopBeanDao();
                shopBeanDao.insertOrReplace(shopBean);

            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopAdapter.notifyDataSetChanged();

                Toast.makeText(GreenDaoAvtivity.this, dbService.loadAllNote().size() + "条数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDateFromUser() {
        infoDialogFragment.show(getFragmentManager(), "添加商品");
    }

    @Override
    public void onAddOnClick(String name, String price) {
        if(infoDialogFragment.getTag().equals("添加商品")){
            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price)){
                addDate(name, price);
            }else {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            }
        }else if(infoDialogFragment.getTag().equals("修改商品")){
            ShopBean shopBean = shops.get(positions);
            shopBean.setName(name);
            shopBean.setPrice(price);
            dbService.saveNote(shopBean);
            shops.remove(positions);
            shops.add(positions,shopBean);
        }

        shopAdapter.notifyDataSetChanged();
        Logger.e("新增 ： " + shops.size());
    }

    private void addDate(String name, String price) {
        ShopBean shop = new ShopBean();
        shop.setType(ShopBean.TYPE_LOVE);
        shop.setAddress("广东深圳");
        shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
        shop.setPrice(price);
        shop.setSell_num(15263);
        shop.setName(name);

        DbService.getInstance().saveNote(shop);

        shops.add(shop);
    }

}
