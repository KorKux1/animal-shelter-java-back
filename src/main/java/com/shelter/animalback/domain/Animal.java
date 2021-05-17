package com.shelter.animalback.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Animal {
    private String name;
    private String breed;
    private String gender;
    private boolean vaccinated;
    private String[] vaccines;
    private long id;

    public Animal(long id, String name, String breed, String gender, boolean vaccinated, String[] vaccines) {
        this(name, breed, gender, vaccinated, vaccines);
        this.id = id;
    }

    public Animal(String name, String breed, String gender, boolean vaccinated, String[] vaccines) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.vaccinated = vaccinated;
        this.vaccines = vaccines;
    }
}