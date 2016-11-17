package uk.ac.cam.cpr41.graphics.sup1;

import uk.ac.cam.cpr41.graphics.sup1.sceneobjects.Cube;
import uk.ac.cam.cpr41.graphics.sup1.sceneobjects.Cylinder;
import uk.ac.cam.cpr41.graphics.sup1.sceneobjects.Sphere;

import java.awt.*;

public class TestScene {
    // Setup a simple test scene with various objects
    public static void main(String[] args) {
        Scene scene = new Scene();

        Sphere sphere = new Sphere(10.0, new Vector3(-20, 20, 60.0));
        sphere.setColor(Color.green);
        scene.addSceneObject(sphere);

        Cylinder cylinder = new Cylinder(10.0, 10, new Vector3(0, 20, 20));
        cylinder.setColor(Color.green);
        scene.addSceneObject(cylinder);

        Cube cube = new Cube(7.0, new Vector3(30, -30, 20));
        cube.setColor(Color.green);
        scene.addSceneObject(cube);

        Sphere smaller = new Sphere(10.0, new Vector3(-30, 30, 80.0));
        smaller.setColor(Color.orange);
        scene.addSceneObject(smaller);

        Sphere closer = new Sphere(10.0, new Vector3(-10, 10, 40.0));
        closer.setColor(Color.blue);
        scene.addSceneObject(closer);

        Light light = new Light(new Vector3(-20, 20, -20));
        light.setIntensity(10.0);
        scene.addLight(light);
        scene.setAmbientLightOn(true);

        Display display = new Display(600, 600);
        display.setScene(scene);
    }
}
