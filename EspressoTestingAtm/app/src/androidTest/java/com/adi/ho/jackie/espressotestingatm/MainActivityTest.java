package com.adi.ho.jackie.espressotestingatm;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by JHADI on 3/28/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testIfBalanceIsDisplayed() throws Exception{
        onView(withId(R.id.balance_textview)).check(matches(isDisplayed()));
    }

//    @Test
//    public void depositMoney(){
//        String expectedValue = "$44.40";
//        onView(withId(R.id.input_balanceedittext)).perform(typeText("44.40"));
//        onView(withId(R.id.deposit_button)).perform(click());
//        onView(withId(R.id.balance_textview)).check(matches(withText(expectedValue)));
//
//    }

    // check if everything is displayed
    @Test
    public void testIfEverythingIsDisplayed() throws Exception {
        onView(withId(R.id.balance_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.input_balanceedittext)).check(matches(isDisplayed()));
        onView(withId(R.id.withdraw_button)).check(matches(isDisplayed()));
        onView(withId(R.id.deposit_button)).check(matches(isDisplayed()));
        onView(withId(R.id.see_accnt_info_button)).check(matches(isDisplayed()));
       // onView(withId(R.id.transaction_listview)).check(matches(isDisplayed()));
    }

    // check if deposit button works
    @Test
    public void testIfDepositButtonWorks() throws Exception{
        String value = "100.37";
        String expectedValue = "$100.37";
        // enter value in balance edit text
        onView(withId(R.id.input_balanceedittext)).perform(typeText(value), closeSoftKeyboard());
        // press the deposit button
        onView(withId(R.id.deposit_button)).perform(click());
        // see if the text view displays what you want
        onView(withId(R.id.balance_textview)).check(matches(withText(expectedValue)));
    }
    // check if withdraw button works
    @Test
    public void testIfWithdrawButtonWorks() throws Exception{
        String value1 = "100.37";
        String value2 = "50.17";
        String expectedValue = "$50.20";
        // enter value in balance edit text
        onView(withId(R.id.input_balanceedittext)).perform(typeText(value1), closeSoftKeyboard());
        // press the deposit button
        onView(withId(R.id.deposit_button)).perform(click());
        // enter value in balance edit text
        // press the withdraw button
        onView(withId(R.id.input_balanceedittext)).perform(typeText(value2),closeSoftKeyboard());
        onView(withId(R.id.withdraw_button)).perform(click());
        // see if the text view displays what you want
        onView(withId(R.id.balance_textview)).check(matches(withText(expectedValue)));
    }
    // check if user info is accurate
    @Test
    public void testIfAccountInfoButtonWorks() throws Exception{
        // click see info button
        onView(withId(R.id.see_accnt_info_button)).perform(click());

        // check if name and email is displayed
        onView(withId(R.id.name_text)).check(matches(isDisplayed()));
        onView(withId(R.id.email_text)).check(matches(isDisplayed()));
    }

}
