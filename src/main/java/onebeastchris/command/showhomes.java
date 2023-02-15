package onebeastchris.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.ServerCommandSource;
import onebeastchris.util.Forms;

import java.util.concurrent.Executors;

import static net.minecraft.server.command.CommandManager.literal;

public class showhomes {
    public static LiteralCommandNode register(CommandDispatcher<ServerCommandSource> dispatcher) {
        return dispatcher.register(
                literal("a").executes(context -> {
                    Executors.newSingleThreadExecutor().execute(() ->
                    {
                        try {
                            Forms.sendForm(context.getSource().getPlayerOrThrow().getUuid());
                        } catch (CommandSyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return 0;
                })
        );
    }
}