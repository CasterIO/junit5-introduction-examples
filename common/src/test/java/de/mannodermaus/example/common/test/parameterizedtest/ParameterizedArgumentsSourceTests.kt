package de.mannodermaus.example.common.test.parameterizedtest

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.AnnotationConsumer
import java.util.stream.Stream

class ParameterizedArgumentsSourceTests {

    @ParameterizedTest(name = "Parsed from JSON: {0}")
    @JsonSource(text = "[{\"score\": 11, \"aceValue\": 1}, {\"score\": 4, \"aceValue\": 11}]")
    fun test(json: JsonNode) {
        val score = json["score"].asInt()
        val aceValue = json["aceValue"].asInt()

        when {
            score < 11 -> assertEquals(11, aceValue)
            else -> assertEquals(1, aceValue)
        }
    }
}

@ArgumentsSource(JsonArgumentsProvider::class)
annotation class JsonSource(@Language("JSON") val text: String)

class JsonArgumentsProvider : ArgumentsProvider, AnnotationConsumer<JsonSource> {

    private val mapper = ObjectMapper()
    private lateinit var text: String

    override fun accept(annotation: JsonSource) {
        this.text = annotation.text
    }

    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
        return mapper.readTree(text)
                .toList()
                .stream()
                .map { Arguments.of(it) }
    }
}
