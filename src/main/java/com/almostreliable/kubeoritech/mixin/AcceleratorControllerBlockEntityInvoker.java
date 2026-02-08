package com.almostreliable.kubeoritech.mixin;

import net.minecraft.core.BlockPos;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;

@Mixin(AcceleratorControllerBlockEntity.class)
public interface AcceleratorControllerBlockEntityInvoker {

    @Invoker
    void callSpawnEndPortal(BlockPos pos);
    @Invoker
    void callSpawnNetherPortal(BlockPos pos);
}
