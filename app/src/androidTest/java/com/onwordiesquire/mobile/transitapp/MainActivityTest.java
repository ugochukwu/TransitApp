package com.onwordiesquire.mobile.transitapp;

import android.content.res.Resources;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.presentation.routeslist.MainActivity;
import com.onwordiesquire.mobile.transitapp.presentation.routeslist.RouteViewModel;
import com.onwordiesquire.mobile.transitapp.test.common.Utils;
import com.onwordiesquire.mobile.transitapp.util.JSONResourceReader;
import com.onwordiesquire.mobile.transitapp.util.Utilities;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by michelonwordi on 10/18/16.
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void listOfRoutes() throws IOException {
        //arrange
        List<Route> routeList = getMockRouteData();


        //act and assert
        onView(withId(R.id.routes_list))
                .check(matches(isDisplayed()));
    }


    private List<Route> getMockRouteData() {
        Resources resources = mainActivityTestRule.getActivity().getApplicationContext().getResources();
        JSONResourceReader jsonResourceReader = new JSONResourceReader(resources, R.raw.data);
        return (jsonResourceReader.constructUsingGson(AvailableRoutes.class)).routes();

    }

}
