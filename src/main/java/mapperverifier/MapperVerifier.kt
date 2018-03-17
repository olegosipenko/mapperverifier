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

class MapperVerifier<T> private constructor(private val type: Class<T>) {

    private lateinit var mappingMethod: Method

    companion object {
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
        val constructor = outClass.constructors[0]

        val constructorArguments = initConstructorArguments(constructor)
        return constructor.newInstance(constructorArguments)
    }

    private fun initConstructorArguments(constructor: Constructor<*>): Array<Any> {
        val constructorArguments = Array<Any>(constructor.parameterCount, { it + 1 })

        for (index in constructor.parameters.indices) {
            val value = when (constructor.parameters[index].type) {
                Byte::class.java -> Byte.MIN_VALUE
                Short::class.java -> Short.MIN_VALUE
                Int::class.java -> Int.MIN_VALUE
                Long::class.java -> Long.MIN_VALUE
                Float::class.java -> Float.MIN_VALUE
                Double::class.java -> Double.MIN_VALUE
                Boolean::class.java -> true
                Char::class.java -> 'a'
                String::class.java -> "correct"
                else -> throw UnsupportedOperationException("not implemented yet")
            }
            constructorArguments.set(index, value)
        }
        return constructorArguments
    }

    private fun extractGetters(clazz: Class<*>): List<Method> {
        return clazz.methods.filter { (it.name.startsWith("get") || it.name.startsWith("is")) && it.returnType != Void.TYPE }
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