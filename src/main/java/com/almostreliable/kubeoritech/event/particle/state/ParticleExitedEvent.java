package com.almostreliable.kubeoritech.event.particle.state;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity.ParticleEvent;

public class ParticleExitedEvent extends ParticleStateEvent {

    private final ServerLevel level;
    private final BlockPos pos;
    private final BlockPos gatePos;
    private final Vec3 fromVec;
    private final Vec3 toVec;
    private final Vec3 directionVec;
    private final ParticleEvent reason;

    public ParticleExitedEvent(
        ServerLevel level, BlockPos pos, AcceleratorControllerBlockEntity controller, BlockPos gatePos, Vec3 fromVec, Vec3 toVec,
        Vec3 directionVec, ParticleEvent reason
    ) {
        super(controller);
        this.level = level;
        this.pos = pos;
        this.gatePos = gatePos;
        this.fromVec = fromVec;
        this.toVec = toVec;
        this.directionVec = directionVec;
        this.reason = reason;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getGatePos() {
        return gatePos;
    }

    public Vec3 getFromVec() {
        return fromVec;
    }

    public Vec3 getToVec() {
        return toVec;
    }

    public Vec3 getDirectionVec() {
        return directionVec;
    }

    public ParticleEvent getReason() {
        return reason;
    }
}
