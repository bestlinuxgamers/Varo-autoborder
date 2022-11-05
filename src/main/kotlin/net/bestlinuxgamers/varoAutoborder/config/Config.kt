package net.bestlinuxgamers.varoAutoborder.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(val playerBorder: Int, val minBorderSize: Int)
