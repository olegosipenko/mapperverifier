/*
 * Copyright 2018 Oleg Osipenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mapperverifier

import java.lang.reflect.Constructor
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.*


class MapperVerifier<T> private constructor(private val type: Class<T>) {

    private lateinit var mappingMethod: Method

    companion object {
        const val GETTER_PREFIX = "get"
        const val BOOL_GETTER_PREFIX = "is"
        const val SETTER_PREFIX = "set"
        const val BUILD_METHOD = "build"

        fun <T> forClass(type: Class<T>): MapperVerifier<T> {
            return MapperVerifier(type)
        }
    }

    fun forMethod(method: Method): MapperVerifier<T> {
        mappingMethod = method
        return this
    }

    fun verify() {
        try {
            performVerification()
        } catch (e: IllegalArgumentException) {
            Assert.fail(e.message)
        }
    }

    private fun performVerification() {
        extractMappingMethod()

        val mapper = type.newInstance()

        val outClass = mappingMethod.parameterTypes[0]
        val outObject = constructOutObject(outClass)
        val outGetters = extractGetters(outClass)

        val inObject = mappingMethod.invoke(mapper, outObject)
        val inClass = mappingMethod.returnType
        val inGetters = extractGetters(inClass)

        assertFields(outGetters, inGetters, outObject, inObject)
    }

    private fun extractMappingMethod() {
        if (!::mappingMethod.isInitialized) {
            val methods = type.methods.filter {
                it.parameterCount == 1 &&
                        it.declaringClass != Object::class.java && it.returnType != Void.TYPE
            }
            validateExtractedMethods(methods)
            mappingMethod = methods[0]
        }
    }

    private fun validateExtractedMethods(methods: List<Method>) {
        if (methods.size > 1) {
            throwMissingMethodException()
        }
    }

    private fun throwMissingMethodException() {
        throw IllegalArgumentException("Mapper class should contain only one public method with type of class " +
                "from and with return type of out class")
    }

    private fun constructOutObject(outClass: Class<*>): Any {
        val outClassConstructors = outClass.constructors

        if (outClassConstructors.isNotEmpty()) {
            val constructor = outClassConstructors[0]
            val constructorArguments = initConstructorArguments(constructor)
            return constructor.newInstance(constructorArguments)
        } else {
            return constructOutBuilderObject(outClass)
        }
    }

    private fun initConstructorArguments(constructor: Constructor<*>): Array<Any> {
        val constructorArguments = Array<Any>(constructor.parameterCount, { it + 1 })
        val constructorParameters = constructor.parameters

        for (index in constructorParameters.indices) {
            constructorArguments[index] = getRandomValue(constructorParameters[index].type)
        }
        return constructorArguments
    }

    private fun getRandomValue(parameterType: Class<*>): Any {
        val random = Random()
        val value = when (parameterType) {
            Byte::class.java -> random.nextInt(Byte.MAX_VALUE.toInt()).toByte()
            Short::class.java -> random.nextInt(Short.MAX_VALUE.toInt()).toShort()
            Int::class.java -> random.nextInt()
            Long::class.java -> random.nextLong()
            Float::class.java -> random.nextFloat()
            Double::class.java -> random.nextDouble()
            Boolean::class.java -> random.nextBoolean()
            Char::class.java -> random.nextInt(Byte.MAX_VALUE.toInt()).toChar()
            String::class.java -> getRandomString(random);
            else -> throw UnsupportedOperationException("not implemented yet")
        }
        return value
    }

    private fun getRandomString(random: Random): String {
        val leftLimit = 97;
        val rightLimit = 122;
        val stringLength = random.nextInt(Byte.MAX_VALUE.toInt())
        val sb = StringBuilder(stringLength)
        for (i in 0 until stringLength) {
            val randomLimitedInt = leftLimit + (random.nextFloat() * (rightLimit - leftLimit + 1)).toInt()
            sb.append(randomLimitedInt.toChar())
        }
        return sb.toString()
    }

    private fun constructOutBuilderObject(outClass: Class<*>): Any {
        val builderClass = outClass.declaredClasses[0]
        val constructor = builderClass.declaredConstructors[0]
        val builderObject = constructor.newInstance()

        val setters = builderClass.methods.filter { it.name.startsWith(SETTER_PREFIX) && it.returnType == builderClass }

        for (setter in setters) {
            val setterParameter = setter.parameterTypes[0]
            setter.invoke(builderObject, getRandomValue(setterParameter))
        }

        val buildMethod = builderClass.declaredMethods.filter { it.name.startsWith(BUILD_METHOD) && it.returnType == outClass }[0]

        return buildMethod.invoke(builderObject)
    }

    private fun extractGetters(clazz: Class<*>): List<Method> {
        return clazz.methods.filter { (it.name.startsWith(GETTER_PREFIX) || it.name.startsWith(BOOL_GETTER_PREFIX)) &&
                it.returnType != Void.TYPE && notNative(it) }
    }

    private fun notNative(method: Method): Boolean {
        val modifiers = method.modifiers
        return !Modifier.isNative(modifiers)
    }

    private fun assertFields(outGetters: List<Method>, inGetters: List<Method>, outObject: Any, inObject: Any) {
        if (outGetters.size != inGetters.size) {
            Assert.fail("number of getters should be equal")
        }
        for (index in outGetters.indices) {
            val outGetter = outGetters[index]
            val inGetter = inGetters[index]
            val outValue = outGetter.invoke(outObject)
            val inValue = inGetter.invoke(inObject)
            if (outValue != inValue) Assert.fail("values for $outGetter not equal")
        }
    }
}