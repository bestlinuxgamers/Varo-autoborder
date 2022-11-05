package net.bestlinuxgamers.varoAutoborder

import de.cuuky.varo.api.VaroAPI
import net.bestlinuxgamers.varoAutoborder.config.Config
import net.bestlinuxgamers.varoAutoborder.config.ConfigManager
import net.bestlinuxgamers.varoAutoborder.listener.VaroStartListener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main : JavaPlugin() {

    private lateinit var config: Config

    override fun onEnable() {
        loadConfig()
        registerListeners()
    }

    private fun loadConfig() {
        config = ConfigManager(File("./$dataFolder/config.json"), logger).load()
    }

    private fun registerListeners() {
        with(VaroAPI.getEventManager()) {
            registerEvent(VaroStartListener(config.playerBorder, config.minBorderSize))
        }
    }

}
