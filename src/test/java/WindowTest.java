import org.junit.jupiter.api.Test;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowTest {

    @Test
    public void testMain() {
        glfwInit();

        long window = glfwCreateWindow(800, 600, "Hello, world!", NULL, NULL);
        glfwWindowHint(GLFW_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_VERSION_MAJOR, 6);
        glfwWindowHint(GLFW_OPENGL_CORE_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        while(!glfwWindowShouldClose(window)) {

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwTerminate();
    }
}
