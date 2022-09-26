package fuzs.portablehole.data;

import fuzs.portablehole.init.ModRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends TagsProvider<Block> {

    public ModBlockTagsProvider(DataGenerator dataGenerator, String modId, ExistingFileHelper fileHelperIn) {
        super(dataGenerator, Registry.BLOCK, modId, fileHelperIn);
    }

    @Override
    protected void addTags() {
        this.tag(ModRegistry.PORTABLE_HOLE_IMMUNE_TAG);
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
