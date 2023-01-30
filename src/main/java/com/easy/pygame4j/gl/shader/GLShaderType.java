package com.easy.pygame4j.gl.shader;

import org.lwjgl.opengl.GL41;

public enum GLShaderType {

	VERTEX_SHADER(GL41.GL_VERTEX_SHADER),
	FRAGMENT_SHADER(GL41.GL_FRAGMENT_SHADER);
	
	int type;
	
	private GLShaderType(int type) {
		this.type = type;
	}
}
