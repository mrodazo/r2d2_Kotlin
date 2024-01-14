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

class Robot(var nombre: String) {
    var posX = 0
    var posY = 0
    var dir = 0

    constructor(nombre: String, posX: Int, posY: Int) : this(nombre) {
        this.posX = posX
        this.posY = posY
    }

    constructor(nombre: String, posX: Int) : this(nombre) {
        this.posX = posX
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

    fun moverRobot(vararg movis: Int): List<Int> {
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
    fun orientacionRobot() = when (this.dir) {
        0 -> "POSITIVEY"
        1 -> "NEGATIVEX"
        2 -> "NEGATIVEY"
        3 -> "POSITIVEX"
        else -> ""
    }

    fun posicion(): String {
        return ("x: ${this.posX}, y: ${this.posY}, direction: ${orientacionRobot()}")
    }

    fun reiniciarPosicion() {
        this.posX = 0
        this.posY = 0
        this.dir = 0
    }
}

fun main() {

    var robot1 = Robot("R2D2")
    var robot2 = Robot("C3PO", 99, -67)


    robot1.moverRobot(10, 5, -2)
    println(robot1.posicion())
    robot1.reiniciarPosicion()

    robot1.moverRobot(0, 0, 0)
    println(robot1.posicion())
    robot1.reiniciarPosicion()

    robot1.moverRobot()
    println(robot1.posicion())
    robot1.reiniciarPosicion()

    robot1.moverRobot(-10, -5, 2)
    println(robot1.posicion())
    robot1.reiniciarPosicion()

    robot1.moverRobot(-10, -5, 2, 4, -8)
    println(robot1.posicion())
}


/*
var r2d2Pos: List<Int>

//
r2d2Pos = moverRobot(10, 5, -2)
println("x: ${r2d2Pos[0]}, y: ${r2d2Pos[1]}, direction: ${orientacionRobot(r2d2Pos[2])}")

r2d2Pos = moverRobot(0, 0, -2)
println("x: ${r2d2Pos[0]}, y: ${r2d2Pos[1]}, direction: ${orientacionRobot(r2d2Pos[2])}")

r2d2Pos = moverRobot()
println("x: ${r2d2Pos[0]}, y: ${r2d2Pos[1]}, direction: ${orientacionRobot(r2d2Pos[2])}")

}


/**
* Realiza el movimiento del robot
*
* @param movis Int (vararg) Lista de movimientos a realizar
*
* @return List<Int>
*/
fun moverRobot (vararg movis: Int): List<Int> {

var posX = 0
var posY = 0
var direRobot = 0 //0 => POSITIVEY; 1 => NEGATIVEX; 2=> NEGATIVEY; 3 => POSITIVEX

// Realizar los movimientos en la dirección en la que esté el robot
for (pasos in movis){
    println("$pasos")
    when (direRobot) {
        0 -> posY += pasos
        1 -> posX -= pasos
        2 -> posY -= pasos
        3 -> posX += pasos
    }
    //Al finalizar cada movimiento
    if (direRobot == 3) direRobot = 0 else direRobot+=1
}

return listOf(posY, posX, direRobot)
}


fun orientacionRobot (direRobot: Int) = when (direRobot) {
    0 -> "POSITIVEY"
    1 -> "NEGATIVEX"
    2 -> "NEGATIVEY"
    3 -> "POSITIVEX"
    else -> ""
}

//Modificar el programa para que el usuario introduzca los pasos

*/
