package eotree

import arrow.core.Option
import arrow.core.getOrElse
import java.util.*
import java.util.stream.Collectors

class EOObject(
    var freeAttrs: List<String>,
    var varargAttr: Option<String>,
    var bndAttrs: List<EOBndExpr>,
    var comment: String = "",
) : EOExpr() {
    override fun generateEO(indent: Int): String {
        return listOfNotNull(
            comment.let {
                if (it.isNotEmpty())
                    indent(indent) + it + "\n"
                else
                    null
            },
            indent(indent),
            "[",
            freeAttrs.joinToString(" "),
            varargAttr
                .map { attr -> (if (freeAttrs.isNotEmpty()) " " else "") + attr + "..." }
                .getOrElse { null },
            "]",
            bndAttrs
                .map { attr: EOBndExpr -> "\n" + attr.generateEO(indent + 1) }
                .joinToString("")
        ).joinToString("")
    }

    override fun toString(): String = "[Object]"
}