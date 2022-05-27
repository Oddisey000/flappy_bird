package com.oddisey.flappy;

import com.oddisey.flappy.input.Input;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main implements Runnable {

    private boolean isRunning = false;
    private long window;

    public void start() {
        isRunning = true;
        Thread thread = new Thread(this, "Game");
        thread.start();
    }

    private void init() {
        if (!glfwInit()) {
            System.out.println("Cannot initialize window!");
            return;
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        // Application's window size and title
        int width = 800;
        int height = 600;
        String title = "Flappy Bird";
        window = glfwCreateWindow(width, height, title, NULL, NULL);

        // Checks if window could be build correctly
        if (window == NULL) {
            System.out.println("Something bad happened to window");
            return;
        }

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert videoMode != null;

        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
        glfwSetKeyCallback(window, new Input());
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(1.0f, .0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGL " + glGetString(GL_VERSION));
    }

    public void run() {
        init();
        while (isRunning) {
            update();
            render();
            if (glfwWindowShouldClose(window)) {
                isRunning = false;
            }
        }
    }

    private void update() {
        glfwPollEvents();
        if (Input.keys[GLFW_KEY_SPACE]) {
            System.out.println("Flying!");
        }
    }
    private void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
