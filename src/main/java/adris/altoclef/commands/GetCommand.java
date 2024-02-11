package adris.altoclef.commands;

import adris.altoclef.AltoClef;
import adris.altoclef.TaskCatalogue;
import adris.altoclef.commandsystem.*;
import adris.altoclef.tasksystem.Task;
import adris.altoclef.ui.MessagePriority;
import adris.altoclef.util.ItemTarget;

public class GetCommand extends Command {

    public GetCommand() throws CommandException {
        super("get", "获取物品或资源",
                new Arg(ItemList.class, "物品"));
    }

    private static void OnResourceDoesNotExist(AltoClef mod, String resource) {
        mod.log("\"" + resource + "\" 不在AltoClef的资源库中，如果这是一个方块，请尝试 Baritone", MessagePriority.OPTIONAL);
        mod.log("使用 @list 获取可以用的资源", MessagePriority.OPTIONAL);
    }

    private void GetItems(AltoClef mod, ItemTarget... items) {
        Task targetTask;
        if (items == null || items.length == 0) {
            mod.log("你好歹填一个资源啊");
            finish();
            return;
        }
        if (items.length == 1) {
            targetTask = TaskCatalogue.getItemTask(items[0]);
        } else {
            targetTask = TaskCatalogue.getSquashedItemTask(items);
        }
        if (targetTask != null) {
            mod.runUserTask(targetTask, this::finish);
        } else {
            finish();
        }
    }

    @Override
    protected void call(AltoClef mod, ArgParser parser) throws CommandException {
        ItemList items = parser.get(ItemList.class);
        GetItems(mod, items.items);
    }
}