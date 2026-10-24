package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;
import com.sheep_flock.Behaviours.Behaviour;
import com.sheep_flock.geometry.*;
import com.sheep_flock.ui.Drawer;

public abstract class Entity {

    protected Shape shape;
    protected Set<Behaviour> behaviours;
    Behaviour currentBehaviour;
    Action currentAction;

    protected float absoluteX;
    protected float absoluteY;
    protected float currentSpeed;
    protected float currentAngle; // The facing of our entity. 0 is the north, pi/2 the east.
    protected float maxSpeed;
    protected float maxAcceleration;
    protected float maxDecceleration;
    protected float visionField;
    protected float maxAngularSpeed;

    // We consider that the heading of an individual is given by its direction of
    // motion
    // ie by the direction of the speed vector

    public Entity(float absoluteX, float absoluteY) {
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
        this.shape = generateMyShape();
        this.shape.updateAbsolutePosition(absoluteX, absoluteY);
        this.behaviours = new HashSet<Behaviour>();
    }

    public void setPosition(float absoluteX, float absoluteY) {
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
        this.shape.updateAbsolutePosition(absoluteX, absoluteY);
    }

    public Shape shape() {
        return shape;
    }

    public float getAbsoluteX() {
        return absoluteX;
    }

    public float getAbsoluteY() {
        return absoluteY;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        this.shape.updateAbsolutePosition(absoluteX, absoluteY);
    }

    public void choose_action() {
        float maxIntensity = Float.NEGATIVE_INFINITY;
        for (Behaviour behaviour : behaviours) {
            Action behaviourAction = behaviour.compute(this);
            if (behaviourAction.intensity() > maxIntensity) {
                currentBehaviour = behaviour;
                currentAction = behaviourAction;
                maxIntensity = behaviourAction.intensity();
            }
        }
    }

    public void act(float deltaT) {

        // 1. Extracting the current action move
        float desiredAngle = currentAction.desiredAngle();
        float desiredSpeed = currentAction.desiredSpeed();

        // 2. Adaptating the change of direction respecting the maxAngularShift
        // the more speed, the less possible rotation
        // To avoid infinite value, the value is stabilized at maxAngularSpeed at low speeds

        float maxTurnThisFrame = currentSpeed > 1f
                ? maxAngularSpeed / currentSpeed * deltaT
                : maxAngularSpeed * deltaT;
        float angleDiff = MathUtils.getShortestAngleDiff(this.currentAngle, desiredAngle);

        float actualTurn = MathUtils.clamp(angleDiff, -maxTurnThisFrame, maxTurnThisFrame);
        this.currentAngle = MathUtils.normalizeAngle(this.currentAngle + actualTurn);

        // 4. Adaptating the desired speed to respect the lax speed and acceleration
        // capacities of the entity
        float speedDiff = desiredSpeed - this.currentSpeed;

        if (speedDiff > 0) {
            this.currentSpeed += Math.min(speedDiff, maxAcceleration * deltaT);
        } else if (speedDiff < 0) {
            this.currentSpeed -= Math.min(-speedDiff, maxDecceleration * deltaT);
        }

        this.currentSpeed = MathUtils.clamp(this.currentSpeed, 0, maxSpeed);

        // 4. Reducing the speed by a cosinus relation if we can't face the desired angle
        // If we face the desired direction, we keep the computed speed
        // Else, we decrease the speed following a cosinus relation
        float remainingAngleDiff = MathUtils.getShortestAngleDiff(this.currentAngle, desiredAngle);

        float speedMultiplier = Math.max(0, (float) Math.cos(remainingAngleDiff));
        currentSpeed *= speedMultiplier;

        // 5. Applying new computed speed and angle to change the position
        float speedX = (float) (this.currentSpeed * Math.sin(this.currentAngle));
        float speedY = (float) (-this.currentSpeed * Math.cos(this.currentAngle));
        this.setPosition(absoluteX + speedX * deltaT, absoluteY + speedY * deltaT);
    }

    abstract Shape generateMyShape();

    abstract void draw(Drawer drawer);

}