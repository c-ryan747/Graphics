package uk.ac.cam.cpr41.graphics.sup1;

// Wrapper for data related to an intersection
public class IntersectionPoint {
    public final Vector3 normal;
    public final Vector3 point;
    public final Ray ray;
    public final double distanceAlongRay;

    public IntersectionPoint(Vector3 point, Vector3 normal, Ray ray, double distanceAlongRay) {
        this.normal = normal;
        this.point = point;
        this.ray = ray;
        this.distanceAlongRay = distanceAlongRay;
    }
}
