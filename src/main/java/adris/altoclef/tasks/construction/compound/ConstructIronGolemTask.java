package adris.altoclef.tasks.construction.compound;

import adris.altoclef.AltoClef;
import adris.altoclef.tasks.construction.DestroyBlockTask;
import adris.altoclef.tasks.construction.PlaceBlockTask;
import adris.altoclef.tasks.squashed.CataloguedResourceTask;
import adris.altoclef.tasksystem.Task;
import adris.altoclef.util.ItemTarget;
import adris.altoclef.util.helpers.StorageHelper;
import adris.altoclef.util.helpers.WorldHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class ConstructIronGolemTask extends Task {
    private BlockPos _position;
    private boolean _canBeFinished = false;

    public ConstructIronGolemTask() {

    }

    public ConstructIronGolemTask(BlockPos pos) {
        _position = pos;
    }

    @Override
    protected void onStart(AltoClef mod) {
        mod.getBehaviour().push();
        mod.getBehaviour().addProtectedItems(Items.IRON_BLOCK, Items.CARVED_PUMPKIN);
        mod.getClientBaritoneSettings().blocksToAvoidBreaking.value.add(Blocks.IRON_BLOCK);
    }

    @Override
    protected Task onTick(AltoClef mod) {
        if (!StorageHelper.itemTargetsMetInventory(mod, golemMaterials(mod))) {
            setDebugState("获取铁傀儡的材料");
            return new CataloguedResourceTask(golemMaterials(mod));
        }
        if (_position == null) {
            for (BlockPos pos : WorldHelper.scanRegion(mod,
                    new BlockPos(mod.getPlayer().getBlockX(), 64, mod.getPlayer().getBlockZ()),
                    new BlockPos(mod.getPlayer().getBlockX(), 128, mod.getPlayer().getBlockZ()))) {
                if (mod.getWorld().getBlockState(pos).getBlock() == Blocks.AIR) {
                    _position = pos;
                    break;
                }
            }
            if (_position == null) {
                _position = mod.getPlayer().getBlockPos();
            }
        }
        if (!WorldHelper.isBlock(mod, _position, Blocks.IRON_BLOCK)) {
            if (!WorldHelper.isBlock(mod, _position, Blocks.AIR)) {
                setDebugState("以基础铁块的方式摧毁方块");
                return new DestroyBlockTask(_position);
            }
            setDebugState("放置基础铁块");
            return new PlaceBlockTask(_position, Blocks.IRON_BLOCK);
        }
//        mod.getPlayer().getServer().getPlayerManager().getPlayer("camelCasedSnivy").getAdvancementTracker()
        if (!WorldHelper.isBlock(mod, _position.up(), Blocks.IRON_BLOCK)) {
            if (!WorldHelper.isBlock(mod, _position.up(), Blocks.AIR)) {
                setDebugState("摧毁中心铁块路径上的块");
                return new DestroyBlockTask(_position.up());
            }
            setDebugState("放置中心铁块");
            return new PlaceBlockTask(_position.up(), Blocks.IRON_BLOCK);
        }
        if (!WorldHelper.isBlock(mod, _position.up().east(), Blocks.IRON_BLOCK)) {
            if (!WorldHelper.isBlock(mod, _position.up().east(), Blocks.AIR)) {
                setDebugState("摧毁东铁块路上的块");
                return new DestroyBlockTask(_position.up().east());
            }
            setDebugState("放置东铁块");
            return new PlaceBlockTask(_position.up().east(), Blocks.IRON_BLOCK);
        }
        if (!WorldHelper.isBlock(mod, _position.up().west(), Blocks.IRON_BLOCK)) {
            if (!WorldHelper.isBlock(mod, _position.up().west(), Blocks.AIR)) {
                setDebugState("摧毁西铁块路上的块");
                return new DestroyBlockTask(_position.up().west());
            }
            setDebugState("放置西铁块");
            return new PlaceBlockTask(_position.up().west(), Blocks.IRON_BLOCK);
        }
        if (!WorldHelper.isBlock(mod, _position.east(), Blocks.AIR)) {
            setDebugState("东侧清理区域...");
            return new DestroyBlockTask(_position.east());
        }
        if (!WorldHelper.isBlock(mod, _position.west(), Blocks.AIR)) {
            setDebugState("西侧清理区域...");
            return new DestroyBlockTask(_position.west());
        }
        if (!WorldHelper.isBlock(mod, _position.up(2), Blocks.AIR)) {
            setDebugState("以南瓜的方式摧毁方块");
            return new DestroyBlockTask(_position.up(2));
        }
        _canBeFinished = true;
        setDebugState("放置南瓜（我认为）");
        return new PlaceBlockTask(_position.up(2), Blocks.CARVED_PUMPKIN);
    }

    @Override
    protected void onStop(AltoClef mod, Task interruptTask) {
        mod.getClientBaritoneSettings().blocksToAvoidBreaking.value.remove(Blocks.IRON_BLOCK);
        mod.getBehaviour().pop();
    }

    @Override
    protected boolean isEqual(Task other) {
        return other instanceof ConstructIronGolemTask;
    }

    @Override
    public boolean isFinished(AltoClef mod) {
        if (_position == null) return false;
        Optional<Entity> closestIronGolem = mod.getEntityTracker().getClosestEntity(new Vec3d(_position.getX(), _position.getY(), _position.getZ()), IronGolemEntity.class);
        return closestIronGolem.isPresent() && closestIronGolem.get().getBlockPos().isWithinDistance(_position, 2) && _canBeFinished;
    }

    @Override
    protected String toDebugString() {
        return "建造铁傀儡";
    }

    private int ironBlocksNeeded(AltoClef mod) {
        if (_position == null) {
            return 4;
        }
        int needed = 0;
        if (mod.getWorld().getBlockState(_position).getBlock() != Blocks.IRON_BLOCK)
            needed++;
        if (mod.getWorld().getBlockState(_position.up().west()).getBlock() != Blocks.IRON_BLOCK)
            needed++;
        if (mod.getWorld().getBlockState(_position.up().east()).getBlock() != Blocks.IRON_BLOCK)
            needed++;
        if (mod.getWorld().getBlockState(_position.up()).getBlock() != Blocks.IRON_BLOCK)
            needed++;
        return needed;
    }

    private ItemTarget[] golemMaterials(AltoClef mod) {
        if (_position == null || mod.getWorld().getBlockState(_position.up(2)).getBlock() != Blocks.CARVED_PUMPKIN) return new ItemTarget[]{
                new ItemTarget(Items.IRON_BLOCK, ironBlocksNeeded(mod)),
                new ItemTarget(Items.CARVED_PUMPKIN, 1)
        }; else return new ItemTarget[]{
                new ItemTarget(Items.IRON_BLOCK, ironBlocksNeeded(mod))
        };
    }
}
