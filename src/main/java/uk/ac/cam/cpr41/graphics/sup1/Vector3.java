package uk.ac.cam.cpr41.graphics.sup1;

// A representation of a 3D vector
// All operations that return a vector return a new object
public class Vector3 {
    public final double x;
    public final double y;
    public final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double dot(Vector3 vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public double length() {
        return Math.sqrt(this.dot(this));
    }

    public Vector3 normalise() {
        double length = length();
        if (length == 0.0) return new Vector3(0, 0, 0); // Avoid divide by zero

        return new Vector3(x / length, y / length, z / length);
    }

    public Vector3 minus(Vector3 vector) {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3 add(Vector3 vector) {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3 scale(double factor) {
        return new Vector3(x * factor, y * factor, z * factor);
    }


    public Vector3 cross(Vector3 vector) {
        return new Vector3(
                y * vector.z - z * vector.y,
                z * vector.x - x * vector.z,
                x * vector.y - y * vector.x);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector3) {
            Vector3 v = (Vector3) obj;
            return v.x == x && v.y == y && v.z == z;
        }
        return super.equals(obj);
    }
}

