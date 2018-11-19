package com.mycompany.a3;

public interface ICollider {
	public boolean collisionWith(ICollider obj);
	public void handleCollision(ICollider obj);
}
