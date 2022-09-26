package fuzs.portablehole.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import fuzs.portablehole.init.ModRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> subProviders = ImmutableList.of(Pair.of(ModChestLoot::new, LootContextParamSets.CHEST));
    private final String modId;

    public ModLootTableProvider(DataGenerator p_124437_, String modId) {
        super(p_124437_);
        this.modId = modId;
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.subProviders;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {

    }

    private static class ModChestLoot extends ChestLoot {

        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
            builder.accept(ModRegistry.STRONGHOLD_CORRIDOR_INJECT_LOOT_TABLE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModRegistry.PORTABLE_HOLE_ITEM.get()).setWeight(1)).add(EmptyLootItem.emptyItem().setWeight(4))));
        }
    }
}
