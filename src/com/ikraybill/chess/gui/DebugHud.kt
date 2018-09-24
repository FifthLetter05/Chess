package com.ikraybill.chess.gui

import java.awt.*
import java.util.HashMap
import javax.swing.JPanel

class DebugHud: JPanel(true) {
    private var renderedText: String? = null
    private val content: MutableMap<String, Any>

    init {
        content = HashMap()
    }

    fun addLine(description: String, data: Any) {
        content[description] = data
        renderedText = ""
        for ((key, value) in content) {
            renderedText += key + ": " + value.toString() + "\n"
        }

    }

    fun removeLine(description: String) {
        content.remove(description)
        renderedText = ""
        for ((key, value) in content) {
            renderedText += key + ": " + value.toString() + "\n"
        }
    }

    fun draw(g: Graphics) {
        var i = 1
        val fontSize = 16
        g.font = Font(Font.SANS_SERIF, Font.BOLD, fontSize)
        for ((key, value) in content) {
            i++
            g.drawString(key + ": " + value.toString(), 10, fontSize * i)
        }
    }

    @Synchronized
    override fun paint(g: Graphics){
        super.paint(g)
        var i = 1
        val fontSize = 16
        g.color = Color(70, 73, 255)
        g.font = Font(Font.SANS_SERIF, Font.BOLD, fontSize)
        for ((key, value) in content) {
            i++
            g.drawString(key + ": " + value.toString(), 10, fontSize * i)
        }
    }
}
