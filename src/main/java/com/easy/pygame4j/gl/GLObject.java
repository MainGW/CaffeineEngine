package com.easy.pygame4j.gl;

/**
 * The root class of the OpenGL Objects.	 
 * 
 */
public abstract class GLObject {
	
	protected final int INVALID_HANDLE_VALUE = Integer.MIN_VALUE;

	protected int handle = INVALID_HANDLE_VALUE;
	
	protected GLObject() {
		
	}
	
	/**
	 * 
	 * @param handle 
	 */
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
	
	public abstract void destory();

	@Override	
	public void finalize() {
		this.destory();
	}
}
