package figures;

public interface Figure {

    String getFigureType();

    String getBackgroundColor();

    String getBorderColor();

    double getArea();

    double getPerimeter();

    default void printFigureInfo() {
        System.out.println(getFigureType()
                + ": площадь " + String.format("%.2f", getArea())
                + " | периметр " + String.format("%.2f", getPerimeter())
                + " | цвет заливки " + getBackgroundColor()
                + " | цвет границ: " + getBorderColor());
    }
}
