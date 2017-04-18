package com.libin.factory.green_dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by doudou on 2017/4/6.
 * 唯一生产公司 (一对一)
 */

@Entity
public class ProcutedBean {
    @Id(autoincrement = true)
    private Long id;

    private String city;

    @Unique
    private String company;

    @Generated(hash = 719103365)
    public ProcutedBean(Long id, String city, String company) {
        this.id = id;
        this.city = city;
        this.company = company;
    }

    @Generated(hash = 433527634)
    public ProcutedBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
