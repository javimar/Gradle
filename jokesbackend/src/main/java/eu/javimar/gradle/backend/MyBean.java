package eu.javimar.gradle.backend;

import eu.javimar.gradle.jokes.Jokes;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean
{

    public String getData()
    {
        Jokes jokes = new Jokes();
        return jokes.tellJokes();
    }


}