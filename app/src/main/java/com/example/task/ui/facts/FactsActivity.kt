package com.example.task.ui.facts

import android.content.res.Configuration
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task.R
import com.example.task.data.network.DataError
import com.example.task.data.network.Success
import com.example.task.ui.base.BaseComponentActivity
import com.example.task.util.observe
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FactsActivity : BaseComponentActivity<FactsViewModel>() {

    override val viewModel: FactsViewModel by viewModels()

    @ExperimentalFoundationApi
    @Composable
    override fun ProvideCompose() {

        val factsData = remember {
            mutableStateOf(listOf<String>())
        }
        val error = remember { mutableStateOf("") }

        observe(viewModel.factsData) {
            when (it) {
                is DataError -> {
                    error.value = it.errorDescription
                }

                is Success -> {
                    it.data.let { response ->
                        factsData.value = response!!.facts
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (error.value.isNotEmpty()) {
                AlertDialog(
                    onDismissRequest = {
                    },
                    title = {
                        Text(stringResource(R.string.error), style = MaterialTheme.typography.h4)
                    },
                    text = {
                        Text(error.value, fontSize = 16.sp)
                    },
                    confirmButton = {
                    },
                    dismissButton = {
                    },
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.White
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(
                        items = factsData.value
                    ) { index, comment ->
                        Text(
                            text = "${index + 1}. $comment",
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                }
            }
        }
    }


    @ExperimentalFoundationApi
    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {

        //jetpack compose compiler sometimes shows different colors
    }
}

