package fuzs.portablehole.data;

import fuzs.portablehole.init.ModRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.builtInBlock(ModRegistry.TEMPORARY_HOLE_BLOCK.get(), Blocks.OBSIDIAN);
    }

    public void builtInBlock(Block block, Block texture) {
        this.simpleBlock(block, this.models().getBuilder(this.name(block))
                .texture("particle", this.blockTexture(texture)));
    }

    public String name(Block block) {
        return Registry.BLOCK.getKey(block).getPath();
    }
}
