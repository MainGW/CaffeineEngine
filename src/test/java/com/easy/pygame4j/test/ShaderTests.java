package com.easy.pygame4j.test;
import com.easy.caffeineengine.display.Window;
import com.easy.caffeineengine.gl.buffer.GLBuffer;
import com.easy.caffeineengine.gl.buffer.GLBuffer.BufferType;
import com.easy.caffeineengine.gl.buffer.GLVertexArray;
import com.easy.caffeineengine.gl.shader.GLProgram;
import com.easy.caffeineengine.gl.shader.GLShader;
import com.easy.caffeineengine.gl.shader.GLShader.ShaderType;

import org.junit.jupiter.api.*;

import org.slf4j.*;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL41C.*;
import static org.lwjgl.system.MemoryStack.*;

import java.nio.file.*;

public class ShaderTests {
	
	private final Logger logger = LoggerFactory.getLogger(ShaderTests.class);

    private final float[] vertices = {
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

    		GLShader vertex = new GLShader(ShaderType.VERTEX_SHADER, Files.readString(Paths.get(loader.getResource("vert.glsl").getPath())));
    		GLShader fragment = new GLShader(ShaderType.FRAGMENT_SHADER, Files.readString(Paths.get(loader.getResource("frag.glsl").getPath())));
    		GLProgram program = new GLProgram.ProgramBuilder()
    				.attachShader(vertex)
    				.attachShader(fragment)
    				.link();

    		GLBuffer vbo = new GLBuffer(BufferType.VERTEX_ARRAY_BUFFER);
    		glBufferData(vbo.getHandle(), vertices, GL_STATIC_DRAW);

    		GLVertexArray vao = new GLVertexArray();
    		vao.bind();
    		vao.setAttribute(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
    		vao.setAttribute(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
    		program.bind();
    		
    		vao.bind();
    		glDrawArrays(GL_TRIANGLES, 0, 3);
    		win.render();

    		vertex.destroy();
    		fragment.destroy();
    		program.destroy();
    		vbo.destroy();
    		vao.destroy();
    		
    		win.destroy();

    	} catch(Exception e) {
    		logger.error(e.getMessage());
    		throw new RuntimeException(e);
    	}
    }
}
