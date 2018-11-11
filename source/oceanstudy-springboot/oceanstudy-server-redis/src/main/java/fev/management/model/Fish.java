/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fev.management.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author Admin
 */
public class Fish{
    
    private int id;
    private String name;
    private double weight;
    private double length;
    private double height;
    private int deep;
    private int age;
    private String img;
    private String video;
    private String status;

    public Fish() {
    }

    public Fish(int id, String name, double weight, double length, double height, int deep, int age, String img, String video, String status) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.deep = deep;
        this.age = age;
        this.img = img;
        this.video = video;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String stringToRedis() {
        return id + "," + name + "," + weight + "," + length + "," + height + "," + deep + "," + age + "," + img + "," + video + "," + status;
    }

    @Override
    public String toString() {
        return "Fish{" + "id=" + id + ", name=" + name + ", weight=" + weight + ", length=" + length + ", height=" + height + ", deep=" + deep + ", age=" + age + ", img=" + img + ", video=" + video + ", status=" + status + '}';
    }
    
    
}
