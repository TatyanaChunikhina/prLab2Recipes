package models;

public class Ingredient {
    public static enum UNITS{ml,gram,tsp,tbp,pc,cup};
    public String name;

    public String numeric;
    public Ingredient(String name, String numeric) {
        this.name = name;
        this.numeric = numeric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }
}
