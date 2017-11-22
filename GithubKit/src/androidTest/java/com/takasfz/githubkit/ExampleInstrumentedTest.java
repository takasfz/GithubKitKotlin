package com.takasfz.githubkit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.takasfz.githubkit.core.response.Response;
import com.takasfz.githubkit.core.response.User;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        JSONObject json = new JSONObject("{\"data\":{\"search\":{\"nodes\":[{\"avatarUrl\":\"https://avatars3.githubusercontent.com/u/478864?v=4\",\"bio\":\"\",\"followers\":{\"totalCount\":68},\"following\":{\"totalCount\":1},\"id\":\"MDQ6VXNlcjQ3ODg2NA==\",\"location\":\"Portland, OR\",\"login\":\"martypdx\",\"repositories\":{\"totalCount\":163},\"url\":\"https://github.com/martypdx\",\"websiteUrl\":\"\"}],\"pageInfo\":{\"endCursor\":\"Y3Vyc29yOjU=\",\"hasNextPage\":true,\"hasPreviousPage\":false,\"startCursor\":\"Y3Vyc29yOjE=\"},\"userCount\":2778}}}");
        Response response = new Response(User.getDecoder(), new String[]{ "search" }, "userCount", json);
        Log.d("", response.toString());

        assertEquals("com.takasfz.githubkit.test", appContext.getPackageName());
    }
}
