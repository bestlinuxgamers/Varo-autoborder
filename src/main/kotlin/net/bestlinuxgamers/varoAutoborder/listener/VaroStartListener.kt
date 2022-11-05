package net.bestlinuxgamers.varoAutoborder.listener

import de.cuuky.varo.Main
import de.cuuky.varo.api.event.events.game.VaroStartEvent
import de.cuuky.varo.api.event.register.VaroEventMethod
import de.cuuky.varo.api.event.register.VaroListener
import de.cuuky.varo.entity.player.VaroPlayer
import kotlin.math.max

class VaroStartListener(private val playerBorder: Int, private val minBorderSize: Int) : VaroListener {

    @VaroEventMethod
    fun onEvent(event: VaroStartEvent) {
        val worldHandler = Main.getVaroGame().varoWorldHandler

        val playerSize = VaroPlayer.getOnlineAndAlivePlayer().size
        val calcBorderSize = playerBorder * playerSize
        val borderSize = max(calcBorderSize, minBorderSize)

        worldHandler.setBorderSize(borderSize.toDouble(), 0, null)

    }

}
