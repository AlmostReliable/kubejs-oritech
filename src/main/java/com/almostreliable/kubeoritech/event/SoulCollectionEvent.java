package com.almostreliable.kubeoritech.event;

import com.almostreliable.kubeoritech.mixin.SoulCollectionDeathListenerMixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.rhino.util.HideFromJS;
import rearth.oritech.block.entity.arcane.BaseSoulCollectionEntity;

import org.jetbrains.annotations.Nullable;

/**
 * See {@link SoulCollectionDeathListenerMixin}.
 */
public class SoulCollectionEvent implements KubeEvent {

    private final ServerLevel level;
    private final Vec3 position;
    private final BaseSoulCollectionEntity blockEntity;
    @Nullable
    private final LivingEntity entity;
    private int soulValue = 1;

    public SoulCollectionEvent(ServerLevel level, Vec3 position, BaseSoulCollectionEntity blockEntity, @Nullable LivingEntity entity) {
        this.level = level;
        this.position = position;
        this.blockEntity = blockEntity;
        this.entity = entity;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public Vec3 getPosition() {
        return position;
    }

    public BaseSoulCollectionEntity getBlockEntity() {
        return blockEntity;
    }

    @Nullable
    public LivingEntity getEntity() {
        return entity;
    }

    public void setSoulValue(int soulValue) {
        this.soulValue = soulValue;
    }

    @HideFromJS
    public int getSoulValue() {
        return soulValue;
    }
}
