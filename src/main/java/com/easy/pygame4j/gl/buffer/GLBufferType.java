package com.easy.pygame4j.gl.buffer;
import static org.lwjgl.opengl.GL41C.*;

public enum GLBufferType {
	VERTEX_ARRAY_BUFFER(GL_ARRAY_BUFFER),
	ELEMENT_ARRAY_BUFFER(GL_ELEMENT_ARRAY_BUFFER);
	
	int id;
	private GLBufferType(int id) {
		this.id = id;
	}
}
