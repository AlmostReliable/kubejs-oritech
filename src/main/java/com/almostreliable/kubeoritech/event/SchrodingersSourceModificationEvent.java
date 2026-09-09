package com.almostreliable.kubeoritech.event;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.script.data.VirtualDataMapFile;
import dev.latvian.mods.rhino.util.HideFromJS;
import rearth.oritech.init.datamap.SchrodingersSafeSource;

public class SchrodingersSourceModificationEvent implements KubeEvent {

    private final VirtualDataMapFile<Block, SchrodingersSafeSource> dataMap;

    @HideFromJS
    public SchrodingersSourceModificationEvent(VirtualDataMapFile<Block, SchrodingersSafeSource> dataMap) {
        this.dataMap = dataMap;
    }

    public void add(Block block, float quality) {
        var source = new SchrodingersSafeSource(quality);
        dataMap.add(block, source);
    }

    public void addTag(TagKey<Block> tag, float quality) {
        var source = new SchrodingersSafeSource(quality);
        dataMap.addTag(tag, source);
    }

    public void remove(Block block) {
        dataMap.remove(block);
    }

    public void removeTag(TagKey<Block> tag) {
        dataMap.removeTag(tag);
    }

    public void clear() {
        dataMap.clear();
    }
}
