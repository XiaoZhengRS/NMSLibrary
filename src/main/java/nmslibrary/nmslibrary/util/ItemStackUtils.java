package nmslibrary.nmslibrary.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class ItemStackUtils {

    // Prevent accidental construction

    private ItemStackUtils() {
    }

    public static boolean hasEnchantment(ItemStack itemStack) {
        return itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants();
    }

    public static boolean hasEnchantment(ItemStack itemStack, Enchantment enchantment) {
        return itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchant(enchantment);
    }

    /**
     * 附魔序列化
     *
     * @param set 附魔集合
     * @return 获得附魔序列化
     */
    @SuppressWarnings("deprecation")
    public static String getEnch(Set<Entry<Enchantment, Integer>> set) {
        StringBuilder enchs = new StringBuilder();
        for (Entry<Enchantment, Integer> ench : set) {

            enchs.append(String.format("{id:%s,lvl:%s},", ench.getKey().getId(), ench.getValue()));
        }
        enchs.deleteCharAt(enchs.length() - 1);
        return enchs.toString();
    }

    /**
     * 取一个列表中某个lore存在的行数
     *
     * @param lores 列表集合
     * @param lore  要判断的数据
     * @return 该Lore所在的行数
     */
    public static int getLoreIndex(List<String> lores, String lore) {
        int i = 0;
        for (String a : lores) {
            if (a.equalsIgnoreCase(lore)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * 设置某物品指定行数的Lore
     *
     * @param is   物品(ItemStack类型)
     * @param Line 行数
     * @param msg  要设置的Lore
     */
    public static void setLore(ItemStack is, int Line, String msg) {
        List<String> lore = Lists.newArrayList();
        if (is == null || is.getType() == Material.AIR) {
            return;
        }
        if (is.getItemMeta().hasLore()) {
            lore.addAll(is.getItemMeta().getLore());
            lore.set((Line - 1), msg.replaceAll("&", "§"));
            is.getItemMeta().setLore(lore);
            is.setItemMeta(is.getItemMeta());
        } else {
            return;
        }
    }

    /**
     * 添加Lore
     *
     * @param is  需要设置的物品
     * @param msg 待添加的String
     * @return 该物品的ItemStack对象
     */
    public static ItemStack addLore(ItemStack is, String msg) {
        Validate.notNull(is);

        String lore = ChatColor.translateAlternateColorCodes('&', msg);
        ItemMeta im = is.getItemMeta();
        if (im.hasLore()) {
            List<String> l = im.getLore();
            l.add(lore);
            im.setLore(l);
            is.setItemMeta(im);
            return is;
        }
        List<String> l = new ArrayList<>();
        l.add(lore);
        im.setLore(l);
        is.setItemMeta(im);
        return is;
    }

    /**
     * 替换指定的Lore
     *
     * @param is        需要替换的物品
     * @param old       原Lore
     * @param newString 新Lore
     * @return 该物品的ItemStack
     */
    public static ItemStack replaceLore(ItemStack is, String old, String newString) {
        Validate.notNull(is);

        ItemMeta im = is.getItemMeta();
        List<String> lore = im.getLore();
        if (!lore.contains(old)) {
            return is;
        }
        while (true) {
            if (!lore.contains(old)) {
                break;
            }
            lore.set(lore.indexOf(old), newString);
        }
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }
}
