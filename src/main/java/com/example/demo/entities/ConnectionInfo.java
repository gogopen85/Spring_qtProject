package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table
public class ConnectionInfo {
    @Id
    @Column(nullable = false)
    private String name; // db name(user friendly)
    @Column(nullable = false)
    private String host; //ipaddress (xxx.xxx.xxx.xxx)
    @Column(nullable = false)
    private String userName; //db login id
    @Column(nullable = false) // db login password
    private String password;

    @Column(nullable = false) // db login password
    private String databaseName;  //db name

    @Column(nullable = false) // db login password
    private String schemaName;  //db schema name

    public ConnectionInfo() {
    }

    public ConnectionInfo(String name, String host, String userName, String password, String databaseName, String schemaName) {
        this.name = name;
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        this.schemaName = schemaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}
