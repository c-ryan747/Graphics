package uk.ac.cam.cpr41.graphics.sup1;

import uk.ac.cam.cpr41.graphics.sup1.sceneobjects.SceneObject;

import java.awt.*;
import java.util.ArrayList;

public class Scene {
    private ArrayList<SceneObject> sceneObjects = new ArrayList<>();
    private ArrayList<Light> lights = new ArrayList<>();

    // Fairly arbitrary default settings
    private Color backgroundColour = new Color(137, 250, 255);
    private boolean ambientLightOn = true;
    private double ambientIntensity = 1.0;
    private static double MAX_INTENSITY = 10.0 * 255.0;

    public Color getColourForRay(Ray ray) {
        SceneObject closest = null;
        IntersectionPoint point = null;

        // Find the closest intersecting object
        for (SceneObject sceneObject : sceneObjects) {
            IntersectionPoint intersectionPoint = sceneObject.intersectsRayAtPoint(ray);
            if (intersectionPoint != null && (point == null || intersectionPoint.distanceAlongRay < point.distanceAlongRay)) {
                closest = sceneObject;
                point = intersectionPoint;
            }
        }

        return closest == null ? backgroundColour : colourForObject(closest, point);
    }

    private Color colourForObject(SceneObject sceneObject, IntersectionPoint point) {
        double intensity = getIntensity(sceneObject, point);

        Color objectsBaseColour = sceneObject.getColor();
        Double intensityR = objectsBaseColour.getRed() * intensity;
        Double intensityG = objectsBaseColour.getGreen() * intensity;
        Double intensityB = objectsBaseColour.getBlue() * intensity;

        // Get the values in range (0 - 255)
        double maxIndividual = Math.max(Math.max(Math.max(MAX_INTENSITY, intensityR), intensityG), intensityB);
        intensityR *= 255.0 / maxIndividual;
        intensityG *= 255.0 / maxIndividual;
        intensityB *= 255.0 / maxIndividual;

        return new Color(intensityR.intValue(), intensityG.intValue(), intensityB.intValue());
    }

    private double getIntensity(SceneObject sceneObject, IntersectionPoint point) {
        double intensity = 0;

        // Ambient illumination
        if (ambientLightOn) {
            intensity += ambientIntensity;
        }

        Vector3 N = point.normal;

        // Light from each source
        for (Light light : lights) {
            // Diffuse
            double Ilight = light.getIntensity();
            Vector3 L = light.getPos().minus(sceneObject.getPos()).normalise();
            double cosTheta = N.dot(L);

            // Check it's being lit from the front
            if (cosTheta > 0) {
                intensity += Ilight * cosTheta;
            }

            // Specular
            Vector3 R = L.minus(N.scale(2 * L.dot(N)));
            Vector3 V = point.ray.getOrigin().minus(point.point).scale(-1).normalise();
            double rDotV = R.dot(V);

            // Check it's being lit from the front
            if (rDotV > 0) {
                intensity += Ilight * Math.pow(rDotV, sceneObject.getPhongRoughness());
            }
        }

        return intensity;
    }


    public void addSceneObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public void setAmbientLightOn(boolean ambientLightOn) {
        this.ambientLightOn = ambientLightOn;
    }
}