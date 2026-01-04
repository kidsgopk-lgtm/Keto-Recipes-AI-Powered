package com.keto.ai.recipes.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.keto.ai.recipes.model.User

class AuthRepository(
    private val firebaseAuth: FirebaseAuth
) {
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful, task.exception?.message)
            }
    }

    fun register(email: String, password: String, name: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
                }
                onResult(task.isSuccessful, task.exception?.message)
            }
    }

    fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful, task.exception?.message)
            }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun getCurrentUser(): User? {
        val firebaseUser = firebaseAuth.currentUser
        return firebaseUser?.let {
            User(
                id = it.uid.toLongOrNull() ?: 0,
                name = it.displayName ?: "",
                email = it.email ?: "",
                weight = 0.0,
                height = 0.0,
                goal = "",
                dailyCalories = 0,
                dailyFat = 0.0,
                dailyCarbs = 0.0,
                dailyProtein = 0.0
            )
        }
    }
}
