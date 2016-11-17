package uk.ac.cam.cpr41.graphics.sup1.sceneobjects;

import uk.ac.cam.cpr41.graphics.sup1.Ray;
import uk.ac.cam.cpr41.graphics.sup1.IntersectionPoint;
import uk.ac.cam.cpr41.graphics.sup1.Vector3;

public abstract class Plane extends SceneObject {
    public final Vector3 normal;

    public Plane(Vector3 centre, Vector3 normal) {
        super(centre);
        this.normal = normal;
    }

    public IntersectionPoint intersectsRayAtPoint(Ray ray) {
        if (ray.getDirection().equals(normal)) return null; // Never meet

        double t = planeIntersectsRayAtDistance(ray, normal, pos);
        Vector3 intersection = ray.moveAlong(t);

        if (pointValid(intersection)) {
            return new IntersectionPoint(intersection, normal, ray, t);
        }

        return null;
    }

    // To be implemented by subclass
    // Limits this infinite plane to a finite one
    public abstract boolean pointValid(Vector3 point);

    public double planeIntersectsRayAtDistance(Ray ray, Vector3 normal, Vector3 point) {
        return (point.minus(ray.getOrigin()).dot(normal)) / (ray.getDirection().dot(normal));
    }
}
