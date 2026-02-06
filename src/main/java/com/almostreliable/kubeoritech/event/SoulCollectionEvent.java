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
    private final Vec3 pos;
    private final BaseSoulCollectionEntity soulCollector;
    @Nullable
    private final LivingEntity entity;
    private int soulValue = 1;

    public SoulCollectionEvent(ServerLevel level, Vec3 pos, BaseSoulCollectionEntity soulCollector, @Nullable LivingEntity entity) {
        this.level = level;
        this.pos = pos;
        this.soulCollector = soulCollector;
        this.entity = entity;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public Vec3 getPos() {
        return pos;
    }

    public BaseSoulCollectionEntity getSoulCollector() {
        return soulCollector;
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
