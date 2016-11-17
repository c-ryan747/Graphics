package uk.ac.cam.cpr41.graphics.sup1.sceneobjects;

import uk.ac.cam.cpr41.graphics.sup1.Ray;
import uk.ac.cam.cpr41.graphics.sup1.IntersectionPoint;
import uk.ac.cam.cpr41.graphics.sup1.Vector3;

public class Cube extends SceneObject {
    private double size;
    private Plane[] sides = new Plane[6];
    private static final Vector3[] normals = {
            new Vector3(1, 0, 0),
            new Vector3(-1, 0, 0),
            new Vector3(0, 1, 0),
            new Vector3(0, -1, 0),
            new Vector3(0, 0, 1),
            new Vector3(0, 0, -1),
    };

    public Cube(double size, Vector3 pos) {
        super(pos);
        this.size = size;

        // Generate a Plane for each side
        for (int i = 0; i < 6; i++) {
            Vector3 normal = normals[i];
            Vector3 center = pos.add(normal.scale(size / 2));

            sides[i] = new Plane(center, normal) {
                @Override
                public boolean pointValid(Vector3 point) {
                    Vector3 local = point.minus(center);
                    return Math.abs(local.x) <= size / 2 && Math.abs(local.y) <= size / 2 && Math.abs(local.z) <= size / 2;
                }
            };
        }
    }

    @Override
    public IntersectionPoint intersectsRayAtPoint(Ray ray) {
        IntersectionPoint closest = new IntersectionPoint(null, null, ray, Double.MAX_VALUE);

        for (Plane side : sides) {
            IntersectionPoint point = side.intersectsRayAtPoint(ray);
            if (point != null && point.distanceAlongRay < closest.distanceAlongRay) {
                closest = point;
            }
        }

        return closest.point == null ? null : closest;
    }
}
