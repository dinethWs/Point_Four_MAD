package com.example.products;


public class VisitingModel {

    String id, name, address, Phone,Date,Time;

    public VisitingModel(){}

    public VisitingModel(String id, String name, String address, String Phone, String Date, String Time){
        this.id=id;
        this.name=name;
        this.address=address;
        this.Phone=Phone;
        this.Date=Date;
        this.Time=Time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
