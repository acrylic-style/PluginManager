
# PluginManager [![Build Status](https://ci.rht0910.tk/job/PluginManager/badge/icon)](https://ci.rht0910.tk/job/PluginManager/) 

Development Builds: [![Build Status](https://ci.rht0910.tk/job/PluginManager-dev/badge/icon)](https://ci.rht0910.tk/job/PluginManager-dev/)

TravisCI: [![Build Status](https://travis-ci.org/rht0910/PluginManager.svg?branch=master)](https://travis-ci.org/rht0910/PluginManager)

Join our Discord Server! [PluginManager](https://discord.gg/hkkF8nF)

Bukkit Project page: https://dev.bukkit.org/projects/pluginmanagement

## Before you use the plugin(Required Plugins)
| Plugin | Download |
| ------ | -------- |
| [TomeitoLib](https://github.com/rht0910/TomeitoLib/) | [here](https://dl.rht0910.tk/TomeitoLib.jar) |
| Permission Managements | Search yourself(Almost servers are using [LuckPerms](https://github.com/lucko/LuckPerms/).) |

## Unsupported Plugins
| Plugin | When | Why not supported | Technical Information |
| ------ | ---- | --------------- | -------------- |
| [BKCommonLib](https://github.com/bergerhealer/BKCommonLib) | Enabling | java.lang.RuntimeException: BKCommonLib is not enabled - Plugin Instance can not be obtained! (disjointed Class state?) | |
| [PluginManager](https://github.com/rht0910/PluginManager/) | Reloading | Cannot reload itself, but if you executed it, Plugin will die | Plugin is already initialized |
| [Still have](https://github.com/rht0910/PluginManager/issues/) | [ a ](https://github.com/rht0910/PluginManager/issues/) | [Problems?](https://github.com/rht0910/PluginManager/issues/) | |

## Commands
| Command | Description |
| ---------------------- | --------------------------------- |
| /pman help | Shows help. |
| /pman load <Plugin> | Load a plugin |
| /pman unload(or /pman disable) <Plugin> | Unload plugin |
| /pman reload <Plugin> | Reload plugin |
| /pman download <FileName> <URL> | Download plugin from url |
| /pman delete <PluginFileName> <Backup file name> | Move a plugin into /plugins/plugins_backup/ |
| /pman restore <FileName> | Restore a plugin |
| /pman editor <Dir> <File> <Line(From 0)> <Value(ex."test:lol"(Not supported white space))> | Edit a plugin config |
| /pman viewer <Dir> <File> <Options> | View a plugin config. "Options" is please write "l:0"(not "1") It caused by bug |
| /pman update | Update this plugin |
| /pman update-dev | Update this plugin to development build |
| /pman usage <Command> | Usage of command |
| /pman permission <ID> <Permission Node> | Test if player has a permission |
| /pman config language <en_US, ja_JP, ...> | Change language |
| /pman config reload | Reload config |
| /pman check | Check version manually |
| /pman check-dev | Check version manually for development build |

## Versions

<span style="color:orange">Latest: [1.4.3](https://github.com/rht0910/PluginManager/releases/tag/1.4.3)</span>

<span style="color:green">Recommended: [1.4.2.4](https://github.com/rht0910/PluginManager/releases/tag/1.4.2.4)</span>

| Version | Status |
| ----- | ----- |
| [\[dev\]](https://github.com/rht0910/PluginManager/tree/dev) | ![#ffa500](https://placehold.it/15/ffa500/000000?text=+) Unstable, may be including bugs! |
| [1.4.3](https://github.com/rht0910/PluginManager/releases/tag/1.4.3) | ![#ffa500](https://placehold.it/15/ffa500/000000?text=+) Latest ![](https://placehold.it/15/00ff00/000000?text=+) Recommended|
| [1.4.2.4](https://github.com/rht0910/PluginManager/releases/tag/1.4.2.4) | ![](https://placehold.it/15/00ff00/000000?text=+) Supported |
| 1.4.2.3 | ![](https://placehold.it/15/00ff00/000000?text=+) Supported |
| [1.4.2.2](https://github.com/rht0910/PluginManager/releases/tag/1.4.2.2) | ![](https://placehold.it/15/ff0000/000000?text=+) Not supported |
| 1.4.2.1 | ![](https://placehold.it/15/ff0000/000000?text=+) Not Supported |
| 1.4.2 | ![](https://placehold.it/15/ff0000/000000?text=+) Including bugs, Don't use it |
| [1.4.1](https://github.com/rht0910/PluginManager/releases/tag/1.4.1) | ![](https://placehold.it/15/ff0000/000000?text=+) Not supported |
| 1.4 | ![](https://placehold.it/15/ff0000/000000?text=+) Not supported |
| Or older | ![](https://placehold.it/15/ff0000/000000?text=+) ![](https://placehold.it/15/ff0000/000000?text=+) ![](https://placehold.it/15/ff0000/000000?text=+) Extremely old version |
