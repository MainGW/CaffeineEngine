package com.easy.caffeineengine.gl.texture;

import com.easy.caffeineengine.gl.GLObject;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL41C.*;

public class GLTexture extends GLObject {

    private final int target, width, height;

    public GLTexture(int type, int width, int height) {
        this.handle = glGenTextures();
        this.target = type;
        this.width = width;
        this.height = height;
    }

    public void bind() {
        glBindTexture(this.target, this.handle);
    }

    public void unbind() {
        glBindTexture(this.target, 0);
    }

    public void nearestFilter() {
        glTexParameteri(this.target, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(this.target, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    }

    public void linearFilter() {
        glTexParameteri(this.target, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(this.target, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    }

    public void linearMipMapFilter() {
        genMipMap();
        glTexParameteri(this.target, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(this.target, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    }

    public void warpRepeat() {
        glTexParameteri(this.target, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(this.target, GL_TEXTURE_WRAP_T, GL_REPEAT);
    }

    public void loadImage(ByteBuffer data, int format) {
        glTexImage2D(this.target, 0, format, this.width, this.height, 0, format, GL_UNSIGNED_BYTE, data);
    }
    public void genMipMap() {
        glGenerateMipmap(this.handle);
    }
    @Override
    public void destroy() {
        glDeleteTextures(this.handle);
        this.invalidateHandle();
    }
}
