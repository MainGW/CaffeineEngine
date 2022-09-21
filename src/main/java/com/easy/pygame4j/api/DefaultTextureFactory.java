package com.easy.pygame4j.api;

import com.easy.pygame4j.display.Texture2D;

public class DefaultTextureFactory implements ITextureFactory{

    @Override
    public ITexture getTexture(String textureType) {
        if(textureType == null) {
            return null;
        }

        if ("2d".equalsIgnoreCase(textureType)) {
            return new Texture2D();
        }
        return null;
    }

    @Override
    public ITexture getTextureById(String textureId) {
        //TODO: Finish Texture Factory
        return null;
    }
}
