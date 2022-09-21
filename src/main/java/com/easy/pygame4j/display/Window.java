package com.easy.pygame4j.display;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private final long window;

    public static class WindowException extends Exception {
        public WindowException(String what) { super(what); }
    }

    public Window() throws WindowException {
        this(800, 600, "", NULL, NULL);
    }

    public Window(int width, int height, String title, long monitor, long share) throws WindowException{
        glfwInit();

        window = glfwCreateWindow(width, height, title, monitor, share);
        if(window == NULL) {
            throw new WindowException("Failed to create window. Reason: ");
        }

        glfwDefaultWindowHints();

    }

    public void show() {
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    public void render() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void destroy() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

}
