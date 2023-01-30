package com.easy.pygame4j.gl.shader;

import static org.lwjgl.opengl.GL41C.*;
import org.slf4j.*;

import com.easy.pygame4j.gl.GLObject;

public class GLShader extends GLObject {
    
	private final Logger logger = LoggerFactory.getLogger(GLShader.class);

    public GLShader(GLShaderType type, String src) {
    	
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
    public void destory() {
    	logger.trace("Shader object destoried");
    	glDeleteShader(this.handle);
    	this.invalidateHandle();
    }
   
}
