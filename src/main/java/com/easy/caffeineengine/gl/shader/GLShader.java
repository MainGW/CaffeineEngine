package com.easy.caffeineengine.gl.shader;

import com.easy.caffeineengine.gl.GLObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.opengl.GL41C.*;

public class GLShader extends GLObject {
    
	private final Logger logger = LoggerFactory.getLogger(GLShader.class);

    public GLShader(ShaderType type, String src) {
    	
    	int shader = glCreateShader(type.type);

    	glShaderSource(shader, src);
    	glCompileShader(shader);
    	String warnLog = glGetShaderInfoLog(shader);
    	if(!warnLog.isEmpty()) {
    		logger.warn("Shader compilation log: " + warnLog);
    	}
    	int result = glGetShaderi(shader, GL_COMPILE_STATUS);
    	if(result != GL_TRUE) {
    		throw new RuntimeException("Shader compilation failed.");
    	}
    	setHandle(shader);
    	
    }

    @Override
    public void destroy() {
    	glDeleteShader(this.handle);
    	this.invalidateHandle();
    }

	public enum ShaderType {
		VERTEX_SHADER(GL_VERTEX_SHADER),
		FRAGMENT_SHADER(GL_FRAGMENT_SHADER);
		final int type;

		ShaderType(int type) {
			this.type = type;
		}
	}
   
}
