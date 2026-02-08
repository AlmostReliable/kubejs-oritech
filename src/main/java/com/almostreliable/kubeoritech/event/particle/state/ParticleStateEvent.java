package com.almostreliable.kubeoritech.event.particle.state;

import com.almostreliable.kubeoritech.mixin.AcceleratorControllerBlockEntityInvoker;

import net.minecraft.core.BlockPos;

import dev.latvian.mods.kubejs.event.KubeEvent;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;

public abstract class ParticleStateEvent implements KubeEvent {

    private final AcceleratorControllerBlockEntity controller;

    ParticleStateEvent(AcceleratorControllerBlockEntity controller) {
        this.controller = controller;
    }

    public void spawnEndPortal(BlockPos pos) {
        ((AcceleratorControllerBlockEntityInvoker) controller).callSpawnEndPortal(pos);
    }

    public void spawnNetherPortal(BlockPos pos) {
        ((AcceleratorControllerBlockEntityInvoker) controller).callSpawnNetherPortal(pos);
    }

    public AcceleratorControllerBlockEntity getController() {
        return controller;
    }
}
