package com.easy.pygame4j.gl.buffer;

import com.easy.pygame4j.gl.GLObject;
import static org.lwjgl.opengl.GL41C.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class GLBuffer extends GLObject {

	public GLBuffer(GLBufferType type) {
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
	public void destory() {
		glDeleteBuffers(this.handle);
		this.invalidateHandle();
	}
}
