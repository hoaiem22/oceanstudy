package hci201.se1171.oceanstudy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Fish implements Serializable {

    private int id;
    private String name;
    private double weight;
    private double lenght;
    private double height;
    private int deep;
    private int age;
    private String img;
    private String video;
    private String status;

    public Fish(int id, String name, double weight, double lenght, double height, int deep, int age, String img, String video, String status) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.lenght = lenght;
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

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
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

    @Override
    public String toString() {
        return id + "," + name + "," + weight + "," + lenght + "," + height + "," + deep + "," + age + "," + img + "," + video
                + "," + status;
    }
}