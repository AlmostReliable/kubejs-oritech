package com.almostreliable.kubeoritech.event.particle.state;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity;
import rearth.oritech.init.recipes.OritechRecipe;

public class ParticleCollidedEvent extends ParticleStateEvent {

    private final ServerLevel level;
    private final BlockPos pos;
    private final Vec3 collisionPos;
    private final ItemStack itemA;
    private final ItemStack itemB;
    private final float speed;
    @Nullable
    private final ResourceKey<Recipe<?>> recipeKey;
    @Nullable
    private final OritechRecipe recipe;

    public ParticleCollidedEvent(
        ServerLevel level, BlockPos pos, ParticleAcceleratorBlockEntity controller, Vec3 collisionPos, ItemStack itemA,
        ItemStack itemB, long speed, @Nullable ResourceKey<Recipe<?>> recipeKey, @Nullable OritechRecipe recipe
    ) {
        super(controller);
        this.level = level;
        this.pos = pos;
        this.collisionPos = collisionPos;
        this.itemA = itemA;
        this.itemB = itemB;
        this.speed = speed;
        this.recipeKey = recipeKey;
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
    public ResourceKey<Recipe<?>> getRecipeKey() {
        return recipeKey;
    }

    @Nullable
    public OritechRecipe getRecipe() {
        return recipe;
    }
}
