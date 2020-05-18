package com.mreigar.cardwalletapp.addcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mreigar.cardwalletapp.R
import com.mreigar.cardwalletapp.gone
import com.mreigar.cardwalletapp.visible
import com.mreigar.data.Error
import com.mreigar.data.Result
import com.mreigar.data.Success
import kotlinx.android.synthetic.main.fragment_add_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddCardFragment : Fragment() {

    private val viewModel: AddCardViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCardButton.setOnClickListener {
            viewModel.addCardToWallet(
                cardNameInputLayout.editText?.text.toString(),
                cardNumberInputLayout.editText?.text.toString(),
                expiryDateInputLayout.editText?.text.toString()
            )
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleLoader(it.isLoading)

            handleResult(it.addCardResult)
        })
    }

    private fun handleResult(addCardResult: Result<Boolean>?) {
        addCardResult?.let {
            when (it) {
                is Success -> Toast.makeText(requireContext(), "Card added success", Toast.LENGTH_SHORT).show()
                is Error -> Snackbar.make(addCardLayout, "Add credit card error: ${it.exception.message}", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun handleLoader(isLoading: Boolean) = if (isLoading) addCardLoader.visible() else addCardLoader.gone()
}