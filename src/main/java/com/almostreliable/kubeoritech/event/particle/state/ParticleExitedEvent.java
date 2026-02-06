package com.almostreliable.kubeoritech.event.particle.state;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import dev.latvian.mods.kubejs.event.KubeEvent;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity.ParticleEvent;

public record ParticleExitedEvent(
    ServerLevel getLevel, BlockPos getPos, AcceleratorControllerBlockEntity getController, BlockPos getGatePos,
    Vec3 getFromVec, Vec3 getToVec, Vec3 getDirectionVec, ParticleEvent getReason
) implements KubeEvent {}
