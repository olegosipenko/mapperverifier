package mapperverifier

import org.junit.Test

class MapperVerifierTest {

    @Test(expected = AssertionException::class)
    fun testBrokenByteMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenByteMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenShortMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenShortMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenIntMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenIntMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenLongMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenLongMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenFloatMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenFloatMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenDoubleMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenDoubleMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenBooleanMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenBooleanMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenCharMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenCharMapper::class.java)
        bbMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenStringMapper() {
        val bbMapperVerifier = MapperVerifier.forClass(BrokenStringMapper::class.java)
        bbMapperVerifier.verify()
    }

    class BrokenByteMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    1,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }

        fun otherMethod() {

        }

        private fun applyPrivately(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    1,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenShortMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    1,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenIntMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    1,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenLongMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    1,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenFloatMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    1f,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenDoubleMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    1.0,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenBooleanMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    false,
                    modelRed.charValue,
                    modelRed.stringValue
            )
        }
    }

    class BrokenCharMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    'w',
                    modelRed.stringValue
            )
        }
    }

    class BrokenStringMapper {
        fun apply(modelRed: ModelRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    "wrong"
            )
        }
    }

    data class ModelRed(
            val byteValue: Byte,
            val shortValue: Short,
            val intValue: Int,
            val longValue: Long,
            val floatValue: Float,
            val doubleValue: Double,
            val booleanValue: Boolean,
            val charValue: Char,
            val stringValue: String)

    data class ModelBlack(val byteValue: Byte,
                     val shortValue: Short,
                     val intValue: Int,
                     val longValue: Long,
                     val floatValue: Float,
                     val doubleValue: Double,
                     val booleanValue: Boolean,
                     val charValue: Char,
                     val stringValue: String)
}