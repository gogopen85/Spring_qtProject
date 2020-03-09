package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Markings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataId;
    private int pointId;
    private int point;
    private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Markings(String dataId, int point) {
        this.dataId = dataId;
        this.point = point;
    }

    public Markings(String dataId, int point, String userId) {
        this.dataId = dataId;
        this.point = point;
        this.userId = userId;
    }

    public Markings(String dataId, int pointId, int point, String userId) {
        this.dataId = dataId;
        this.pointId = pointId;
        this.point = point;
        this.userId = userId;
    }
}
