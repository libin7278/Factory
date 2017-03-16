package com.libin.factory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.libin.factory.R;
import com.libin.factory.green_dao.bean.ShopBean;
import com.libin.factory.green_dao.dao.LoveDao;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoAvtivity extends AppCompatActivity {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;

    private List<ShopBean> shops;

    String TAG = GreenDaoAvtivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_avtivity);

        findViewId();

        setOnClick();

    }

    private void findViewId() {
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_query = (Button) findViewById(R.id.btn_query);
    }

    private void setOnClick() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDate();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDate();

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryDate();
            }
        });


    }


    private void addDate() {
        ShopBean shop = new ShopBean();
        shop.setType(ShopBean.TYPE_LOVE);
        shop.setAddress("广东深圳");
        shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
        shop.setPrice("19.40");
        shop.setSell_num(15263);
        shop.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮");
        LoveDao.insertLove(shop);
    }

    private void queryDate() {
        shops = new ArrayList<>();
        shops = LoveDao.queryLove();

        Logger.e(shops.get(0).getName());
    }

    private void deleteDate() {
        if (!shops.isEmpty()) {
            LoveDao.deleteLove(shops.get(0).getId());
        }
    }

    private void updateDate() {
        if (!shops.isEmpty()) {
            ShopBean shop = shops.get(0);
            shop.setName("我是修改的名字");
            LoveDao.updateLove(shop);
        }
    }
}
