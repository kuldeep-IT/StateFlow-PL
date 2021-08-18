package com.peerbitskuldeep.stateflowpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {

            viewModel.loginCredential(
                name.text.toString(),
                password.text.toString()
            )

        }


        lifecycleScope.launchWhenStarted {

            viewModel.loginUiState.collect {

                when(it)
                {
                    is MainViewModel.LoginUiState.Success -> {
                        Snackbar.make(findViewById(android.R.id.content),"SuccessFully Login", Snackbar.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }

                    is MainViewModel.LoginUiState.Error ->
                    {
                        Snackbar.make(findViewById(android.R.id.content),it.msg, Snackbar.LENGTH_LONG).show()

                        progressBar.visibility = View.GONE
                    }

                    is MainViewModel.LoginUiState.Loading ->
                    {
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainViewModel.LoginUiState.Empty -> {

                    }

                    else -> Unit
                }

            }

        }


    }
}