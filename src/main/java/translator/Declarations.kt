package translator

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import eotree.EOBndExpr
import eotree.EOCopy
import eotree.EODot
import eotree.EOExpr
import eotree.eoDot
import lexer.TokenCode
import tree.Declaration.ClassDeclaration
import tree.Declaration.Declaration
import tree.Declaration.MethodDeclaration
import tree.Declaration.NormalClassDeclaration
import tree.Declaration.VariableDeclaration
import tree.Initializer
import tree.Type.PrimitiveType
import tree.Type.TypeName
import util.ParseExprTasks
import util.TokenCodes

fun mapClassDeclaration(dec: Declaration): Option<EOBndExpr> {
    // TODO: get rid of option and implement all cases

    return when (dec) {
        is MethodDeclaration -> {
            mapMethodDeclaration(dec).some()
        }
        is NormalClassDeclaration -> {
            mapClass(dec as ClassDeclaration).some()
        }
        is VariableDeclaration -> {
            preMapVariableDeclaration(dec).some()
        }
        else -> {
            None
        }
    }
}

/**
 * Maps a variable declaration in class object (i.e. static variable).
 */
fun preMapVariableDeclaration(dec: VariableDeclaration): EOBndExpr =
    when (dec.type) {
        is TypeName ->
            EOBndExpr(EODot("cage"), dec.name)
        is PrimitiveType ->
            EOBndExpr(
                EOCopy(
                    listOf(decodePrimitiveType(dec.type as PrimitiveType).value, decodeInitializer(dec.initializer)).eoDot(),
                    listOf(decodePrimitiveType(dec.type as PrimitiveType).value, "new").eoDot()
                ),
                dec.name
            )
        null ->
            throw IllegalArgumentException("\"var\" declarations are not supported yet")
        else ->
            throw IllegalArgumentException("Type of type " + dec.type.javaClass.name + " is not supported")
    }

fun mapVariableDeclaration(parseExprTasks: ParseExprTasks, dec: VariableDeclaration): EOExpr =
    when (dec.type) {
        is TypeName -> {
            EOCopy(listOf(dec.name, "write").eoDot(), parseExprTasks.addTask(dec.initializer).eoDot())
        }
        is PrimitiveType -> {
            EOCopy(
                listOf(dec.name, "write").eoDot(),
                parseExprTasks.addTask(dec.initializer).eoDot()
            )
        }
        null ->
            throw IllegalArgumentException("\"var\" declarations are not supported yet")
        else ->
            throw IllegalArgumentException("Type of type " + dec.type.javaClass.name + " is not supported")
    }

fun decodePrimitiveType(type: PrimitiveType): TokenCodes {
    return when (type.typeCode) {
        TokenCode.Char -> TokenCodes.PRIM__CHAR
        TokenCode.Int -> TokenCodes.PRIM__INT
        TokenCode.Float -> TokenCodes.PRIM__FLOAT
        TokenCode.Double -> TokenCodes.PRIM__DOUBLE
        TokenCode.Boolean -> TokenCodes.PRIM__BOOLEAN
        TokenCode.Long -> TokenCodes.PRIM__LONG
        TokenCode.Byte -> TokenCodes.PRIM__BYTE
        TokenCode.Short -> TokenCodes.PRIM__SHORT
        else -> throw IllegalArgumentException("Type code " + type.typeCode + " is not supported")
    }
}

fun decodeInitializer(initializer: Initializer?): String {
    return "constructor_1"
}
