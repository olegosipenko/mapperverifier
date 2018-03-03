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

class MapperVerifier<T> private constructor(configuration: Configuration<T>) {

    companion object {
        fun <T> forClass(type: Class<T>): MapperVerifier<T> {
            val configuration = Configuration.of(type)
            return MapperVerifier(configuration)
        }
    }

    fun verify() {
        try {
            performVerification()
        } catch (e: Throwable) {
            handleError(e)
        }
    }

    private fun performVerification() {

    }

    private fun handleError(e: Throwable) {

    }
}