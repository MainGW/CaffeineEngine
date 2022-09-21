package com.easy.pygame4j.render;


import static org.lwjgl.opengl.GL41.*;

import java.util.ArrayList;

public class ShaderProgram {
    
    private final int program;
    
    private ArrayList<Shader> shaders;
    
    public abstract class ShaderProgramException extends RuntimeException {
        private final long serialVersionUID = 2348398843L;

        private String code;

        public ShaderProgramException() {

        }

        public ShaderProgramException(String message) {
            super(message);
        }

        public ShaderProgramException(String message, String code) {
            super(message);
            this.code = code;
        }

        public ShaderProgramException(String message, Throwable e) {
            super(message, e);
        }

        public ShaderProgramException(Throwable e) {
            super(e);
        }
    }

    public class ShaderProgramLinkException extends ShaderProgramException {

        private final long serialVersionUID = 2980983019L;

        private String code;

        public ShaderProgramLinkException() {

        }

        public ShaderProgramLinkException(String message) {
            super(message);
        }

        public ShaderProgramLinkException(String message, String code) {
            super(message);
            this.code = code;
        }

        public ShaderProgramLinkException(String message, Throwable e) {
            super(message, e);
        }

        public ShaderProgramLinkException(Throwable e) {
            super(e);
        }

    };
    
    private ShaderProgram() {
        program = glCreateProgram();
    }
    
    public void attachShader(Shader shader) {
        shaders.add(shader); glAttachShader(program, shader.getShaderId());
    }
    
    public void link() {
        glLinkProgram(program);
        if(glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            throw new ShaderProgramLinkException(glGetProgramInfoLog(program));
        }
    }

    public void use() {
        glUseProgram(program);
    }
}
