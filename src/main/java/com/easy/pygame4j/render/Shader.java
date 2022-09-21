package com.easy.pygame4j.render;

import java.io.*;
import java.nio.IntBuffer;

import org.lwjgl.*;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL41.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Shader {
    private final int shader;

    public static class ShaderCompilationException extends RuntimeException {
        private final long serialVersionUID = 2980983019L;

        private String code;

        public ShaderCompilationException() {

        }

        public ShaderCompilationException(String message) {
            super(message);
        }

        public ShaderCompilationException(String message, String code) {
            super(message);
            this.code = code;
        }

        public ShaderCompilationException(String message, Throwable e) {
            super(message, e);
        }

        public ShaderCompilationException(Throwable e) {
            super(e);
        }
    }

    public Shader(String file, int type) throws ShaderCompilationException {
        StringBuilder builder = new StringBuilder();
        try ( FileInputStream fis = new FileInputStream(file) ) {
            byte[] bytes = new byte[2048];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, len));
            }
        }catch (IOException e) {
            throw new ShaderCompilationException(e);
        }

        String code = builder.toString();

        shader = glCreateShader(type);
        glShaderSource(shader, code);
        glCompileShader(shader);

        try (MemoryStack stack = MemoryStack.stackPush() ){
            IntBuffer buffer = stack.mallocInt(1);

            glGetShaderiv(shader, GL_COMPILE_STATUS, buffer);
            if(buffer.get(1) == GL_FALSE) {
                throw new ShaderCompilationException("Failed to compile Shader. Reason: " + glGetShaderInfoLog(shader));
            }

        } catch(Exception e) {
            throw new ShaderCompilationException(e);
        }
    }

    public int getShaderId() { return shader; }

}
