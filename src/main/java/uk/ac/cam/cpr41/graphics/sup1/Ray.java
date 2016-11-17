package uk.ac.cam.cpr41.graphics.sup1;

// A vector line representation
public class Ray {
    private Vector3 origin;
    private Vector3 direction;

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction.normalise();
    }

    public Vector3 getOrigin() {
        return origin;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public Vector3 moveAlong(double d) {
        return origin.add(direction.scale(d));
    }
}
