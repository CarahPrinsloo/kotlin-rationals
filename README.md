# kotlin-rationals
My implementation of a Rational class in Kotlin.

Your task is to implement a class Rational representing rational numbers. A rational number is a number expressed as a ratio n/d , where n (numerator) and d (denominator) are integer numbers, except that d cannot be zero. If the denominator is zero, you can throw an exception on creating a new instance of the class, e.g. IllegalArgumentException.

Examples of rational numbers are 1/2, 2/3, 117/1098, and 2/1 (which can alternatively be written simply as 2). Rational numbers are represented exactly, without rounding or approximation, which gives them the advantage compared to floating-point numbers.

Your task it to model the behavior of rational numbers, including allowing them to be added, subtracted, multiplied, divided and compared. All arithmetic and comparison operations must be available for rationals: +, -, *, /, ==, <, >= etc. Check whether a number belongs to a range should also be possible: 1/2 in 1/3..2/3 should return true.

The Rational class should contain a numerator and denominator which might be unlimited numbers, so use java.math.BigInteger class for storing them.

The rational numbers must be compared to their "normalized" forms: for example, 1/2 should be equal to 2/4, or 117/1098 to 13/122. The string representation of a rational must be also given in the normalized form. Note that the denominator 1 must be omitted, so 2/1 must be printed as "2". The denominator must be always positive in the normalized form. If the negative rational is normalized, then only the numerator can be negative, e.g. the normalized form of 1/-2 should be -1/2.

Note that you can use BigInteger.gcd to find the greatest common divisor used in the normalized form calculation.

You need to support two ways to create rationals. The first one is to convert a string representation to a rational directly, like in "1/2".toRational(). Converting an integer number should also be possible, and 1 should be used as denominator by default: "23".toRational() is the same as "23/1".toRational().

The alternative way to create a rational is to use divBy infix function like in 1 divBy 2. The receiver and the argument might be of types Int, Long, or BigInteger.

## Installation
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
brew update
brew install kotlin
```

## Demo
```
cd Task/src/rationals
kotlinc Rational.kt -include-runtime -d Rational.jar
java -jar Rational.jar
```
