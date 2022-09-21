package com.easy.pygame4j.api;

public interface ITextureFactory {

    ITexture getTexture(String textureType);

    ITexture getTextureById(String textureId);

}
