package com.itech4kids.skyblock.Objects.Inventories;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockSkillGuiItem;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.sun.org.apache.bcel.internal.generic.SWAP;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkillLevelsInventory extends CraftInventoryCustom implements Listener {

    public SkillType skillType;
    public SkyblockPlayer skyblockPlayer;

    public SkillLevelsInventory(SkillType skillType, SkyblockPlayer skyblockPlayer) {
        super(null, 54, StringUtils.capitalize(skillType.name().toLowerCase()) + " Skill");
        skyblockPlayer.setInventory(skillType.name().toLowerCase() + " Skill", this);
        this.skillType = skillType;
        this.skyblockPlayer = skyblockPlayer;
        Bukkit.getPluginManager().registerEvents(this, Main.getMain());
        SkyblockSkillGuiItem farming = new SkyblockSkillGuiItem(SkillType.FARMING, skyblockPlayer, "Harvest crops and shear sheep to earn Farming XP!", "Farmhand", null, 294, 0);
        SkyblockSkillGuiItem mining = new SkyblockSkillGuiItem(SkillType.MINING, skyblockPlayer, "Spelunk islands for ores and valuable materials to earn Mining XP!", "Spelunker", null, 274, 0);
        SkyblockSkillGuiItem combat = new SkyblockSkillGuiItem(SkillType.COMBAT, skyblockPlayer, "Fight mobs and players to earn Combat XP!", "Warrior", null, 272, 0);
        SkyblockSkillGuiItem foraging = new SkyblockSkillGuiItem(SkillType.FORAGING, skyblockPlayer, "Cut trees and forage for other plants to earn Foraging XP!", "Logger", null, 6, 3);
        SkyblockSkillGuiItem fishing = new SkyblockSkillGuiItem(SkillType.FISHING, skyblockPlayer, "Visit your local pond to fish and earn Fishing XP!", "Treasure Hunter", null, 346, 0);
        SkyblockSkillGuiItem enchanting = new SkyblockSkillGuiItem(SkillType.ENCHANTING, skyblockPlayer, "Enchant items to earn Enchanting XP!", "Conjourer", null, 116, 0);
        SkyblockSkillGuiItem alchemy = new SkyblockSkillGuiItem(SkillType.ALCHEMY, skyblockPlayer, "Brew potions to earn Alchemy XP!", "Brewer", null, 379, 0);
        SkyblockSkillGuiItem carpentry = new SkyblockSkillGuiItem(SkillType.CARPENTRY, skyblockPlayer, "Craft items to earn Carpentry XP!", "Carpentry", null, 58, 0);
        List<String> runecraftingRewards = new ArrayList<>();
        runecraftingRewards.add(ChatColor.GRAY + "Access to runes with the same level");
        SkyblockSkillGuiItem runecrafting = new SkyblockSkillGuiItem(SkillType.RUNECRAFTING, skyblockPlayer, "Slay bosses, runic mobs & fuse runes to earn Runecrafting XP!", "Runecrafter", runecraftingRewards, 378, 0);
        SkyblockSkillGuiItem social = new SkyblockSkillGuiItem(SkillType.SOCIAL, skyblockPlayer, "Gain Social XP for every new unique guest, hosting guests and visiting islands!", "Social", null, 388, 0);
        SkyblockSkillGuiItem taming = new SkyblockSkillGuiItem(SkillType.TAMING, skyblockPlayer, "Level up pets to gain Taming XP!", "Zoologist", null, 383, 0);
        SkyblockSkillGuiItem dungeoneering = new SkyblockSkillGuiItem(SkillType.CATACOMBS, skyblockPlayer, "Coming Soon!", "Catacombs", null, 397, 3);

        ItemStack goback = new ItemStack(Material.ARROW);
        ItemMeta gobackmeta = goback.getItemMeta();
        gobackmeta.setDisplayName(ChatColor.GREEN + "Go Back");
        goback.setItemMeta(gobackmeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closeMeta);

        ItemStack nextPage = new ItemStack(Material.ARROW);
        ItemMeta nextPageMeta = nextPage.getItemMeta();
        nextPageMeta.setDisplayName(ChatColor.GREEN + "Next Page");
        nextPage.setItemMeta(nextPageMeta);

        ItemStack space1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
        ItemMeta itemMeta = space1.getItemMeta();
        itemMeta.setDisplayName(" ");
        space1.setItemMeta(itemMeta);

        for (int i = 0; i < 54; ++i){
            this.setItem(i, space1);
        }

        this.setItem(48, goback);
        this.setItem(49, close);
        this.setItem(50, nextPage);

        int i = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + 1;

        ItemStack lvlItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData());
        ItemMeta meta = lvlItem.getItemMeta();
        List<String> lore = new ArrayList<>();

        int index = 0;

        switch (skillType){
            case FARMING:
                this.setItem(0, farming);
                createInventory("Farmhand", index, lvlItem, meta, lore, i);
                break;
            case MINING:
                this.setItem(0, mining);
                createInventory("Spelunker", index, lvlItem, meta, lore, i);
                break;
            case COMBAT:
                this.setItem(0, combat);
                createInventory("Warrior", index, lvlItem, meta, lore, i);
                break;
            case FORAGING:
                this.setItem(0, foraging);
                createInventory("Logger", index, lvlItem, meta, lore, i);
                break;
            case FISHING:
                this.setItem(0, fishing);
                createInventory("Treasure Hunter", index, lvlItem, meta, lore, i);
                break;
            case ENCHANTING:
                this.setItem(0, enchanting);
                createInventory("Conjourer", index, lvlItem, meta, lore, i);
                break;
            case ALCHEMY:
                this.setItem(0, alchemy);
                createInventory("Brewer", index, lvlItem, meta, lore, i);
                break;
            case CARPENTRY:
                this.setItem(0, carpentry);
                createInventory("Cosmetic Skill", index, lvlItem, meta, lore, i);
                break;
            case RUNECRAFTING:
                this.setItem(0, runecrafting);
                createInventory("Cosmetic Skill", index, lvlItem, meta, lore, i);
                break;
            case SOCIAL:
                this.setItem(0, social);
                createInventory("Cosmetic Skill", index, lvlItem, meta, lore, i);
                break;
            case TAMING:
                this.setItem(0, taming);
                createInventory("Zoologist", index, lvlItem, meta, lore, i);
                break;
            case CATACOMBS:
                this.setItem(0, dungeoneering);
                createInventory("Catacombs", index, lvlItem, meta, lore, i);
                break;
        }
    }

    public void createInventory(String s, int index, ItemStack lvlItem, ItemMeta meta, List<String> lore, int i){

        for (index = 1; index < 26; ++index){
            if (index >= 1 && index < 4) {
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(1 + 8 + (9 * (index - 1)), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(1 + 8 + (9 * (index - 1)), lvlItem);
                    lore.clear();
                }
            }else if (index == 4){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(28, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(28, lvlItem);
                    lore.clear();
                }
            }else if (index >= 5 && index < 9){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(5 + 24 - ((index - 5) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(5 + 24 - ((index - 5) * 9), lvlItem);
                    lore.clear();
                }
            }else if (index == 9){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(3, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(3, lvlItem);
                    lore.clear();
                }
            }else if (index >= 10 && index < 14){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(10 - 6 + ((index - 10) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(10 - 6 + ((index - 10) * 9), lvlItem);
                    lore.clear();
                }
            }else if (index == 14){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(32, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(32, lvlItem);
                    lore.clear();
                }
            }else if (index >= 15 && index < 19){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(15 + 18 - ((index - 15) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(15 + 18 - ((index - 15) * 9), lvlItem);
                    lore.clear();
                }
            }else if (index == 19){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(7, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(7, lvlItem);
                    lore.clear();
                }
            }else if (index >= 20 && index < 26){
                if (index < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(20 - 12 + ((index - 20) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + index);
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(ChatColor.YELLOW + " " + s + " " + index);
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    this.setItem(20 - 12 + ((index - 20) * 9), lvlItem);
                    lore.clear();
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getClickedInventory().equals(this)){
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Next Page")) {
                SkillsLevelsInventory2 skillsLevelsInventory2 = new SkillsLevelsInventory2(skillType, skyblockPlayer);
                skyblockPlayer.getBukkitPlayer().openInventory(skillsLevelsInventory2);
            }
        }
    }
}
