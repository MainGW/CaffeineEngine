package com.easy.caffeineengine.gl.buffer;

import com.easy.caffeineengine.gl.GLObject;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL41C.*;

public class GLBuffer extends GLObject {

	public GLBuffer(BufferType type) {
		int bufferObj = glGenBuffers();
		glBindBuffer(type.id, bufferObj);
		this.setHandle(bufferObj);
	}
	
	public void data(ByteBuffer data, int method) {
		glBufferData(this.handle, data, method);
	}

	public void data(FloatBuffer data, int method) {
		glBufferData(this.handle, data, method);
	}
	
	@Override
	public void destroy() {
		glDeleteBuffers(this.handle);
		this.invalidateHandle();
	}

	public enum BufferType {

		VERTEX_ARRAY_BUFFER(GL_ARRAY_BUFFER),
		ELEMENT_ARRAY_BUFFER(GL_ELEMENT_ARRAY_BUFFER);
		final int id;
		BufferType(int id) {
			this.id = id;
		}
	}
}
