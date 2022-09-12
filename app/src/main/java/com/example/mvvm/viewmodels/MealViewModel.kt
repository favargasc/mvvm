package com.example.mvvm.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.example.mvvm.repositories.MealRepository
import com.example.mvvm.repositories.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealViewModel
@Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {
    private val _state: MutableState<MealListState> = mutableStateOf(MealListState()) //private state
    val state: State<MealListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getMealList()
    }

    fun getMealList() {
        mealRepository.getMeals().onEach { result ->
            when(result) {
                is Result.Error -> {
                    _state.value = MealListState(error = result.message ?: "Error Inesperado")
                }
                is Result.Loading -> {
                    _state.value = MealListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = MealListState(meals = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeMeal(meal: Meal) {
        mealRepository.removeMeal(meal)
    }

    fun addNewMeal(meal: Meal) {
        mealRepository.addNewMeal(meal)
    }

    fun modifyMeal(meal: Meal, mealId: String) {
        mealRepository.modifyMeal(meal, mealId)
    }
}