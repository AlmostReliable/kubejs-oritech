package com.almostreliable.kubeoritech.event.particle.state;

import com.almostreliable.kubeoritech.mixin.ParticleAcceleratorBlockEntityInvoker;

import net.minecraft.core.BlockPos;

import dev.latvian.mods.kubejs.event.KubeEvent;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity;

public abstract class ParticleStateEvent implements KubeEvent {

    private final ParticleAcceleratorBlockEntity controller;

    ParticleStateEvent(ParticleAcceleratorBlockEntity controller) {
        this.controller = controller;
    }

    public void spawnEndPortal(BlockPos pos) {
        ((ParticleAcceleratorBlockEntityInvoker) controller).callSpawnEndPortal(pos);
    }

    public void spawnNetherPortal(BlockPos pos) {
        ((ParticleAcceleratorBlockEntityInvoker) controller).callSpawnNetherPortal(pos);
    }

    public ParticleAcceleratorBlockEntity getController() {
        return controller;
    }
}
