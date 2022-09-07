package com.example.mvvm.viewmodels
import androidx.lifecycle.ViewModel
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.example.mvvm.repositories.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MealViewModel
@Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {
    fun addNewMeal(meal: Meal) {
        mealRepository.addNewMeal(meal)
    }

    //get the meals into the callback
    fun getMeals(callback: (MutableList<Meal>)->Unit){
        var meals= mutableListOf<Meal>()
        CoroutineScope(Dispatchers.Main).async{
            meals=mealRepository.getMeals()
            callback(meals)
        }
    }

    fun modifyMeal(meal: Meal){
        mealRepository.modifyMeal(meal)
    }


    fun deleteMeal(meal: Meal){
        mealRepository.deleteMeal(meal)
    }

}