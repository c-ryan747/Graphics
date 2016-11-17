package uk.ac.cam.cpr41.graphics.sup1.sceneobjects;

import uk.ac.cam.cpr41.graphics.sup1.Ray;
import uk.ac.cam.cpr41.graphics.sup1.IntersectionPoint;
import uk.ac.cam.cpr41.graphics.sup1.Vector3;

public class Cylinder extends SceneObject {
    private double radius;
    private double height;
    private Plane[] lids;

    public Cylinder(double radius, double height, Vector3 origin) {
        super(origin);
        this.radius = radius;
        this.height = height;

        // Create the top and bottom lids
        lids = new Plane[2];
        lids[0] = new Plane(pos.add(new Vector3(0, height / 2, 0)), new Vector3(0, 1, 0)) {
            @Override
            public boolean pointValid(Vector3 point) {
                return point.minus(this.pos).length() <= radius;
            }
        };
        lids[1] = new Plane(pos.minus(new Vector3(0, height / 2, 0)), new Vector3(0, -1, 0)) {
            @Override
            public boolean pointValid(Vector3 point) {
                return point.minus(this.pos).length() <= radius;
            }
        };
    }

    @Override
    public IntersectionPoint intersectsRayAtPoint(Ray ray) {
        IntersectionPoint closest = new IntersectionPoint(null, null, ray, Double.MAX_VALUE);

        // Infinite cylinder intersection quadratic
        double a = ray.getDirection().x * ray.getDirection().x +
                   ray.getDirection().z * ray.getDirection().z;
        double b = 2 * (ray.getDirection().x * ray.getOrigin().x - pos.x * ray.getDirection().x +
                        ray.getDirection().z * ray.getOrigin().z - pos.z * ray.getDirection().z);
        double c = ray.getOrigin().x * ray.getOrigin().x - 2 * ray.getOrigin().x * pos.x + pos.x * pos.x +
                   ray.getOrigin().z * ray.getOrigin().z - 2 * ray.getOrigin().z * pos.z + pos.z * pos.z
                   - radius * radius;
        double determinant = b * b - 4.0 * a * c;

        // If solutions exist check they're in our finite cylinder
        if (determinant > 0) {
            double t1 = (-b + Math.sqrt(determinant)) / (2.0 * a);
            double t2 = (-b - Math.sqrt(determinant)) / (2.0 * a);

            Vector3 side1 = ray.moveAlong(t1);
            Vector3 side2 = ray.moveAlong(t2);

            if (t1 >= 0 && Math.abs(side1.y - pos.y) <= Math.abs(height / 2)) {
                if (t1 < closest.distanceAlongRay) {
                    closest = new IntersectionPoint(side1, side1.minus(new Vector3(pos.x, side1.y, pos.z)).normalise(), ray, t1);
                }
            }

            if (t2 >= 0 && Math.abs(side2.y - pos.y) <= Math.abs(height / 2)) {
                if (t2 < closest.distanceAlongRay) {
                    closest = new IntersectionPoint(side2, side2.minus(new Vector3(pos.x, side2.y, pos.z)).normalise(), ray, t2);
                }
            }
        }

        // Also check for intersections on the lids
        for (Plane lid : lids) {
            IntersectionPoint point = lid.intersectsRayAtPoint(ray);
            if (point != null && point.distanceAlongRay < closest.distanceAlongRay) {
                closest = point;
            }
        }

        return closest.point == null ? null : closest;
    }
}
