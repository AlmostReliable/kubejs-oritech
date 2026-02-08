package com.almostreliable.kubeoritech.event.particle.state;

import com.almostreliable.kubeoritech.ModInitializer;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.block.entity.accelerator.AcceleratorParticleLogic.ActiveParticle;

@SuppressWarnings("AssignmentToStaticFieldFromInstanceMethod")
public class ParticleInjectedEvent extends ParticleStateEvent {

    private final ServerLevel level;
    private final BlockPos pos;
    private final BlockPos startPos;
    private final BlockPos gatePos;
    private final ActiveParticle particle;
    private final ItemStack item;

    public ParticleInjectedEvent(
        ServerLevel level, BlockPos pos, AcceleratorControllerBlockEntity controller, BlockPos startPos, BlockPos gatePos,
        ActiveParticle particle, ItemStack item
    ) {
        super(controller);
        this.level = level;
        this.pos = pos;
        this.startPos = startPos;
        this.gatePos = gatePos;
        this.particle = particle;
        this.item = item;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getStartPos() {
        return startPos;
    }

    public BlockPos getGatePos() {
        return gatePos;
    }

    public ActiveParticle getParticle() {
        return particle;
    }

    public ItemStack getItem() {
        return item;
    }

    public void disableNetherPortal() {
        ModInitializer.NETHER_PORTA_ENABLED = false;
    }

    public void disableEndPortal() {
        ModInitializer.END_PORTAL_ENABLED = false;
    }
}
