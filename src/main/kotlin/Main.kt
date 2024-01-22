/**
 * ¿DÓNDE ESTÁ R2D2?
 *
 * Calcula dónde estará el robot R2D2 (sus coordenadas finales) que se encuentra en una cuadrícula representada por los ejes "x" e "y".
 *
 *  El robot siempre comienza en la coordenada (0, 0).
 *  Para indicarle que se mueva, le enviamos un array formado por enteros (positivos o negativos) que indican la secuencia de pasos a dar.
 *  Por ejemplo: [10, 5, -2] indica que primero se mueve 10 pasos, se detiene, luego 5, se detiene, y finalmente 2 y se detiene. El resultado en este caso sería (x: -5, y: 12)
 * Si el número de pasos es negativo, se desplazaría en sentido contrario al que está mirando.
 * Los primeros pasos los hace en el eje "y". Interpretamos que inicialmente siempre está mirando hacia la parte positiva del eje "y".
 * El robot tiene un fallo en su programación: cada vez que finaliza una secuencia de pasos y se detiene, gira 90 grados en el sentido contrario a las agujas del reloj.
 */

enum class Direccion {
    POSITIVEY,
    NEGATIVEX,
    NEGATIVEY,
    POSITIVEX
}

class Robot(nombre: String) {
    var posX = 0
    var posY = 0
    var dir = 0
    var nombre: String = ""
        set(value) {
            require(value.isNotEmpty()) { "Casque por nombre vacío" }
            field = value
        }

    init {
        this.nombre = nombre //El nombre es obligatorio y no puede estar vacío
    }

    constructor(nombre: String, posX: Int, posY: Int) : this(nombre) {
        this.posX = posX
        this.posY = posY
    }

    constructor(nombre: String, posX: Int, posY: Int, diegoooll: Int) : this(nombre) {
        this.posX = posX
        this.posY = posY
        this.dir = diegoooll

    }

    /**
     * Realiza el movimiento del robot
     *
     * @param movis Int (vararg) Lista de movimientos a realizar
     *
     * @return List<Int>
     */

    fun mover(movis: IntArray): List<Int> {
        //Comienza en la posición (0, 0)
        // dir: 0 => POSITIVEY; 1 => NEGATIVEX; 2=> NEGATIVEY; 3 => POSITIVEX

        // Realizar los movimientos en la dirección en la que esté el robot
        for (pasos in movis) {
            when (this.dir) {
                0 -> this.posY += pasos
                1 -> this.posX -= pasos
                2 -> this.posY -= pasos
                3 -> this.posX += pasos
            }
            //Al finalizar cada movimiento
            if (this.dir == 3) this.dir = 0 else this.dir += 1
        }
        return listOf(posY, posX, dir)
    }

    /**
     * Indica la orientación del robot al finalizar el movimiento
     *
     * @param dir Int dirección en la que apunta al terminar los pasos
     *
     * @return String dirección indicando positivo o negativo del eje X o Y
     */
    fun obtenerDireccion() = when (this.dir) {
        0 -> "POSITIVEY"
        1 -> "NEGATIVEX"
        2 -> "NEGATIVEY"
        3 -> "POSITIVEX"
        else -> ""
    }

    fun mostrarPosicion(): String {
        return ("x: ${this.posX}, y: ${this.posY}, direction: ${obtenerDireccion()}")
    }
}

fun main() {

    val robot1 = Robot("R2D2")

    val movimientos = listOf(
        intArrayOf(1, -5, 0, -9),
        intArrayOf(3, 3, 5, 6, 1, 0, 0, -7),
        intArrayOf(2, 1, 0, -1, 1, 1, -4),
        intArrayOf(),
        intArrayOf(3, 5)
    )
/**
 * Una forma de hacerlo, pero la segunda es mejor
    for (movimiento in movimientos) {
        robot1.mover(movimiento)
        println(robot1.mostrarPosicion())
    }
*/
    //Usando función lambda, recorre cada elemento de movimientos (it)
    movimientos.forEach {
        robot1.mover(it)
        println(robot1.mostrarPosicion())
    }

}

