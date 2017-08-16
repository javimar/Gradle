package eu.javimar.gradle.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokesbackend.backend.gradle.javimar.eu",
                ownerName = "jokesbackend.backend.gradle.javimar.eu",
                packagePath = ""
        )
)
public class MyEndpoint
{
    @ApiMethod
    public MyBean tellAJoke()
    {
        return new MyBean();
    }
}
