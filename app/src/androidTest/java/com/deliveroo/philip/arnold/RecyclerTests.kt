package com.deliveroo.philip.arnold

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.deliveroo.philip.arnold.mainscreen.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.MockResponse
import org.junit.After







@RunWith(AndroidJUnit4::class)
class RecyclerTests {
    val mMockWebServer = MockWebServer()

    val knownFilm = "The Shawshank Redemption"
    // a single entry that only contains Shawshank Redemption
    val singleContent = "{\n" +
            "\"page\": 1,\n" +
            "\"total_results\": 7531,\n" +
            "\"total_pages\": 377,\n" +
            "\"results\": [\n" +
            "{\n" +
            "\"popularity\": 27.486,\n" +
            "\"vote_count\": 13583,\n" +
            "\"video\": false,\n" +
            "\"poster_path\": \"/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg\",\n" +
            "\"id\": 278,\n" +
            "\"adult\": false,\n" +
            "\"backdrop_path\": \"/j9XKiZrVeViAixVRzCta7h1VU9W.jpg\",\n" +
            "\"original_language\": \"en\",\n" +
            "\"original_title\": \"The Shawshank Redemption\",\n" +
            "\"genre_ids\": [\n" +
            "80,\n" +
            "18\n" +
            "],\n" +
            "\"title\": \"The Shawshank Redemption\",\n" +
            "\"vote_average\": 8.7,\n" +
            "\"overview\": \"Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.\",\n" +
            "\"release_date\": \"1994-09-23\"\n" +
            "},\n" +
            "]\n" +
            "}"

    @get:Rule
    private val activityRule = ActivityTestRule(
        MainActivity::class.java, false, false)

    @Before
    fun setup() {
//        mMockWebServer.start(443);
//        mMockWebServer.enqueue(MockResponse().setBody(singleContent).setResponseCode(200))

        activityRule.launchActivity(null)
    }
    @After
    @Throws(Exception::class)
    fun tearDown() {
        //mMockWebServer.shutdown()
    }

    @Test
    fun waitAndCheckForKnownFilm() {
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler)).check(
            ViewAssertions.matches(
                EspressoTestUtils.atPosition(
                    1,
                    ViewMatchers.hasDescendant(ViewMatchers.withText(knownFilm))
                )
            )
        )
    }
}