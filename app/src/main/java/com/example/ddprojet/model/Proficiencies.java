package com.example.ddprojet.model;

public class Proficiencies {
    String url;
    String name;

    public Proficiencies(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String isSkill(){
        if(name.contains("Skill:")){
            return (name.split(" ")[1]);
        }
        return null;
    }
}
