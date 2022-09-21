import com.easy.pygame4j.display.Window;
import com.easy.pygame4j.render.Shader;
import org.junit.jupiter.api.*;
import org.lwjgl.opengl.GL41;
import org.lwjgl.system.Configuration;

import java.util.Objects;


public class ShaderTest {

    @Test
    public void fileReadTest() throws Window.WindowException {

        Window window = new Window();
        window.show();

        Configuration.GLFW_CHECK_THREAD0.set(false);

        String name = Objects.requireNonNull(this.getClass().getClassLoader().getResource("vert.glsl")).getPath();

        System.out.println(name);

        Shader shader = new Shader(name, GL41.GL_VERTEX_SHADER);

    }
}
