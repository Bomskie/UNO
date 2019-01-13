package shared;

public enum Color {
    Rood("Red"),
    Blauw("Blue"),
    Geel("Goldenrod"),
    Groen("Green");

    private final String color;
    Color(String color){this.color = color;}
    public String getValue(){return color;}
}
