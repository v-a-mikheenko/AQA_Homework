package figures;

public class Circle implements Figure {
    private final String figureType = "Круг";
    private final String backgroundColor;
    private final String borderColor;
    private final double r;

    public String getFigureType() {
        return figureType;
    }

    public Circle(String backgroundColor, String borderColor, double r) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.r = r;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public double getArea() {
        return (Math.PI * Math.pow(r, 2));
    }

    public double getPerimeter() {
        return (2 * Math.PI * r);
    }
}
