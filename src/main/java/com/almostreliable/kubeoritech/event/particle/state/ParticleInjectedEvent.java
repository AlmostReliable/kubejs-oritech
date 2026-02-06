package com.almostreliable.kubeoritech.event.particle.state;

import com.almostreliable.kubeoritech.ModInitializer;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import dev.latvian.mods.kubejs.event.KubeEvent;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.block.entity.accelerator.AcceleratorParticleLogic;

@SuppressWarnings("AssignmentToStaticFieldFromInstanceMethod")
public record ParticleInjectedEvent(
    ServerLevel getLevel, BlockPos getPos, AcceleratorControllerBlockEntity getController, BlockPos getStartPos, BlockPos getGatePos,
    AcceleratorParticleLogic.ActiveParticle getParticle, ItemStack getItem
) implements KubeEvent {

    public void disableNetherPortal() {
        ModInitializer.NETHER_PORTA_ENABLED = false;
    }

    public void disableEndPortal() {
        ModInitializer.END_PORTAL_ENABLED = false;
    }
}
