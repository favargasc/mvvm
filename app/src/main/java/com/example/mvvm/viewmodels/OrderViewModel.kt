package com.example.mvvm.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.User
import com.example.mvvm.repositories.OrderRepository
import com.example.mvvm.repositories.Result
import com.example.mvvm.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OrderViewModel
@Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _state: MutableState<CartMealListState> = mutableStateOf(CartMealListState()) //private state
    val state: State<CartMealListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun addNewUser(cartMeal: CartMeal) {
        orderRepository.addNewOrder(cartMeal)
    }

    init {
        getCartMealList()
    }

    fun getCartMealList() {
        orderRepository.getOrders().onEach { result ->
            when(result) {
                is Result.Error -> {
                    _state.value = CartMealListState(error = result.message ?: "Error Inesperado")
                }
                is Result.Loading -> {
                    _state.value = CartMealListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = CartMealListState(meals = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}