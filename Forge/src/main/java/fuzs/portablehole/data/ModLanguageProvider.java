package fuzs.portablehole.data;

import fuzs.portablehole.init.ModRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(ModRegistry.PORTABLE_HOLE_ITEM.get(), "Portable Hole");
        this.add("item.portablehole.portable_hole.description", "Click on a block and see what happens!");
    }
}
