package com.easy.caffeineengine.gl.buffer;

import com.easy.caffeineengine.gl.GLObject;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL41C.*;

public class GLVertexArray extends GLObject {

	public GLVertexArray() {
		int vao = glGenVertexArrays();
		this.setHandle(vao);
	}
	
	public void bind() {
		glBindVertexArray(this.handle);
	}
	
	public void unbind() {
		glBindVertexArray(0);
	}
	
	public void setAttribute(int index, int size, int dataType, boolean normalize, int strength, ByteBuffer offset) {
		glVertexAttribPointer(index, size, dataType, normalize, strength, offset);
		glEnableVertexAttribArray(index);
	}

	public void setAttribute(int index, int size, int dataType, boolean normalize, int strength, long offset) {
		glVertexAttribPointer(index, size, dataType, normalize, strength, offset);
		glEnableVertexAttribArray(index);
	}

	@Override
	public void destroy() {
		this.unbind();
		glDeleteVertexArrays(this.handle);
		this.invalidateHandle();
		
	}
}
