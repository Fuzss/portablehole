package fuzs.portablehole.init;

import fuzs.portablehole.PortableHole;
import fuzs.portablehole.core.particles.SparkleParticleData;
import fuzs.portablehole.core.particles.SparkleParticleType;
import fuzs.portablehole.world.item.PortableHoleItem;
import fuzs.portablehole.world.level.block.TemporaryHoleBlock;
import fuzs.portablehole.world.level.block.entity.TemporaryHoleBlockEntity;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.core.ModLoader;
import fuzs.puzzleslib.init.RegistryManager;
import fuzs.puzzleslib.init.RegistryReference;
import fuzs.puzzleslib.init.builder.ModBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class ModRegistry {
    private static final RegistryManager REGISTRY = CoreServices.FACTORIES.registration(PortableHole.MOD_ID);
    public static final RegistryReference<Block> TEMPORARY_HOLE_BLOCK = REGISTRY.whenNotOn(ModLoader.FORGE).registerBlock("temporary_hole", () -> new TemporaryHoleBlock(BlockBehaviour.Properties.of(Material.PORTAL, MaterialColor.COLOR_BLACK).noCollission().lightLevel((p_50854_) -> {
        return 15;
    }).strength(-1.0F, 3600000.0F).noDrops()));
    public static final RegistryReference<Item> PORTABLE_HOLE_ITEM = REGISTRY.registerItem("portable_hole", () -> new PortableHoleItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.UNCOMMON)));
    public static final RegistryReference<BlockEntityType<TemporaryHoleBlockEntity>> TEMPORARY_HOLE_BLOCK_ENTITY_TYPE = REGISTRY.registerBlockEntityTypeBuilder("temporary_hole", () -> ModBlockEntityTypeBuilder.of(TemporaryHoleBlockEntity::new, TEMPORARY_HOLE_BLOCK.get()));
    public static final RegistryReference<ParticleType<SparkleParticleData>> SPARK_PARTICLE_TYPE = REGISTRY.register(Registry.PARTICLE_TYPE_REGISTRY, "sparkle", () -> new SparkleParticleType());

    public static final TagKey<Block> PORTABLE_HOLE_IMMUNE_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(PortableHole.MOD_ID, "portable_hole_immune"));

    public static final ResourceLocation STRONGHOLD_CORRIDOR_INJECT_LOOT_TABLE = new ResourceLocation(PortableHole.MOD_ID, "inject/" + BuiltInLootTables.STRONGHOLD_CORRIDOR.getPath());

    public static void touch() {

    }
}
