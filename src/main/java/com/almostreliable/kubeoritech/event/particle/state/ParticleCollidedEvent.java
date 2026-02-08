package com.almostreliable.kubeoritech.event.particle.state;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.init.recipes.OritechRecipe;

import org.jetbrains.annotations.Nullable;

public class ParticleCollidedEvent extends ParticleStateEvent {

    private final ServerLevel level;
    private final BlockPos pos;
    private final Vec3 collisionPos;
    private final ItemStack itemA;
    private final ItemStack itemB;
    private final float speed;
    @Nullable
    private final ResourceLocation recipeId;
    @Nullable
    private final OritechRecipe recipe;

    public ParticleCollidedEvent(
        ServerLevel level, BlockPos pos, AcceleratorControllerBlockEntity controller, Vec3 collisionPos, ItemStack itemA,
        ItemStack itemB, float speed, @Nullable ResourceLocation recipeId, @Nullable OritechRecipe recipe
    ) {
        super(controller);
        this.level = level;
        this.pos = pos;
        this.collisionPos = collisionPos;
        this.itemA = itemA;
        this.itemB = itemB;
        this.speed = speed;
        this.recipeId = recipeId;
        this.recipe = recipe;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Vec3 getCollisionPos() {
        return collisionPos;
    }

    public ItemStack getItemA() {
        return itemA;
    }

    public ItemStack getItemB() {
        return itemB;
    }

    public float getSpeed() {
        return speed;
    }

    @Nullable
    public ResourceLocation getRecipeId() {
        return recipeId;
    }

    @Nullable
    public OritechRecipe getRecipe() {
        return recipe;
    }
}
