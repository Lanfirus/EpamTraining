package Task5.model;

import Task5.controller.Controller;

public class ModelEllipse implements Model, Cloneable{

    private double x;
    private double y;
    private double focalDistance;
    private double radiusA;
    private double radiusB;
    private Controller controller;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getFocalDistance() {
        return focalDistance;
    }

    public double getRadiusA() {
        return radiusA;
    }

    public double getRadiusB() {
        return radiusB;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setFocalDistance(double focalDistance) {
        this.focalDistance = focalDistance;
    }

    public void setRadiusA(double radiusA) {
        this.radiusA = radiusA;
    }

    public void setRadiusB(double radiusB) {
        this.radiusB = radiusB;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public String getAllData() {
        return toString();
    }

    @Override
    public String toString(){
        StringBuilder temp = new StringBuilder();
        temp.append("X = " + getX());
        temp.append(" Y = " + getY());
        temp.append(" FocalDistance = " + getFocalDistance());
        temp.append(" RadiusA = " + getRadiusA());
        temp.append(" RadiusB = " + getRadiusB());
        return temp.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof ModelEllipse)) return false;
        ModelEllipse test = (ModelEllipse) obj;
        if ((int)this.getX() == (int)test.getX() && (int)this.getY() == (int)test.getY() &&
            (int)this.getFocalDistance() == (int)test.getFocalDistance() && (int)this.getRadiusA() == (int)test.getRadiusA() &&
            (int)this.getRadiusB() == (int)test.getRadiusB()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int startValue = 31;
        double hashCode = ((getX() == 0) ? hashCode() : startValue*getX());
        hashCode = ((getY() == 0) ? hashCode() : hashCode*getY());
        hashCode = ((getFocalDistance() == 0) ? hashCode() : hashCode*getFocalDistance());
        hashCode = ((getRadiusA() == 0) ? hashCode() : hashCode*getRadiusA());
        hashCode = ((getRadiusB() == 0) ? hashCode() : hashCode*getRadiusB());
        return (int)hashCode;
    }

    @Override
    public ModelEllipse clone() throws CloneNotSupportedException{
        return (ModelEllipse)super.clone();
    }
}
