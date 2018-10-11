package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_area")
public class TbArea {
    @Id
    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_name")
    private String areaName;

    private Integer priority;

    @Column(name = "carate_time")
    private Date carateTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;

    /**
     * @return area_id
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * @return area_name
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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

    /**
     * @return last_edit_time
     */
    public Date getLastEditTime() {
        return lastEditTime;
    }

    /**
     * @param lastEditTime
     */
    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}