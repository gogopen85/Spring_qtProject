package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Waveform_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String patient_id;
    private String starttime;
    private String endtime;
    private String wavetype;
    private String filepath;

    public Waveform_info() {}

    public Waveform_info(String patient_id, String starttime, String endtime, String wavetype, String filepath) {
        this.patient_id = patient_id;
        this.starttime = starttime;
        this.endtime = endtime;
        this.wavetype = wavetype;
        this.filepath = filepath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getWavetype() {
        return wavetype;
    }

    public void setWavetype(String wavetype) {
        this.wavetype = wavetype;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
