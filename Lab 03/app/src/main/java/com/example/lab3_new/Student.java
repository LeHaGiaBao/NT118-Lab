package com.example.lab3_new;

public class Student {
    private int id;
    private String name;
    private int age;

    private float score;

    public Student(int id, String name, int age, float score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getScore() {return score;}
}
