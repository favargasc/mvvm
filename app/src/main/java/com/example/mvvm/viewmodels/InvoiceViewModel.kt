package com.example.mvvm.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.Invoice
import com.example.mvvm.repositories.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.mvvm.repositories.*
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class InvoiceViewModel
@Inject constructor(
    private val invoiceRepository: InvoiceRepository
) : ViewModel() {
    private val _state: MutableState<InvoicesListState> =
        mutableStateOf(InvoicesListState())
    val state: State<InvoicesListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun addNewInvoice(invoice: Invoice) {
        invoiceRepository.addNewInvoice(invoice)
    }

    init {
        getInvoices()
    }

    fun getInvoices() {
        invoiceRepository.getInvoices().onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value = InvoicesListState(error = result.message ?: "Error Inesperado")
                }
                is Result.Loading -> {
                    _state.value = InvoicesListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = InvoicesListState(meals = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeInvoice(invoice: Invoice) {
        invoiceRepository.removeInvoice(invoice)
    }

    fun modifyInvoice(id: String, date: String, invoiceId: String) {
        invoiceRepository.modifyInvoice(id, date, invoiceId)
    }
}