package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    private Integer id;

    private String name;

    private String password;

    @Column(name = "carate_time")
    private Date carateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return carate_time
     */
    public Date getCarateTime() {
        return carateTime;
    }

    /**
     * @param carateTime
     */
    public void setCarateTime(Date carateTime) {
        this.carateTime = carateTime;
    }
}