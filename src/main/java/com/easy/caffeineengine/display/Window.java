package com.easy.caffeineengine.display;

import com.easy.caffeineengine.api.GLWindowCallBack;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final long window;


    public Window() {
        this(800, 600, "", NULL, NULL);
    }
    
    public Window(int width, int height, String title, long monitor, long share) {
       
    	GLFWErrorCallback.createPrint(System.err).set();
    	
    	if(!glfwInit()) {
    		throw new RuntimeException("Failed to initialize glfw.");
    	}
    	
    	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        
        window = glfwCreateWindow(width, height, title, monitor, share);
        if(window == NULL) {
        	glfwTerminate();
            throw new RuntimeException("Failed to create window.");
        }
        
        glfwMakeContextCurrent(this.window);
        GL.createCapabilities(); 
        
    }

    public void mainLoop(GLWindowCallBack callBack) {
    	while(!glfwWindowShouldClose(window)) {
    		callBack.GLWindowCallBackFunc(this, window);
    		glfwPollEvents();
    	}
    }
    
    public void show() {
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    public void render() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void destroy() {
        glfwDestroyWindow(window);
        glfwSetErrorCallback(null);
        glfwTerminate();
    }

}
