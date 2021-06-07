package com.example.roomsample.util

class StringUtils {
    companion object {
        fun getAgeText(age: String): String {
            return "($age Y)"
        }

        fun getDepartmentText(department: String): String {
            return "/$department"
        }

    }
}