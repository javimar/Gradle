package eu.javimar.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.text.TextUtils;
import android.util.Log;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class AsyncTaskNotEmptyTest
{
    @Test
    public void testNonEmptyJoke()
    {
        String jokeReturnedByAsycnTask = "";

        JokesAsyncTask endpointsAsyncTask = (JokesAsyncTask)
                new JokesAsyncTask(
                        InstrumentationRegistry.getTargetContext(),
                        null)
                        .execute();

        try {
            jokeReturnedByAsycnTask = endpointsAsyncTask.get();
        }
        catch (InterruptedException | ExecutionException e) {
            Log.e(AsyncTaskNotEmptyTest.class.getSimpleName(), e.getMessage());
        }
        Assert.assertTrue("AsyncTask returns empty",
                !TextUtils.isEmpty(jokeReturnedByAsycnTask));
    }
}
