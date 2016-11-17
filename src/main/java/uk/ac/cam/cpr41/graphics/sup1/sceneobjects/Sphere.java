package uk.ac.cam.cpr41.graphics.sup1.sceneobjects;

import uk.ac.cam.cpr41.graphics.sup1.Ray;
import uk.ac.cam.cpr41.graphics.sup1.IntersectionPoint;
import uk.ac.cam.cpr41.graphics.sup1.Vector3;

public class Sphere extends SceneObject {
    private double radius;

    public Sphere(double radius, Vector3 origin) {
        super(origin);
        this.radius = radius;
    }

    // Source https://en.wikipedia.org/wiki/Line–sphere_intersection
    // Returns null if it doesn't have a solution
    public IntersectionPoint intersectsRayAtPoint(Ray ray) {
        // Intersection quadratic
        double determinant = Math.pow(ray.getDirection().dot(ray.getOrigin().minus(pos)), 2) -
                             Math.pow(ray.getOrigin().minus(pos).length(), 2) +
                             radius * radius;

        // If solutions exist return the closest
        if (determinant > 0) {
            double b = (ray.getDirection().dot(ray.getOrigin().minus(pos)));

            double t1 = - b + Math.sqrt(determinant);
            double t2 = - b - Math.sqrt(determinant);

            double tClose = Math.max(0, Math.min(Math.max(0, t1), Math.max(0, t2)));
            if (tClose != 0) {
                Vector3 intersection = ray.moveAlong(tClose);
                return new IntersectionPoint(intersection, getNormalAtPoint(intersection), ray, tClose);
            }
        }


        return null;
    }

    private Vector3 getNormalAtPoint(Vector3 point) {
        return point.minus(pos).normalise();
    }
}
