package adris.altoclef.commands;

import adris.altoclef.AltoClef;
import adris.altoclef.commandsystem.*;
import adris.altoclef.tasks.container.StoreInStashTask;
import adris.altoclef.util.BlockRange;
import adris.altoclef.util.ItemTarget;
import adris.altoclef.util.helpers.WorldHelper;
import net.minecraft.util.math.BlockPos;

public class StashCommand extends Command {
    public StashCommand() throws CommandException {
        // stash <stash_x> <stash_y> <stash_z> <stash_radius> [item list]
        super("stash", "将物品存放在箱子/容器中。如果物品清单为空，将存放所有未配备的物品。",
                new Arg(Integer.class, "x_start"),
                new Arg(Integer.class, "y_start"),
                new Arg(Integer.class, "z_start"),
                new Arg(Integer.class, "x_end"),
                new Arg(Integer.class, "y_end"),
                new Arg(Integer.class, "z_end"),
                new Arg(ItemList.class, "items (empty for ALL)", null, 6, false));
    }

    @Override
    protected void call(AltoClef mod, ArgParser parser) throws CommandException {
        BlockPos start = new BlockPos(
                parser.get(Integer.class),
                parser.get(Integer.class),
                parser.get(Integer.class)
        );
        BlockPos end = new BlockPos(
                parser.get(Integer.class),
                parser.get(Integer.class),
                parser.get(Integer.class)
        );

        ItemList itemList = parser.get(ItemList.class);
        ItemTarget[] items;
        if (itemList == null) {
            items = DepositCommand.getAllNonEquippedOrToolItemsAsTarget(mod);
        } else {
            items = itemList.items;
        }


        mod.runUserTask(new StoreInStashTask(true, new BlockRange(start, end, WorldHelper.getCurrentDimension()), items), this::finish);
    }
}
