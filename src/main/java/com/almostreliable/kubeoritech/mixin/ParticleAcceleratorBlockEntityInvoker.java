package com.almostreliable.kubeoritech.mixin;

import net.minecraft.core.BlockPos;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity;

@Mixin(ParticleAcceleratorBlockEntity.class)
public interface ParticleAcceleratorBlockEntityInvoker {

    @Invoker
    void callSpawnEndPortal(BlockPos pos);
    @Invoker
    void callSpawnNetherPortal(BlockPos pos);
}
