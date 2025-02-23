package solutions.thex.reporeporter.responseWrapper.badge.inline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import solutions.thex.reporeporter.svg.responseWrapper.badge.inline.InlineTitleAsResponseWrapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class InlineTitleAsResponseWrapperTests {

    private InlineTitleAsResponseWrapper inlineTitleAsResponseWrapper;

    @BeforeEach
    void setup() {
        inlineTitleAsResponseWrapper = new InlineTitleAsResponseWrapper();
    }

    @Test
    void wrapMustReturnUnprocessableEntityHttpStatusAsResponseEntityWhenAnyOfParamsNotProvided() throws IOException {
        // Given
        // missing title param for example
        String design = "s_simple_black_white_#";

        // When
        var response = inlineTitleAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void wrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithWhiteSpaceSplitter() throws IOException {
        // Given
        String design = "s simple repo-reporter black white #";

        // When
        var response = inlineTitleAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void wrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithUnderLineSplitter() throws IOException {
        // Given
        String design = "s_simple_repo-reporter_black_white_#";

        // When
        var response = inlineTitleAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void wrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithHyphenSplitter() throws IOException {
        // Given
        String design = "s-simple-repo_reporter-black-white-#";

        // When
        var response = inlineTitleAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnUnprocessableEntityHttpStatusAsResponseEntityWhenAnyOfRequiredParamsNotProvided() throws IOException {
        // Given
        // missing title param for example
        String design = "black_white";

        // When
        var response = inlineTitleAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithWhiteSpaceSplitter() throws IOException {
        // Given
        String design = "repo-reporter black s";

        // When
        var response = inlineTitleAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithUnderLineSplitter() throws IOException {
        // Given
        String design = "repo-reporter_black_s";

        // When
        var response = inlineTitleAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithHyphenSplitter() throws IOException {
        // Given
        String design = "repo_reporter-black-s";

        // When
        var response = inlineTitleAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
