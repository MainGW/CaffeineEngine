package com.easy.pygame4j.gl.shader;

import com.easy.pygame4j.gl.GLObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.lwjgl.opengl.GL41C.*;

/**
 * The OpenGL Program Class.
 *
 */
public class GLProgram extends GLObject {

	public GLProgram(int handle) {
		this.setHandle(handle);
	}

	/**
	 * Bind a program.
	 */
	public void bind() {
		glUseProgram(this.handle);
	}
	
	public void unbind() {
		glUseProgram(0);
	}

	@Override
	public void destory() {
		glDeleteProgram(this.handle);
		this.invalidateHandle();
	}
	
	public static class ProgramBuilder {

		private final Logger logger = LoggerFactory.getLogger(ProgramBuilder.class);
		private final int program;
		
		public ProgramBuilder() {
			program = glCreateProgram();	
		}
		
		public ProgramBuilder attachShader(GLShader shader) {
			glAttachShader(this.program, shader.getHandle());
			return this;
		}
	
		public GLProgram link() {
			glLinkProgram(this.program);
			if(glGetProgrami(program, GL_LINK_STATUS) != GL_TRUE) {
				throw new RuntimeException("Program link failed.");
			}
			String warnLog;
			if(!(warnLog = glGetProgramInfoLog(this.program)).isEmpty()) {
				logger.warn(warnLog);
			}
			return new GLProgram(this.program);
		}
		
	}
}
