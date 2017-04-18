package com.libin.factory.green_dao.bean;

import com.libin.factory.green_dao.dao.DaoSession;
import com.libin.factory.green_dao.dao.ProcutedBeanDao;
import com.libin.factory.green_dao.dao.SaleBeanDao;
import com.libin.factory.green_dao.dao.ShopBeanDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * Created by doudou on 2017/3/14.
 * 商品
 */
@Entity
public class ShopBean {
    public static final int TYPE_LOVE = 0x02;

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String name;
    @Property(nameInDb = "price")
    private String price;

    private Long p_id;  // 关联两个表的外键
    @ToOne(joinProperty = "p_id")
    private ProcutedBean procutedBean;  // 源实体

    private Long s_id;
    @ToMany(referencedJoinProperty = "s_id")
    private List<SaleBean> saleBeen;

    private int sell_num;
    private String image_url;
    private String address;
    private int type;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 757474161)
    private transient ShopBeanDao myDao;

    @Generated(hash = 162137259)
    private transient Long procutedBean__resolvedKey;

    @Generated(hash = 2089758551)
    public ShopBean(Long id, String name, String price, Long p_id, Long s_id, int sell_num,
            String image_url, String address, int type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.p_id = p_id;
        this.s_id = s_id;
        this.sell_num = sell_num;
        this.image_url = image_url;
        this.address = address;
        this.type = type;
    }

    @Generated(hash = 748345971)
    public ShopBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSell_num() {
        return this.sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getP_id() {
        return this.p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1467232332)
    public ProcutedBean getProcutedBean() {
        Long __key = this.p_id;
        if (procutedBean__resolvedKey == null
                || !procutedBean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProcutedBeanDao targetDao = daoSession.getProcutedBeanDao();
            ProcutedBean procutedBeanNew = targetDao.load(__key);
            synchronized (this) {
                procutedBean = procutedBeanNew;
                procutedBean__resolvedKey = __key;
            }
        }
        return procutedBean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1139434249)
    public void setProcutedBean(ProcutedBean procutedBean) {
        synchronized (this) {
            this.procutedBean = procutedBean;
            p_id = procutedBean == null ? null : procutedBean.getId();
            procutedBean__resolvedKey = p_id;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1858665484)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getShopBeanDao() : null;
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", p_id=" + getP_id() +
                ", procutedBean=" + getProcutedBean() +
                ", sell_num=" + sell_num +
                ", image_url='" + image_url + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", procutedBean__resolvedKey=" + procutedBean__resolvedKey +
                '}';
    }

    public Long getS_id() {
        return this.s_id;
    }

    public void setS_id(Long s_id) {
        this.s_id = s_id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 711909168)
    public List<SaleBean> getSaleBeen() {
        if (saleBeen == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SaleBeanDao targetDao = daoSession.getSaleBeanDao();
            List<SaleBean> saleBeenNew = targetDao._queryShopBean_SaleBeen(id);
            synchronized (this) {
                if (saleBeen == null) {
                    saleBeen = saleBeenNew;
                }
            }
        }
        return saleBeen;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1147493591)
    public synchronized void resetSaleBeen() {
        saleBeen = null;
    }
}
