package uk.ac.cam.cpr41.graphics.sup1;

public class Light {
    private Vector3 pos;
    private double intensity = 7.0;

    public Light(Vector3 pos) {
        this.pos = pos;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public Vector3 getPos() {
        return pos;
    }
}
