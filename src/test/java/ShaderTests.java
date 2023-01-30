import com.easy.pygame4j.api.GLWindowCallBack;
import com.easy.pygame4j.display.Window;
import com.easy.pygame4j.gl.buffer.*;
import com.easy.pygame4j.gl.shader.*;

import org.junit.jupiter.api.*;

import org.slf4j.*;

import org.lwjgl.Version;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL41C.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class ShaderTests {
	
	private final Logger logger = LoggerFactory.getLogger(ShaderTests.class);

    @Test
    public void programLinkTest() throws IOException {
    	
        Window window = new Window();
       
        logger.debug("LWJGL  Version: " + Version.getVersion());
    	logger.debug("OpenGL Version: " + glGetString(GL_VERSION));
        
        
        Path path = Paths.get(this.getClass().getClassLoader().getResource("vert.glsl").getPath());
        String src = Files.readString(path);
        logger.trace("Loaded Vertex shader source file: {}", path.toString()); 
        
        GLShader vert = new GLShader(GLShaderType.VERTEX_SHADER, src);
        GLProgram program = new GLProgram.ProgramBuilder().attachShader(vert).link();

        window.show();
        
        window.mainLoop(new GLWindowCallBack() {
			
			@Override
			public void GLWindowCallBackFunc(Window win, long handle) {
				win.render();
			}

		});

        window.destroy();
        
    }
    
    private final float verticles[] = {
    		0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
    		-0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
    		0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f
    };
   
    private final ClassLoader loader = this.getClass().getClassLoader();
    
    @Test
    public void drawTriangle() {
    	
    	Window win = new Window();
    	
    	glEnable(GL_DEPTH_TEST);
    	
    	try (MemoryStack stack = stackPush()) {

    		GLShader vertex = new GLShader(GLShaderType.VERTEX_SHADER, Files.readString(Paths.get(loader.getResource("vert.glsl").getPath())));
    		GLShader fragment = new GLShader(GLShaderType.FRAGMENT_SHADER, Files.readString(Paths.get(loader.getResource("frag.glsl").getPath())));
    		GLProgram program = new GLProgram.ProgramBuilder()
    				.attachShader(vertex)
    				.attachShader(fragment)
    				.link();

    		GLBuffer vbo = new GLBuffer(GLBufferType.VERTEX_ARRAY_BUFFER);
    		glBufferData(vbo.getHandle(), verticles, GL_STATIC_DRAW);

    		GLVertexArray vao = new GLVertexArray();
    		vao.bind();
    		vao.setAttribute(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
    		vao.setAttribute(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
    		program.bind();
    		win.mainLoop(new GLWindowCallBack() {
				@Override
				public void GLWindowCallBackFunc(Window win, long handle) {
					glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
					glClear(GL_COLOR|GL_DEPTH);

					vao.bind();
					glDrawArrays(GL_TRIANGLES, 0, 3);
					win.render();
				}
			});
    		
    		vertex.destory();
    		fragment.destory();
    		program.destory();
    		vbo.destory();
    		vao.destory();

    	} catch(Exception e) {
    		logger.error(e.getMessage());
    		throw new RuntimeException(e);
    	}
    }
}
