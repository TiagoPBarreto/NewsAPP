package com.barreto.newsapp.presentation.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barreto.newsapp.domain.usecases.AppEntryUsesCases
import com.barreto.newsapp.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUsesCases: AppEntryUsesCases
) :ViewModel(){

    fun onEvent(event:OnBoardingEvents){
        when (event){
            is OnBoardingEvents.SaveAppEntry ->{
               saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsesCases.saveAPPEntry()
        }
    }
}