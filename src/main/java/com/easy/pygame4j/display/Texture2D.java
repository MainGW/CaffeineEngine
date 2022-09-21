package com.easy.pygame4j.display;

import com.easy.pygame4j.api.ITexture;
import static org.lwjgl.opengl.GL41.*;

public class Texture2D implements ITexture {

    private final int texture;

    public Texture2D() {
        this(0, 0, GL_MIRRORED_REPEAT, GL_MIRRORED_REPEAT, GL_LINEAR, GL_LINEAR_MIPMAP_NEAREST);
    }

    public Texture2D(int width, int height, int wrap_s, int wrap_t, int maxFilter, int minFilter) {

        texture = glGenTextures();
        bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WIDTH, width);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_HEIGHT, height);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap_s);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap_t);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, maxFilter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter);
        unbind();

    }

    @Override
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    @Override
    public void setTextureParameter(int parameter, int value) {

    }

    @Override
    public void texImage() {

    }

    @Override
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }
}
