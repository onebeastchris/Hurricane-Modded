package net.onebeastchris.hurricane.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@ConfigSerializable
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public final class HurricaneConfiguration {
    @Comment("""
            "Fixes" Bedrock players running into lagback issues on certain blocks by removing any collision detection from the given block.
            Caveats: a custom client - Java or Bedrock - could take advantage of no collision and walk right through.
             Additionally, placement of these blocks on both platforms may be buggier than usual.""")
    private CollisionFixes collisionFixes = new CollisionFixes();
    @Comment("""
            Fixes Bedrock players being unable to control pigs and striders by controlling their movement serverside.
            Java Edition controls pigs and striders on the client end. Bedrock depends on the server.
            This option should be relatively safe but does modify server behavior. Geyser or Floodgate must be installed.""")
    private boolean itemSteerableFix = true;

    @Comment("""
            Suppresses "Mismatch in destroy block pos" warnings so they don't spam each time e.g. grass is broken.\s
            This is set to false by default, as there can be valid reasons to have the warnings.\s
            """)
    private boolean suppressWarnings = false;

    @SuppressWarnings("ClassEscapesDefinedScope")
    public CollisionFixes collisionFixes() {
        return collisionFixes;
    }

    public boolean itemSteerableFix() {
        return itemSteerableFix;
    }

    public boolean suppressWarnings() {
        return suppressWarnings;
    }

    @ConfigSerializable
    static final class CollisionFixes {
        private boolean bamboo = true;
        private boolean pointedDripstone = true;
        public boolean bamboo() {
            return bamboo;
        }
        public boolean pointedDripstone() {
            return pointedDripstone;
        }
    }
}