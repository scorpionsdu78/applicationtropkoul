package com.example.ddprojet.model;

public class Proficiency {
    String url;
    String name;
    String subName;

    public Proficiency(String url, String name) {
        this.url = url;
        this.name = name;

        String regex = "Skill: ";
        if(name.contains(regex)){
            System.out.println("find");
            this.subName = name.split(regex)[1];
            this.name = regex.split(": ")[0];
        }
        System.out.println("Proficiency : " + this.name + " | " + this.subName);
    }

    public String getUrl() {
        return this.url;
    }

    public String getName() {
        return this.name;
    }

    public String getSubName() {
        return this.subName;
    }

    public boolean isSkill(){
        if(name.equals("Skill")){
            return true;
        }
        return false;
    }
}
