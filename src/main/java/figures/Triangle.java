package figures;

public class Triangle implements Figure {
    private final String figureType = "Треугольник";
    private final String backgroundColor;
    private final String borderColor;
    private final double a;
    private final double b;
    private final double c;

    public String getFigureType() {
        return figureType;
    }

    public Triangle(String backgroundColor, String borderColor, double a, double b, double c) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public double getArea() {
        double halfP = (a + b + c) / 2.0;
        return Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
    }

    public double getPerimeter() {
        return (a + b + c);
    }
}
