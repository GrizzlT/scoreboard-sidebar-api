/*
 * MIT License
 *
 * Copyright (c) 2018 ColoredCarrot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.coloredcarrot.api.sidebar;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * All rights reserved.
 * Forked by GrizzlT
 *
 * @author ColoredCarrot
 * @since 2.5
 */
public class SidebarPlaceholders extends PlaceholderExpansion
{
    @Override
    public boolean canRegister()
    {
        return true;
    }

    @Override
    public boolean persist()
    {
        return true;
    }

    @Override
    public @NotNull String getIdentifier()
    {
        return "sidebar";
    }

    @Override
    public @NotNull String getAuthor()
    {
        return SidebarAPI.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion()
    {
        return SidebarAPI.getInstance().getDescription().getVersion();
    }

    /**
     * Valid placeholders (in full form) (n can be any integer above -1):
     * - %sidebar_title%
     * - %sidebar_linenextvar_n%
     * - %sidebar_line_n_var_n%
     */
    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier)
    {
        try
        {
            if (player == null) {
                return "";
            }

            if (identifier.equals("title"))
            {

                Sidebars sidebar = SidebarAPI.getSidebar(player);

                if (sidebar == null)
                    return "";
                else
                    return sidebar.getTitle();

            }

            else if (identifier.startsWith("linenextvar_"))
            {

                int line = Integer.parseInt(identifier.split("inenextvar_")[1]);

                Sidebars sidebar = SidebarAPI.getSidebar(player);

                if (sidebar == null)
                    return "";

                return sidebar.getEntries().get(line).getNext();

            }

            else if (identifier.startsWith("line_") && identifier.contains("_var_"))
            {

                int line = Integer.parseInt(identifier.split("ine_")[1].split("_var_")[0]);
                int var  = Integer.parseInt(identifier.split("_var_")[1]);

                Sidebars sidebar = SidebarAPI.getSidebar(player);

                if (sidebar == null)
                    return "";

                return sidebar.getEntries().get(line).getVariations().get(var);

            }

        }
        catch (Exception ignored)
        {
        }

        return "";
    }
}
