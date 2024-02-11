package adris.altoclef.commands;

import adris.altoclef.AltoClef;
import adris.altoclef.commandsystem.ArgParser;
import adris.altoclef.commandsystem.Command;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop", "停止所有的运行及任务");
    }

    @Override
    protected void call(AltoClef mod, ArgParser parser) {
        mod.getUserTaskChain().cancel(mod);
        finish();
    }
}
