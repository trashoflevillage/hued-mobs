package io.github.trashoflevillage.huedmobs.access;

import org.joml.Vector3f;

public interface LivingEntityMixinAccess {
    Vector3f getColorShift();
    void setColorShift(Vector3f val);
}
