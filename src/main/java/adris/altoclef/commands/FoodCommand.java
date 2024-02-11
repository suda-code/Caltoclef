package adris.altoclef.commands;

import adris.altoclef.AltoClef;
import adris.altoclef.commandsystem.Arg;
import adris.altoclef.commandsystem.ArgParser;
import adris.altoclef.commandsystem.Command;
import adris.altoclef.commandsystem.CommandException;
import adris.altoclef.tasks.resources.CollectFoodTask;

public class FoodCommand extends Command {
    public FoodCommand() throws CommandException {
        super("food", "收集一定数量的食物 (素食)", new Arg(Integer.class, "数量"));
    }

    @Override
    protected void call(AltoClef mod, ArgParser parser) throws CommandException {
        mod.runUserTask(new CollectFoodTask(parser.get(Integer.class)), this::finish);
    }
}