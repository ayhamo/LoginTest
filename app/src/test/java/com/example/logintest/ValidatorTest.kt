package com.example.logintest

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val amount = 3
        val desc = "Some"
        val result = Validator.validateInput(amount,desc)
        assertThat(result).isEqualTo(true)
    }

//    @Test
//    fun whenInputIsInvalid(){
//        val amount = -1
//        val desc = ""
//        val result = Validator.validateInput(amount, desc)
//        assertThat(result).isEqualTo(true)
//    }

}