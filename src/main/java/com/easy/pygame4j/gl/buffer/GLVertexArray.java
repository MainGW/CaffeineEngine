package com.easy.pygame4j.gl.buffer;

import com.easy.pygame4j.gl.GLObject;
import static org.lwjgl.opengl.GL41C.*;

import java.nio.ByteBuffer;

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
	public void destory() {
		this.unbind();
		glDeleteVertexArrays(this.handle);
		this.invalidateHandle();
		
	}
}
