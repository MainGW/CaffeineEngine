package com.easy.caffeineengine.gl;

public abstract class GLObject {
	
	protected final int INVALID_HANDLE_VALUE = Integer.MIN_VALUE;

	protected int handle = INVALID_HANDLE_VALUE;
	
	protected GLObject() {
	}

	public void setHandle(int handle) {
		this.handle = handle;
	}
	
	public int getHandle() {
		if(!checkHandle()) throw new IllegalStateException("OpenGL Object has invalid handle!");
		return handle;
	}

	public boolean checkHandle() {
		return handle != INVALID_HANDLE_VALUE;
	}
	
	public void invalidateHandle() {
		this.handle = INVALID_HANDLE_VALUE;
	}
	
	public abstract void destroy();

}
