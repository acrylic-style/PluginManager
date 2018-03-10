
# PluginManager

Jenkins (Release Build): [![Build Status](https://ci.rht0910.tk/job/PluginManager/badge/icon)](https://ci.rht0910.tk/job/PluginManager/)

Jenkins (Development Build): [![Build Status](https://ci.rht0910.tk/job/PluginManager-dev/badge/icon)](https://ci.rht0910.tk/job/PluginManager-dev/)

TravisCI: [![Build Status](https://travis-ci.org/rht0910/PluginManager.svg?branch=master)](https://travis-ci.org/rht0910/PluginManager)

PluginManager is Bukkit plugin.

Project page: https://dev.bukkit.org/projects/pluginmanagement



## Commands
| Command | Description |
| ---------------------- | --------------------------------- |
| /pman help | Shows help. |
| /pman load <Plugin> | Load a plugin. |
| /pman unload(or /pman disable) <Plugin> | Unload plugin. |
| /pman reload <Plugin> | Reload plugin. |
| /pman download <FileName> <URL> | Download plugin from url. |
| /pman delete <PluginFileName> <Backup file name> | Move a plugin into /plugins/plugins_backup/ |
| /pman restore <FileName> | Restore a plugin. |
| /pman editor <Dir> <File> <Line(From 0)> <Value(ex."test:lol"(Not supported white space))> | Edit a plugin config |
| /pman viewer <Dir> <File> <Options> | View a plugin config. "Options" is please write "l:0"(not "1") It caused by bug. |
| /pman update | Update this plugin. |
| /pman update-dev | Update this plugin to development build. |
| /pman usage <Command> | Usage of command. |
| /pman config language <en_US, ja_JP, ...> | Change language. |
| /pman config reload | Reload config |
| /pman check | Check version manually. |
| /pman check-dev | Check version manually for development build. |
