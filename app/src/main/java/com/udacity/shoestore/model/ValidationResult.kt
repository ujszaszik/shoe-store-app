package com.udacity.shoestore.model

sealed class ValidationResult {

    object Valid : ValidationResult()

    object Incomplete : ValidationResult()

    object Existing : ValidationResult()

    object Cancelled : ValidationResult()

}