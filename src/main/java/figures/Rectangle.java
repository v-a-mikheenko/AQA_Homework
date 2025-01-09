package figures;

public class Rectangle implements Figure {
    private final String figureType = "Прямоугольник";
    private final String backgroundColor;
    private final String borderColor;
    private final double a;
    private final double b;

    public String getFigureType() {
        return figureType;
    }

    public Rectangle(String backgroundColor, String borderColor, double a, double b) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.a = a;
        this.b = b;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public double getArea() {
        return a * b;
    }

    public double getPerimeter() {
        return (a + b) * 2.0;
    }
}
