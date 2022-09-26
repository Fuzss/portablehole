package fuzs.portablehole.client.core;

import net.minecraft.client.renderer.texture.AbstractTexture;

public interface ClientAbstractions {

    void setFilterSave(AbstractTexture texture, boolean filter, boolean mipmap);

    void restoreLastFilter(AbstractTexture texture);
}
