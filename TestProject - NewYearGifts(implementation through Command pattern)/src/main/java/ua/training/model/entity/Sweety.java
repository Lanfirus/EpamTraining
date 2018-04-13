package ua.training.model.entity;

public enum Sweety {
    CARAMEL_CANDY (12, 80, "Caramel Candy"),
    CHOCOLATE_CANDY   (13, 60, "Chocolate Candy"),
    JELLY_CANDY   (11, 40, "Jelly Candy"),
    LOLLIPOP_CANDY    (16, 90, "Lollipop Candy"),
    WAFFLE (18, 20, "Waffle"),
    MARSHMALLOW  (9, 30, "Marshmallow");

    private final Integer weight;
    private final Integer sugarValue;
    private final String name;

    Sweety(Integer weight, Integer sugarValue, String name) {
        this.weight = weight;
        this.sugarValue = sugarValue;
        this.name = name;
    }

    public Integer getWeight() { return weight; }
    public Integer getSugarValue() { return sugarValue; }
    public String getName() {return name;}
}