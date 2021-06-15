package rationals

import java.math.BigInteger

class Rational(var n: BigInteger, var d: BigInteger) : Comparable<Rational> {

    init {
        if (d.equals(0)) {
            throw IllegalArgumentException()
        }
    }

    override fun equals(other: Any?): Boolean {
        other as Rational
        this.simplify()
        other.simplify()
        return if (this === other) {
            true
        } else {
            this.n.toDouble().div(this.d.toDouble()) == (other.n.toDouble().div(other.d.toDouble()))
        }
    }

    override fun toString(): String {
        this.simplify()
        return when {
            d == 1.toBigInteger() || n.rem(d) == 0.toBigInteger() -> n.div(d).toString()
            this.d < 0.toBigInteger() || (this.n < 0.toBigInteger() && this.d < 0.toBigInteger()) -> {
                fromRationalToString(Rational(this.n.negate(), this.d.negate()))
            }
            else -> {
                fromRationalToString(Rational(this.n, this.d))
            }
        }
    }

    override fun compareTo(other: Rational): Int {
        val lhs: BigInteger = this.n * other.d
        val rhs: BigInteger = this.d * other.n
        if (lhs < rhs) return -1
        return if (lhs > rhs) +1 else 0
    }

    operator fun plus(r: Rational): Rational = (n.times(r.d).plus(r.n.times(d))).divBy(r.d.times(d))

    operator fun minus(r: Rational): Rational = (n.times(r.d).minus(r.n.times(d))).divBy(r.d.times(d))

    operator fun times(r: Rational): Rational = n.times(r.n).divBy(r.d.times(d))

    operator fun div(r: Rational): Rational = n.times(r.d).divBy(d.times(r.n))

    operator fun unaryMinus(): Rational = Rational(n.negate(), d)

    fun simplify() {
        val gcf: BigInteger = getGCF(this.n, this.d)
        this.n = this.n.divide(gcf)
        this.d = this.d.divide(gcf)
    }

    private fun fromRationalToString(r: Rational): String = "${r.n}/${r.d}"

    private fun getGCF(num: BigInteger, den: BigInteger): BigInteger {
        var quotient: BigInteger?
        var remainder = (1).toBigInteger()
        var val1 = num
        var val2 = den

        while (remainder != (0).toBigInteger()) {
            quotient = val1.divide(val2)
            remainder = val1.minus(quotient.multiply(val2))
            if (remainder != (0).toBigInteger()) {
                val1 = val2
                val2 = remainder
            }
        }
        return val2
    }
}

infix fun Int.divBy(r2: Int): Rational = Rational(toBigInteger(), r2.toBigInteger())

infix fun Long.divBy(r2: Long): Rational = Rational(toBigInteger(), r2.toBigInteger())

infix fun BigInteger.divBy(r2: BigInteger): Rational = Rational(this, r2)

fun String.toRational(): Rational {
    val fraction = split("/")

    return when (fraction.size) {
        1 -> Rational(fraction[0].toBigInteger(), 1.toBigInteger())
        else -> Rational(fraction[0].toBigInteger(), fraction[1].toBigInteger())
    }
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}
