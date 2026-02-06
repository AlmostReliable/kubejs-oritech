package com.almostreliable.kubeoritech.event.particle.state;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import dev.latvian.mods.kubejs.event.KubeEvent;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.init.recipes.OritechRecipe;

import org.jetbrains.annotations.Nullable;

public record ParticleCollidedEvent(
    ServerLevel getLevel, BlockPos getPosition, AcceleratorControllerBlockEntity getControllerBlockEntity, ItemStack getItemA,
    ItemStack getItemB, float getSpeed, @Nullable ResourceLocation getRecipeId, @Nullable OritechRecipe getRecipe
) implements KubeEvent {}
