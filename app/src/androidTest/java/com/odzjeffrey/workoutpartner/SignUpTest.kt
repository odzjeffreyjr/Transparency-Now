package com.odzjeffrey.workoutpartner

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.test.ext.junit.rules.ActivityScenarioRule

@RunWith(AndroidJUnit4::class)
class SignUpTest{

    @Rule
    @JvmField
    val signUpActivity = ActivityScenarioRule(SignUp::class.java)

    @Test
    fun signUp(){
        onView(withId(R.id.etName)).perform(typeText("JeffreyOduman"))
        onView(withId(R.id.etEmail)).perform(typeText("odzjeffreyjr1@gmail.com"))
        onView(withId(R.id.etPassword)).perform(typeText("Ilovemybaby200"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btnSignUp)).perform(click())
    }
}

