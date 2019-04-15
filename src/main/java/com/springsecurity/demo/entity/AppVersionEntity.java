package com.springsecurity.demo.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "app_version")
public class AppVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String version;
    private String title;
    private Date time;

    public AppVersionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AppVersionEntity{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                '}';
    }
}
