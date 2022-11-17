/*
 * Copyright (c) 2021 the-sugar-tree
 *
 *  Licensed under the General Public License, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/lgpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sugar_tree.inventoryshare;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.sugar_tree.inventoryshare.api.SharedConstants.*;

public class Commands implements TabExecutor {

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("inventoryshare")) {
            if (sender.isOp()) {
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("inventory")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("true")) {
                                if (!inventory) {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        InventoryClass.savePlayerInventory(p);
                                    }
                                    inventory = true;
                                    Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "인벤토리 공유: " + ChatColor.GREEN + inventory + ChatColor.GOLD + "로 설정되었습니다");
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        InventoryClass.invApply(p);
                                        p.updateInventory();
                                    }
                                } else {
                                    sender.sendMessage(PREFIX + ChatColor.RED + "이미 인벤토리 공유가 활성화 되어 있습니다!");
                                }
                            } else if (args[1].equalsIgnoreCase("false")) {
                                if (inventory) {
                                    inventory = false;
                                    Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "인벤토리 공유: " + ChatColor.GREEN + inventory + ChatColor.GOLD + "로 설정되었습니다");
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        InventoryClass.invDisApply(p);
                                        p.updateInventory();
                                    }
                                } else {
                                    sender.sendMessage(PREFIX + ChatColor.RED + "이미 인벤토리 공유가 비활성화 되어 있습니다!");
                                }
                            } else {
                                sender.sendMessage(usageMessage);
                            }
                        } else {
                            sender.sendMessage(PREFIX + ChatColor.GOLD + "인벤토리 공유: " + ChatColor.GREEN + inventory);
                        }
                    } else if (args[0].equalsIgnoreCase("advancement")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("true")) {
                                advancement = true;
                                Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "발전과제 공유: " + ChatColor.GREEN + advancement + ChatColor.GOLD + "로 설정되었습니다");
                            } else if (args[1].equalsIgnoreCase("false")) {
                                advancement = false;
                                Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "발전과제 공유: " + ChatColor.GREEN + advancement + ChatColor.GOLD + "로 설정되었습니다");
                            } else {
                                sender.sendMessage(usageMessage);
                            }
                        } else {
                            sender.sendMessage(PREFIX + ChatColor.GOLD + "발전과제 공유: " + ChatColor.GREEN + advancement);
                        }
                    } else if (args[0].equalsIgnoreCase("AnnounceDeath")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("true")) {
                                AnnounceDeath = true;
                                Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "사망 시 좌표출력: " + ChatColor.GREEN + AnnounceDeath + ChatColor.GOLD + "로 설정되었습니다");
                            } else if (args[1].equalsIgnoreCase("false")) {
                                AnnounceDeath = false;
                                Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "사망 시 좌표출력: " + ChatColor.GREEN + AnnounceDeath + ChatColor.GOLD + "로 설정되었습니다");
                            } else {
                                sender.sendMessage(usageMessage);
                            }
                        } else {
                            sender.sendMessage(PREFIX + ChatColor.GOLD + "사망 시 좌표출력: " + ChatColor.GREEN + AnnounceDeath);
                        }
                    } else if (args[0].equalsIgnoreCase("teaminventory")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("true")) {
                                if (!teaminventory) {
                                    teaminventory = true;
                                    Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "팀 아이템 공유: " + ChatColor.GREEN + teaminventory + ChatColor.GOLD + "로 설정되었습니다");
                                    if (inventory) {
                                        for (Player p : Bukkit.getOnlinePlayers()) {
                                            InventoryClass.invApply(p);
                                            p.updateInventory();
                                        }
                                    }
                                } else {
                                    sender.sendMessage(PREFIX + ChatColor.RED + "이미 팀 아이템 공유가 활성화 되어 있습니다!");
                                }
                            } else if (args[1].equalsIgnoreCase("false")) {
                                if (teaminventory) {
                                    teaminventory = false;
                                    Command.broadcastCommandMessage(sender, PREFIX + ChatColor.GOLD + "팀 아이템 공유: " + ChatColor.GREEN + teaminventory + ChatColor.GOLD + "로 설정되었습니다");
                                    if (inventory) {
                                        for (Player p : Bukkit.getOnlinePlayers()) {
                                            InventoryClass.invApply(p);
                                            p.updateInventory();
                                        }
                                    }
                                } else {
                                    sender.sendMessage(PREFIX + ChatColor.RED + "이미 팀 아이템 공유가 비활성화 되어 있습니다!");
                                }
                            } else {
                                sender.sendMessage(usageMessage);
                            }
                        } else {
                            sender.sendMessage(PREFIX + ChatColor.GOLD + "팀 아이템 공유: " + ChatColor.GREEN + teaminventory);
                        }
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if (args.length == 1) {
                            if (plugin.getConfig().contains("inventory")) advancement = plugin.getConfig().getBoolean("inventory");
                            if (plugin.getConfig().contains("advancement")) advancement = plugin.getConfig().getBoolean("advancement");
                            if (plugin.getConfig().contains("AnnounceDeath")) AnnounceDeath = plugin.getConfig().getBoolean("AnnounceDeath");
                            if (plugin.getConfig().contains("teaminventory")) AnnounceDeath = plugin.getConfig().getBoolean("teaminventory");
                            sender.sendMessage(PREFIX + ChatColor.GREEN + "Config 파일이 새로고침 되었습니다!");
                        } else {
                            sender.sendMessage(usageMessage);
                        }
                    } else if (args[0].equalsIgnoreCase("check")) {
                        sender.sendMessage(check);
                    } else {
                        sender.sendMessage(usageMessage);
                    }
                } else {
                    sender.sendMessage(usageMessage);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is a mistake.");
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("inventoryshare")) {
            if (args.length == 1) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("inventory");
                arrayList.add("advancement");
                arrayList.add("AnnounceDeath");
                arrayList.add("teaminventory");
                arrayList.add("check");
                arrayList.add("reload");
                arrayList.removeIf(s -> !s.startsWith(args[0]));
                return arrayList;
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("inventory") || args[0].equalsIgnoreCase("advancement")
                        || args[0].equalsIgnoreCase("announcedeath") || args[0].equalsIgnoreCase("teaminventory")) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("true");
                    arrayList.add("false");
                    arrayList.removeIf(s -> !s.startsWith(args[1]));
                    return arrayList;
                } else {
                    return new ArrayList<>();
                }
            } else {
                return new ArrayList<>();
            }
        }
        return null;
    }

    String usageMessage = ChatColor.DARK_AQUA + "-----------------------------------------------------\n" +
            PREFIX + ChatColor.GREEN + "커맨드 도움말\n" +
            ChatColor.LIGHT_PURPLE + "(true나 false를 입력하지 않고 커맨드를 사용하면 현재 설정을 보여줍니다.)\n" +
            ChatColor.AQUA + "/inventoryshare" + ChatColor.GREEN + " inventory " + ChatColor.GOLD + "[true|false]" + ChatColor.YELLOW + " - 현재 인벤토리 공유 설정을 수정합니다.\n" +
            ChatColor.AQUA + "/inventoryshare" + ChatColor.GREEN + " advancement " + ChatColor.GOLD + "[true|false]" + ChatColor.YELLOW + " - 현재 발전과제 공유 설정을 수정합니다.\n" +
            ChatColor.AQUA + "/inventoryshare" + ChatColor.GREEN + " AnnounceDeath " + ChatColor.GOLD + "[true|false]" + ChatColor.YELLOW + " - 현재 사망시 좌표 공유 설정을 수정합니다.\n" +
            ChatColor.AQUA + "/inventoryshare" + ChatColor.GREEN + " teaminventory " + ChatColor.GOLD + "[true|false]" + ChatColor.YELLOW + " - 현재 팀 아이템 공유 설정을 수정합니다.\n" +
            ChatColor.AQUA + "/inventoryshare" + ChatColor.GREEN + " check" + ChatColor.YELLOW + " - 현재 설정값들을 확인합니다.\n" +
            ChatColor.AQUA + "/inventoryshare" + ChatColor.GREEN + " reload" + ChatColor.YELLOW + " - config 파일을 새로고침 합니다.\n" +
            ChatColor.DARK_AQUA + "-----------------------------------------------------"
            ;
    String check = ChatColor.DARK_AQUA + "-----------------------------------------------------\n" +
            PREFIX + ChatColor.GOLD + "inventory: " + ChatColor.GREEN + inventory +
            PREFIX + ChatColor.GOLD + "advancement: " + ChatColor.GREEN + advancement +
            PREFIX + ChatColor.GOLD + "AnnounceDeath: " + ChatColor.GREEN + AnnounceDeath +
            PREFIX + ChatColor.GOLD + "teaminventory: " + ChatColor.GREEN + teaminventory +
            ChatColor.DARK_AQUA + "-----------------------------------------------------"
            ;
}
