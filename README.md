# Alto Clef

Plays block game.

动力来源 Baritone.

客户端机器人可以完成任何相对简单且可以拆分为较小任务的 Minecraft 任务。 “相对简单”是一个模糊的术语，因此请检查当前功能列表以查看示例。

在 2021 年 5 月 24 日的视频：[第一个完全自主地击败 Minecraft 的机器人](https://youtu.be/baAa6s8tahA).

**加入原作者的 [Discord Server](https://discord.gg/JdFP4Kqdqc)** 来 反馈/反馈更新/发 疯

## 这是怎么工作的？

看看这个 [维基百科指南](https://github.com/gaucho-matrero/altoclef/wiki/1:-Documentation:-Big-Picture) 或这个 [视频解释](https://youtu.be/q5OmcinQ2ck?t=387)

## 当前能力和示例：
- 从新鲜的生存世界中获得 400 多种物品，如钻石盔甲、蛋糕和下界砖楼梯
- 躲避怪物的攻击并杀死怪物，同时完成任意任务
- 收集各种食物
- 通过 /msg 接收来自聊天私信的命令。白名单+黑名单可配置（以下简称管家系统）。这是一个 [Butler 系统演示视频](https://drive.google.com/file/d/1axVYYMJ5VjmVHaWlCifFHTwiXlFssOUc/view?usp=sharing)
- 可以通过命令重新加载的简单配置文件（检查 .minecraft 目录）
- 自行通关整个游戏（无需用户输入）
- 打印整个蜜蜂电影脚本，并沿直线显示标志，沿途自动收集标志+桥接材料。
- 成为终结者：徒手逃离玩家，秘密收集钻石装备，然后返回并造成严重破坏。

## Download

**注意:** 记得把你原来男中音的配置删了！要不然会引起各种各样的问题（原作者说以后会修）

### 替代版本（推荐）（非官方）

如果您正在寻找 1.19.2 - 1.19.4 支持，请查看此[要点](https://gist.github.com/JustaSqu1d/171df3ff386859da31d37534122d3b10)。请注意，这些项目是该原始项目的分支，与 Alto Clef 没有直接关系。它还具有更新的错误修复和功能。

#### （旧）每晚发布

首先下载[最新的长期版本](https://github.com/gaucho-matrero/altoclef/releases)，然后[下载 Nightly](https://nightly.link/gaucho-matrero/altoclef/workflows)/gradle/main/Artifacts.zip 并替换 `altoclef-4.0-SNAPSHOT.jar`。

如果夜间链接不起作用，请检查最新的 [构建操作](https://github.com/gradle/main/Artifacts.zip) 并替换 `altoclef-4.0-SNAPSHOT.jar`。 com/gaucho-matrero/altoclef/actions）成功并下载“Artifacts.zip”（您必须登录 GitHub）。将现有的 `altoclef-4.0-SNAPSHOT.jar` 替换为 `Artifacts.zip` 中的

然后，将 `altoclef-4.0-SNAPSHOT.jar` 从 `Artifacts.zip` 复制到 `./ mods`.

然后，将长期发行版 zip 文件中的 `baritone-unoptimized-fabric-1.XX.X.jar` 复制到 `./mods`

#### （旧）长期版本

[检查版本](https://github.com/gaucho-matrero/altoclef/releases)。请注意，您需要复制两个 jar 文件才能使 mod 正常工作。

#### (old) Meloweh 的额外功能发布（非官方）

具有一些原理图支持、命令宏和一些实用功能。最终将被合并，但如果您愿意，现在可以尝试一下：
```text
此版本将在Caltoclef中合并
```
- [AltoClef jar](https://github.com/Meloweh/altoclef/releases)
- [Baritone jar](https://github.com/Meloweh/baritone/releases)

### 版本

这是一个只有 **Fabric** 的mod，目前仅适用于 **Minecraft 1.18.2**。

对于较旧的 MC 版本，请尝试 [multiconnect](https://www.curseforge.com/minecraft/mc-mods/multiconnect) 注意：multiconnect 未经测试且不隶属于 altoclef，使用风险自负！）

## [Usage Guide](usage.md)

## [TODO's/Future Features](todos.md)

## [Development Guide](develop.md)
