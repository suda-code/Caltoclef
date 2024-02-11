package adris.altoclef.commands;

import adris.altoclef.AltoClef;
import adris.altoclef.TaskCatalogue;
import adris.altoclef.commandsystem.ArgParser;
import adris.altoclef.commandsystem.Command;
import adris.altoclef.commandsystem.CommandException;
import adris.altoclef.ui.MessagePriority;

import java.util.Arrays;

public class ListCommand extends Command {
    public ListCommand() {
        super("list", "列出所有可获得的项目");
    }

    @Override
    protected void call(AltoClef mod, ArgParser parser) throws CommandException {
        mod.log("######### 所有可获得的物品 #########", MessagePriority.OPTIONAL);
        mod.log(Arrays.toString(TaskCatalogue.resourceNames().toArray()), MessagePriority.OPTIONAL);
        mod.log("############# 结束 ###############", MessagePriority.OPTIONAL);
    }
}
