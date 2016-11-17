package uk.ac.cam.cpr41.graphics.sup1.sceneobjects;

import uk.ac.cam.cpr41.graphics.sup1.Ray;
import uk.ac.cam.cpr41.graphics.sup1.IntersectionPoint;
import uk.ac.cam.cpr41.graphics.sup1.Vector3;

import java.awt.*;

public abstract class SceneObject {
    protected Vector3 pos;
    protected Color color;
    protected double phongRoughness = 5.0;

    public SceneObject(Vector3 pos) {
        this.pos = pos;
    }

    // Find the closest plane if any that intersects this ray
    // Returns null if it doesn't intersect
    public abstract IntersectionPoint intersectsRayAtPoint(Ray ray);

    // Various setters + getters
    public double getPhongRoughness() {
        return phongRoughness;
    }

    public void setPhongRoughness(double phongRoughness) {
        this.phongRoughness = phongRoughness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector3 getPos() {
        return pos;
    }
}
