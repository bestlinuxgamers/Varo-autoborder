package net.bestlinuxgamers.varoAutoborder.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

/**
 * Klasse zum Verwalten der Config
 */
class ConfigManager(private val file: File, private val logger: Logger) {
    private val json = Json {
        encodeDefaults = true
        prettyPrint = true
    }

    /**
     * Läd die Config. Wenn keine vorhanden ist, wird die standard Config benutzt.
     * @return Config, welche geladen wurde
     */
    fun load(): Config {
        val fileContent = readFromFile()
        if (fileContent == null) {
            save(DEFAULT_CONFIG)
            return DEFAULT_CONFIG
        }
        return json.decodeFromString(fileContent)
    }

    /**
     * Speichert eine Config
     * @param config Config, die gespeichert werden soll
     */
    fun save(config: Config) {
        writeToFile(json.encodeToString(config))
    }

    /**
     * Schreibt in die config Datei
     * @param content Inhalt, welcher in die config Datei geschrieben werden soll
     */
    private fun writeToFile(content: String) {
        if (!file.exists()) {
            val parent = Path(file.parent)
            if (!parent.exists()) {
                parent.createDirectories()
            }
            file.createNewFile()
        }
        val fileWriter = FileWriter(file)
        try {
            fileWriter.write(content)
            fileWriter.flush()
            fileWriter.close()
        } catch (ex: IOException) {
            logger.log(Level.SEVERE, "Unable to write Config to File")
        }
    }

    /**
     * Läd die Config aus der Datei
     * @return Inhalt der Config datei, oder null
     */
    private fun readFromFile(): String? {
        if (file.exists()) {
            return file.readText()
        }
        return null
    }

    companion object {
        val DEFAULT_CONFIG: Config = Config(50, 300)
    }

}
