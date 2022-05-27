package com.oddisey.flappy;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main implements Runnable {

    private boolean isRunning = false;

    public void start() {
        isRunning = false;
        Thread thread = new Thread(this, "Game");
        thread.start();
    }

    private void init() {
        if (!glfwInit()) {
            // TODO: handle it
            System.out.println("Cannot initialize window!");
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        // Application's window size and title
        int width = 1280;
        int height = 720;
        String title = "Flappy Bird";
        long window = glfwCreateWindow(width, height, title, NULL, NULL);

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert videoMode != null;
        glfwSetWindowPos(window, videoMode.width(), videoMode.height());
    }

    public void run() {
        init();
        while (isRunning) {
            update();
            render();
        }
    }

    private void update() {}
    private void render() {}

    public static void main(String[] args) {
        new Main().start();
    }
}
