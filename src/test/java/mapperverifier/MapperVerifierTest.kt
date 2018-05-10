package mapperverifier

import org.junit.Test

class MapperVerifierTest {

    @Test(expected = AssertionException::class)
    fun testBrokenByteMapper() {
        val bByteMapperVerifier = MapperVerifier.forClass(BrokenByteMapper::class.java)
        bByteMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenShortMapper() {
        val bShortMapperVerifier = MapperVerifier.forClass(BrokenShortMapper::class.java)
        bShortMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenIntMapper() {
        val bIntMapperVerifier = MapperVerifier.forClass(BrokenIntMapper::class.java)
        bIntMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenLongMapper() {
        val bLongMapperVerifier = MapperVerifier.forClass(BrokenLongMapper::class.java)
        bLongMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenFloatMapper() {
        val bFloatMapperVerifier = MapperVerifier.forClass(BrokenFloatMapper::class.java)
        bFloatMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenDoubleMapper() {
        val bDoubleMapperVerifier = MapperVerifier.forClass(BrokenDoubleMapper::class.java)
        bDoubleMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenBooleanMapper() {
        val bBoolMapperVerifier = MapperVerifier.forClass(BrokenBooleanMapper::class.java)
        bBoolMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenCharMapper() {
        val bCharMapperVerifier = MapperVerifier.forClass(BrokenCharMapper::class.java)
        bCharMapperVerifier.verify()
    }

    @Test(expected = AssertionException::class)
    fun testBrokenStringMapper() {
        val bStringMapperVerifier = MapperVerifier.forClass(BrokenStringMapper::class.java)
        bStringMapperVerifier.verify()
    }

    @Test
    fun testBuilderClass() {
        val javaBuilderMapper = MapperVerifier.forClass(JavaBuilderMapper::class.java)
        javaBuilderMapper.verify()
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

    class JavaBuilderMapper {
        fun apply(modelRed: JavaBuilderClassRed): ModelBlack {
            return ModelBlack(
                    modelRed.byteValue,
                    modelRed.shortValue,
                    modelRed.intValue,
                    modelRed.longValue,
                    modelRed.floatValue,
                    modelRed.doubleValue,
                    modelRed.booleanValue,
                    modelRed.charValue,
                    modelRed.stringValue)
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