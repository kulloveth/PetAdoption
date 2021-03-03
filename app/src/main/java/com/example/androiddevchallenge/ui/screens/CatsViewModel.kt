/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.PetRepository
import com.example.androiddevchallenge.data.model.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val repository: PetRepository) : ViewModel() {

    private val _catsLiveData = MutableLiveData<List<Cat>>()
    val catsLiveData: LiveData<List<Cat>> = _catsLiveData

    private val _catLiveData: MutableLiveData<Cat> = MutableLiveData()
    val catLiveData: LiveData<Cat> = _catLiveData
    init {
        viewModelScope.launch {
            _catsLiveData.postValue(repository.fetchCats())
        }
    }
    fun catById(id: Int) {
        _catsLiveData.value?.find { it.id == id }?.let {
            _catLiveData.postValue(it)
        }
    }
}
