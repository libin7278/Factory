package com.libin.factory.green_dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by doudou on 2017/4/6.
 * 多个经销商
 */

@Entity
public class SaleBean {
    @Id(autoincrement = true)
    private Long id;

    private Long s_id;

    private String SName;

    @Generated(hash = 1554192705)
    public SaleBean(Long id, Long s_id, String SName) {
        this.id = id;
        this.s_id = s_id;
        this.SName = SName;
    }

    @Generated(hash = 973668987)
    public SaleBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getS_id() {
        return this.s_id;
    }

    public void setS_id(Long s_id) {
        this.s_id = s_id;
    }

    public String getSName() {
        return this.SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

}
